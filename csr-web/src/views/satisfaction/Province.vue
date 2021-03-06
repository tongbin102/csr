<template>
  <div>
    <a-row :gutter="[16,{xs:8,sm:16,md:24,lg:32}]">
      <a-col :span="12">{{ title }}</a-col>
      <a-col :span="12"><span style="float: right;">评估期：{{ month }}</span></a-col>
    </a-row>
    <a-row id="satisfactionDetails">
      <a-col :span="24">
        <a-table
          id="scoreTable"
          :table-layout="tableLayout"
          :columns="scoreColumns"
          :data-source="scoreData"
          :pagination="false"
          :loading="scoreLoading">
          <template slot="name" slot-scope="text, record, index">
            <span v-if="index === 0">{{ name }}</span>
            <a-button type="link" v-else @click="handleClickCity(record.storeCode)">
              <span>{{ record.storeName }}</span>
            </a-button>
          </template>
          <span slot="scoreTitle"></span>
          <template slot="rankCountry" slot-scope="text, record">
            <span>{{ record.rankCountry }}</span>
            <span v-if="record.rankCountryDiff !== 0 && record.score != record.scoreDiff"><a-icon :type="record.rankCountryDiff > 0 ? 'arrow-up' : 'arrow-down'"/>{{ record.rankCountryDiff > 0 ? record.rankCountryDiff: record.rankCountryDiff * (-1) }}</span>
          </template>
          <template slot="rankScope" slot-scope="text, record">
            <span>{{ record.rankScope }}</span>
            <span v-if="record.rankScopeDiff !== 0 && record.score != record.scoreDiff"><a-icon :type="record.rankScopeDiff > 0 ? 'arrow-up' : 'arrow-down'"/>{{ record.rankScopeDiff > 0 ? record.rankScopeDiff : record.rankScopeDiff * (-1) }}</span>
          </template>
          <template slot="diff" slot-scope="text, record" v-if="record.score != record.scoreDiff">
            <span v-if="record.scoreDiff !== 0"><a-icon :type="record.scoreDiff > 0 ? 'arrow-up' : 'arrow-down'"/>{{ record.scoreDiff > 0 ? record.scoreDiff : record.scoreDiff * (-1) }}分</span>
            <span v-else>持平</span>
          </template>
        </a-table>
        <div class="channelInfo">
          <a-row>
            <a-col :span="24">
              <span style="font-weight: bold;">分渠道得分</span>
              <a-button type="link" style="float: right; color: #47DADA; height:100%; line-height:100%;" @click="handleClickChannelAnalysis">
                <span style="text-decoration: underline;">趋势分析</span>
              </a-button>
            </a-col>
          </a-row>
          <a-table id="scoreChannelTable" :columns="scoreChannelColumns" :data-source="scoreChannelData" :pagination="false" :loading="scoreChannelLoading">
            <template slot="period" slot-scope="text, record">
              <span>{{ record.name }}</span>
            </template>
            <span slot="periodTitle"></span>
          </a-table>
        </div>
        <div class="factorInfo">
          <a-row>
            <a-col :span="24">
              <span style="font-weight: bold;">分因子得分</span>
              <a-button type="link" style="float: right; color: #47DADA; height:100%; line-height:100%;" @click="handleClickFactorAnalysis">
                <span style="text-decoration: underline;">趋势分析</span>
              </a-button>
            </a-col>
          </a-row>
          <a-table id="scoreFactorTable" :columns="scoreFactorColumns" :data-source="scoreFactorData" :pagination="false" :loading="scoreFactorLoading">
            <template slot="period" slot-scope="text, record">
              <span>{{ record.name }}</span>
            </template>
            <span v-for="factor in factorList" :key="factor.id" :slot="factor.id">{{ factor.name }}</span>
            <template slot="title">
            </template>
          </a-table>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script>
import moment from 'moment';
import { getScoreInfo, getScoreChannelInfo, getScoreFactorInfo } from '@/api/score';
import { getChannelList1 } from '@/api/channel';
import { getAllFactor } from '@/api/factor';
import { getProvinceByCode } from '@/api/province';

