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
          <template slot="score" slot-scope="text, record, index">
            <span v-if="index === 0">{{ name }}总得分：{{ record.score }}</span>
            <a-button type="link" v-else @click="handleClickStore(record.factorCode)">
              <span>{{ record.factorName }}得分：</span>
              <span>{{ record.score }}</span>
            </a-button>
          </template>
          <span slot="scoreTitle"></span>

          <template slot="rankCountry" slot-scope="text, record">
            <span v-if="record.rankCountryDiff > 0">{{ record.rankCountry + ' 上升+' + record.rankCountryDiff }}</span>
            <span v-if="record.rankCountryDiff === 0">{{ record.rankCountry + ' 持平' }}</span>
            <span v-if="record.rankCountryDiff < 0">{{ record.rankCountry + ' 下降' + record.rankCountryDiff }}</span>
          </template>
          <template slot="rankScope" slot-scope="text, record">
            <span v-if="record.rankScopeDiff > 0">{{ record.rankScope + ' 上升+' + record.rankScopeDiff }}</span>
            <span v-if="record.rankScopeDiff === 0">{{ record.rankScope + ' 持平' }}</span>
            <span v-if="record.rankScopeDiff < 0">{{ record.rankScope + ' 下降' + record.rankScopeDiff }}</span>
          </template>
          <template slot="diff" slot-scope="text, record">
            <span v-if="record.scoreDiff > 0">{{ '提高+' + record.scoreDiff }}</span>
            <span v-if="record.scoreDiff === 0">持平</span>
            <span v-if="record.scoreDiff < 0">{{ '降低' + record.scoreDiff }}</span>
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
          <a-table id="scoreChannelTable" :columns="scoreFactorColumns" :data-source="scoreFactorData" :pagination="false" :loading="scoreFactorLoading">
            <template slot="period" slot-scope="text, record">
              <span>{{ record.name }}</span>
            </template>
            <span slot="customTitle"></span>
          </a-table>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script>
import moment from 'moment';
import { getScoreFactorInfoForStore, getScoreChannelInfo, getScoreFactorInfo } from '@/api/score';
import { getChannelList1 } from '@/api/channel';
import { getAllFactor } from '@/api/factor';
import { getStoreByCode } from '@/api/store';

export default {
  data () {
    const scoreColumns = [
      {
        dataIndex: 'score',
        key: 'score',
        width: '40%',
        align: 'left',
        ellipsis: true,
        slots: { title: 'scoreTitle' },
        scopedSlots: { customRender: 'score' }
      },
      {
        title: '全国排名',
        dataIndex: 'rankCountry',
        key: 'rankCountry',
        width: '20%',
        align: 'center',
        scopedSlots: { customRender: 'rankCountry' },
        customCell: function (record, index) {
          return {
            style: {
              color: record.rankCountryDiff > 0 ? '#31D582' : record.rankCountryDiff < 0 ? '#FF4B4B' : ''
            }
          };
        }
      },
      {
        title: '区域排名',
        dataIndex: 'rankScope',
        key: 'rankScope',
        width: '20%',
        align: 'center',
        scopedSlots: { customRender: 'rankScope' },
        customCell: function (record, index) {
          return {
            style: {
              color: record.rankScopeDiff > 0 ? '#31D582' : record.rankScopeDiff < 0 ? '#FF4B4B' : ''
            }
          };
        }
      },
      {
        title: '环比上期',
        dataIndex: 'diff',
        key: 'diff',
        align: 'center',
        width: '20%',
        scopedSlots: { customRender: 'diff' },
        customCell: function (record, index) {
          return {
            style: {
              color: record.scoreDiff > 0 ? '#31D582' : record.scoreDiff < 0 ? '#FF4B4B' : ''
            }
          };
        }
      }
    ];
    return {
      title: '',
      scopeId: 6,
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
      store_code: this.code,
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

      getStoreByCode(this.code).then(res => {
        const store = res.resData;
        this.title = '所属区域：' + store.name;
        this.name = store.name;
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
        factorList.forEach((factor) => {
          scoreFactorColumns.push({
            title: factor.name,
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
      await getScoreFactorInfoForStore(params).then(res => {
        this.scoreLoading = false;
        this.scoreData = [...res.resData.totalScoreList, ...res.resData.scoreFactorList];
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
    handleClickStore (factorCode) {
      this.$router.push({
        path: '/factor/Details',
        query: {
          store_code: this.code,
          factor_code: factorCode
        }
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