export default {
  data () {
    const scoreColumns = [
      {
        dataIndex: 'name',
        key: 'name',
        width: '40%',
        align: 'left',
        slots: { title: 'nameTitle' },
        scopedSlots: { customRender: 'name' }
      },
      {
        title: '得分',
        dataIndex: 'score',
        key: 'score',
        width: '10%',
        align: 'center',
        scopedSlots: { customRender: 'score' }
      },
      {
        title: '全国排名',
        dataIndex: 'rankCountry',
        key: 'rankCountry',
        width: '15%',
        align: 'center',
        scopedSlots: { customRender: 'rankCountry' },
        customCell: function (record, index) {
          let color = '';
          if (record.score - record.scoreDiff !== 0) {
            color = record.rankCountryDiff > 0 ? '#31D582' : record.rankCountryDiff < 0 ? '#FF4B4B' : '';
          }
          return {
            style: {
              color: color
            }
          };
        }
      },
      {
        title: '区域排名',
        dataIndex: 'rankScope',
        key: 'rankScope',
        width: '15%',
        align: 'center',
        scopedSlots: { customRender: 'rankScope' },
        customCell: function (record, index) {
          let color = '';
          if (record.score - record.scoreDiff !== 0) {
            color = record.rankScopeDiff > 0 ? '#31D582' : record.rankScopeDiff < 0 ? '#FF4B4B' : '';
          }
          return {
            style: {
              color: color
            }
          };
        }
      },
      {
        title: '环比',
        dataIndex: 'diff',
        key: 'diff',
        width: '10%',
        align: 'center',
        scopedSlots: { customRender: 'diff' },
        customCell: function (record, index) {
          let color = '';
          if (record.score - record.scoreDiff !== 0) {
            color = record.scoreDiff > 0 ? '#31D582' : record.scoreDiff < 0 ? '#FF4B4B' : '';
          }
          return {
            style: {
              color: color
            }
          };
        }
      }
    ];
    return {
      title: '',
      scopeId: 3,
      code: '',
      name: '',
      month: '',
      period: '',
      lastPeriod: '',
      scoreLoading: false,
      scoreChannelLoading: false,
      scoreFactorLoading: false,
      scoreColumns,
      scoreData: [],
      scoreChannelColumns: [],
      scoreChannelData: [],
      scoreFactorColumns: [],
      scoreFactorData: [],
      factorList: [],
      tableLayout: 'fixed'
    };
  },
  mounted () {
    this.initialData();
    this.fetchColumns();
    this.fetchScoreData({
      scope_id: this.scopeId,
      parent_code: this.code,
      current_period: this.period,
      last_period: this.lastPeriod
    });
  },
  methods: {
    initialData () {
      this.code = this.$route.query.code;
      this.month = moment().add('month', 0).format('yyyy年MM月');
      this.period = moment().add('month', 0).format('yyyyMM');
      this.lastPeriod = moment().subtract(1, 'month').format('yyyyMM');

      getProvinceByCode(this.code).then(res => {
        const province = res.resData;
        this.title = '所属区域：' + province.name;
        this.name = province.name;
      });
    },
    fetchColumns (params = {}) {
      getChannelList1(params).then(res => {
        const scoreChannelColumns = [];
        scoreChannelColumns.push({
          dataIndex: 'period',
          key: 'period',
          width: '10%',
          align: 'center',
          slots: { title: 'periodTitle' }
        });
        const channelList = res.resData;
        this.channelList = channelList;
        channelList.forEach((channel) => {
          scoreChannelColumns.push({
            title: channel.name,
            dataIndex: channel.name,
            key: channel.id,
            width: (90 / channelList.length) + '%',
            align: 'center'
          });
        });
        this.scoreChannelColumns = scoreChannelColumns;
      });
      getAllFactor(params).then(res => {
        const scoreFactorColumns = [];
        scoreFactorColumns.push({
          dataIndex: 'period',
          key: 'period',
          width: '10%',
          align: 'center',
          slots: { title: 'periodTitle' }
        });
        const factorList = res.resData;
        this.factorList = factorList;
        factorList.forEach(factor => {
          scoreFactorColumns.push({
            // title: factor.name,
            slots: { title: factor.id },
            dataIndex: factor.name,
            key: factor.id,
            width: (90 / factorList.length) + '%',
            align: 'center'
          });
        });
        this.scoreFactorColumns = scoreFactorColumns;
      });
    },
    async fetchScoreData (params = {}) {
      this.scoreLoading = true;
      await getScoreInfo(params).then(res => {
        this.scoreLoading = false;
        this.scoreData = [...res.resData.totalScoreList, ...res.resData.childScoreList];
      });
      this.fetchScoreChannelData({
        scope_id: this.scopeId,
        store_code: this.code,
        current_period: this.period,
        last_period: this.lastPeriod
      });
      this.fetchScoreFactorData({
        scope_id: this.scopeId,
        store_code: this.code,
        current_period: this.period,
        last_period: this.lastPeriod
      });
    },
    fetchScoreChannelData (params = {}) {
      this.scoreChannelLoading = true;
      getScoreChannelInfo(params).then(res => {
        this.scoreChannelLoading = false;
        this.scoreChannelData = res.resData;
      });
    },
    fetchScoreFactorData (params = {}) {
      this.scoreFactorLoading = true;
      getScoreFactorInfo(params).then(res => {
        this.scoreFactorLoading = false;
        this.scoreFactorData = res.resData;
      });
    },
    handleClickCity (code) {
      this.$router.push({
        path: '/satisfaction/city',
        query: { code: code }
      });
    },
    handleClickChannelAnalysis () {
      this.$router.push({
        path: '/analysis/channel',
        query: {
          scope_id: this.scopeId,
          code: this.code
        }
      });
    },
    handleClickFactorAnalysis () {
      this.$router.push({
        path: '/analysis/factor',
        query: {
          scope_id: this.scopeId,
          code: this.code
        }
      });
    }
  }

};
</script>
<style lang="less" scoped>
@import './Satisfaction.less';
</style>
