<template>
  <div class="card-container">
    <a-affix :offset-top="top" @change="handleAffixChange">
      <div style="background-color: #FFFFFF">
        <a-row class="title">
          <a-col :span="24">代码：<span>{{ storeCode }}</span></a-col>
          <a-col :span="24">名称：<span>{{ storeName }}</span></a-col>
        </a-row>
        <!-- <a-divider></a-divider> -->
      </div>
    </a-affix>
    <a-tabs
      id="factorDetailsTab"
      v-model="activeKey"
      type="card"
      size="small"
      :tabBarGutter="tabBarGutter"
      :tabBarStyle="tabBarStyle">
      <a-tab-pane v-for="pane in panes" :key="pane.key" :tab="pane.title" :closable="pane.closable">
        <a-table
          id="scoreFactorTable"
          :table-layout="tableLayout"
          :columns="scoreFactorColumns"
          :data-source="scoreFactorData"
          :pagination="false"
          :loading="scoreFactorLoading">
          <template slot="score" slot-scope="text, record, index">
            <span v-if="index === 0">{{ record.factorName + '：' + record.score }}</span>
            <a-button type="link" v-else @click="handleClickStore(record.factorCode)">{{ record.factorName }}得分：{{ record.score }} </a-button>
          </template>
          <span slot="scoreTitle"></span>

          <template slot="rankCountry" slot-scope="text, record">
            <span>{{ record.rankCountry }}</span>
            <span v-if="record.rankCountryDiff !== 0">
              <a-icon :type="record.rankCountryDiff > 0 ? 'arrow-up' : 'arrow-down'" />{{ record.rankCountryDiff > 0 ? record.rankCountryDiff: record.rankCountryDiff * (-1) }}
            </span>
          </template>
          <template slot="rankScope" slot-scope="text, record">
            <span>{{ record.rankScope }}</span>
            <span v-if="record.rankScopeDiff !== 0">
              <a-icon :type="record.rankScopeDiff > 0 ? 'arrow-up' : 'arrow-down'" />{{ record.rankScopeDiff > 0 ? record.rankScopeDiff : record.rankScopeDiff * (-1) }}
            </span>
          </template>
          <template slot="scoreDiff" slot-scope="text, record">
            <span v-if="record.scoreDiff !== 0">
              <a-icon :type="record.scoreDiff > 0 ? 'arrow-up' : 'arrow-down'" />{{ record.scoreDiff > 0 ? record.scoreDiff : record.scoreDiff * (-1) }}分
            </span>
            <span v-else>持平</span>
          </template>
          <!-- <template slot="scoreDiff" slot-scope="text, record">
            <span v-if="record.scoreDiff > 0">{{ '提高+' + record.scoreDiff }}</span>
            <span v-if="record.scoreDiff === 0">持平</span>
            <span v-if="record.scoreDiff < 0">{{ '降低' + record.scoreDiff }}</span>
          </template> -->
        </a-table>

        <a-table
          id="regulationScoreTable"
          :table-layout="tableLayout"
          :columns="regulationScoreColumns"
          :data-source="regulationScoreData"
          :pagination="false"
          :loading="regulationScoreLoading"
          :scroll="{ y: 300 }">
          <template slot="element" slot-scope="text, record">
            <span>{{ record.elementCode.split(';')[1] || '' }}</span>
          </template>
          <template slot="regulation" slot-scope="text, record">
            <span v-html="record.description"></span>
          </template>
          <template slot="evaluateScore" slot-scope="text, record">
            <span>{{ record.regulationScoreMap.evaluateScore || '' }}</span>
          </template>
          <template slot="bonusScore" slot-scope="text, record">
            <span>{{ record.regulationScoreMap.bonusScore || '' }}</span>
          </template>
          <template v-for="channel in channelList" :slot="'evaluateChannelScore' + channel.id" slot-scope="text, record">
            <a-button
              class="scoreChannelBtn"
              :key="channel.id"
              type="link"
              @click="handleClickGrade(channel.code, record.id, 1)"
              size="small"
              v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.name]">
              <span
                v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] === '优秀'"
                style="color: #09CDFF">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] }}</span>
              <span
                v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] === '优良'"
                style="color: #2DD42D">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] }}</span>
              <span
                v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] === '达标'"
                style="color: #FFD27A">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] }}</span>
              <span
                v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] === '薄弱'"
                style="color: #FF3030">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.name] }}</span>
            </a-button>
          </template>
          <template v-for="channel in channelList" :slot="'bonusChannelScore' + channel.id" slot-scope="text,record">
            <a-button
              class="scoreChannelBtn"
              :key="channel.id"
              type="link"
              @click="handleClickGrade(channel.code, record.id, 2,record.regulationScoreChannelMap['bonusChannelScore' + channel.name])"
              size="small"
              v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.name]">
              <span
                v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.name] === '优秀'"
                style="color: #09CDFF">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.name] }}</span>
              <span
                v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.name] === '优良'"
                style="color: #2DD42D">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.name] }}</span>
              <span
                v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.name] === '达标'"
                style="color: #FFD27A">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.name] }}</span>
              <span
                v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.name] === '薄弱'"
                style="color: #FF3030">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.name] }}</span>
            </a-button>
          </template>
        </a-table>

        <a-table
          id="deductTable"
          :columns="deductChannelScoreColumns"
          :data-source="deductChannelScoreData"
          :pagination="false"
          :showHeader="false"
          :locale="locale">
          <template slot="deductScore" slot-scope="text, record">
            <a-button type="link" @click="handleClickDeductScore(record.channelCode, record.regulationId)">{{ record.deductScore }}</a-button>
          </template>
        </a-table>

      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
import moment from 'moment';
import merge from 'webpack-merge';
import { getStoreByCode } from '@/api/store';
import { getAllFactor } from '@/api/factor';
import { getScoreFactorInfoForFactor } from '@/api/score';
import { getAllChannelList } from '@/api/channel';
import { getRegulationScoreInfo } from '@/api/regulation';

export default {
  data () {
    const deductChannelScoreColumns = [
      {
        // title: '扣分名称',
        dataIndex: 'deductName',
        key: 'deductName',
        width: '40%'
      },
      {
        // title: 'aaa',
        dataIndex: 'deductScore',
        key: 'deductScore',
        scopedSlots: { customRender: 'deductScore' },
        width: '60%'
      }
    ];
    return {
      top: 0,
      storeCode: '',
      storeName: '',
      factorCode: '',
      month: moment().add('month', 0).format('yyyy年MM月'),
      period: moment().add('month', 0).format('yyyyMM'),
      lastPeriod: moment().subtract(1, 'month').format('yyyyMM'),
      // 考核渠道列表
      channelList: [],
      // 扣分渠道列表
      channelList2: [],
      activeKey: this.factorCode,
      panes: [],
      scoreFactorLoading: false,
      scoreFactorColumns: [],
      scoreFactorData: [],
      regulationScoreLoading: false,
      regulationScoreColumns: [],
      regulationScoreData: [],
      deductChannelScoreColumns,
      deductChannelScoreData: [],
      tabBarGutter: 0,
      tabBarStyle: {
        // textAlign: 'center',
        // background: '#FF0000'
      },
      locale: {
        emptyText: '无扣分'
      },
      tableLayout: 'fixed'
    };
  },
  mounted () {
    this.initialData();
    this.tabActiveKey = this.$route.query.factor_code;
    // await this.fetchChannelList2({
    //   ctype: 2
    // });

    this.fetchFactorColumns();
    this.fetchFactorData({
      store_code: this.storeCode,
      factor_code: this.factorCode,
      current_period: this.period,
      last_period: this.lastPeriod
    });
    this.fetchRegulationScoreColumns();
    this.fetchRegulationScoreData({
      store_code: this.storeCode,
      period: this.period,
      factor_code: this.factorCode
    });

    this.$watch('$route', (route) => {
      // console.log('change');
      const factorCode = route.query.factor_code;
      this.tabActiveKey = factorCode;
      this.factorCode = factorCode;
      this.fetchFactorData({
        store_code: this.storeCode,
        factor_code: this.factorCode,
        current_period: this.period,
        last_period: this.lastPeriod
      });
      this.fetchRegulationScoreData({
        store_code: this.storeCode,
        period: this.period,
        factor_code: this.factorCode
      });
    });
  },
  watch: {
    activeKey: function (val) {
      // this.$route.query.factorId = val;
      this.$router.push({
        // path: '/factor/Details',
        query: merge(this.$route.query, { factor_code: val })
      });
    }
  },
  methods: {
    initialData () {
      this.storeCode = this.$route.query.store_code;
      this.factorCode = this.$route.query.factor_code;
      getStoreByCode(this.storeCode).then(res => {
        // console.log(res);
        if (res.resCode === 200) {
          this.storeName = res.resData.name;
        }
      });
      getAllFactor().then(res => {
        const factorList = res.resData;
        const panes = [];
        factorList.forEach(factor => {
          panes.push({
            title: factor.name,
            content: factor.name,
            key: factor.code
          });
        });
        this.panes = panes;
        this.activeKey = this.$route.query.factor_code;
      });
    },
    fetchFactorColumns () {
      const scoreFactorColumns = [
        {
          dataIndex: 'score',
          key: 'score',
          slots: { title: 'scoreTitle' },
          scopedSlots: { customRender: 'score' },
          width: '40%',
          align: 'left'
        },
        {
          title: '全国排名',
          dataIndex: 'rankCountry',
          key: 'rankCountry',
          scopedSlots: { customRender: 'rankCountry' },
          width: '20%',
          align: 'center',
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
          scopedSlots: { customRender: 'rankScope' },
          width: '20%',
          align: 'center',
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
          dataIndex: 'scoreDiff',
          key: 'scoreDiff',
          scopedSlots: { customRender: 'scoreDiff' },
          width: '20%',
          align: 'center',
          customCell: function (record, index) {
            return {
              style: {
                color: record.scoreDiff > 0 ? '#31D582' : record.scoreDiff < 0 ? '#FF4B4B' : ''
              }
            };
          }
        }
      ];
      this.scoreFactorColumns = scoreFactorColumns;
    },
    fetchFactorData (params = {}) {
      this.scoreFactorLoading = true;
      getScoreFactorInfoForFactor(params).then(res => {
        this.scoreFactorLoading = false;
        this.scoreFactorData = res.resData.scoreFactorList;
      });
    },
    // fetchChannelList2 (params = {}) {
    //   getChannelList2().then(res => {
    //     this.channelList2 = res.resData;
    //   });
    // },
    fetchRegulationScoreColumns () {
      getAllChannelList().then(res => {
        const channelList = [res.resData[0], res.resData[1], res.resData[2]];
        this.channelList = channelList;
        const channelList2 = [res.resData[3], res.resData[4], res.resData[5]];
        this.channelList2 = channelList2;
        const regulationScoreColumns = [
          {
            title: '要素',
            dataIndex: 'element',
            key: 'element',
            align: 'left',
            width: '25%',
            scopedSlots: { customRender: 'element' }
          },
          {
            title: '细则',
            dataIndex: 'regulation',
            key: 'regulation',
            align: 'left',
            width: '15%',
            scopedSlots: { customRender: 'regulation' }
          },
          {
            title: '综合得分',
            dataIndex: 'comprehensiveScore',
            key: 'comprehensiveScore',
            align: 'center',
            children: [
              {
                title: '考核项',
                dataIndex: 'evaluateScore',
                key: 'evaluateScore',
                align: 'center',
                width: '7.5%',
                scopedSlots: { customRender: 'evaluateScore' }
              },
              {
                title: '加分项',
                dataIndex: 'bonusScore',
                key: 'bonusScore',
                align: 'center',
                width: '7.5%',
                scopedSlots: { customRender: 'bonusScore' }
              }
            ]
          },
          {
            title: '基础考核分',
            dataIndex: 'evaluateChannelScore',
            key: 'evaluateChannelScore'
            // width: '22.5%'
          },
          {
            title: '附加分',
            dataIndex: 'bonusChannelScore',
            key: 'bonusChannelScore'
            // width: '22.5%'
          }
        ];
        const evaluateChannelScoreChildren = [];
        channelList.forEach((channel) => {
          evaluateChannelScoreChildren.push({
            title: channel.name,
            dataIndex: 'evaluateChannelScore' + channel.name,
            key: 'evaluateChannelScore' + channel.id,
            align: 'center',
            width: '7.5%',
            scopedSlots: { customRender: 'evaluateChannelScore' + channel.id }
          });
        });
        regulationScoreColumns[3].children = evaluateChannelScoreChildren;
        const bonusChannelScoreChildren = [];
        channelList.forEach((channel) => {
          bonusChannelScoreChildren.push({
            title: channel.name,
            dataIndex: 'bonusChannelScore' + channel.name,
            key: 'bonusChannelScore' + channel.id,
            align: 'center',
            width: '7.5%',
            scopedSlots: { customRender: 'bonusChannelScore' + channel.id }
          });
        });
        regulationScoreColumns[4].children = bonusChannelScoreChildren;
        this.regulationScoreColumns = regulationScoreColumns;
      });
    },
    fetchRegulationScoreData (params = {}) {
      this.regulationScoreLoading = true;
      getRegulationScoreInfo(params).then(res => {
        this.regulationScoreLoading = false;
        this.regulationScoreData = res.resData;
        // console.log(res.resData);
        // const regulationScoreData = res.resData;
        const deductChannelScoreData = [];
        this.regulationScoreData.forEach(score => {
          this.channelList2.forEach(channel => {
            // eslint-disable-next-line no-prototype-builtins
            if (score.regulationScoreChannelMap.hasOwnProperty('deductChannelScore' + channel.name)) {
              const deductScore = score.regulationScoreChannelMap['deductChannelScore' + channel.name];
              if (deductScore !== '0') {
                deductChannelScoreData.push({
                  deductName: channel.name,
                  deductScore: deductScore,
                  channelId: channel.id,
                  channelCode: channel.code,
                  regulationId: score.id
                });
              }
            }
          });
        });
        this.deductChannelScoreData = deductChannelScoreData;
        // console.log(this.deductChannelScoreData);
        // this.regulationScoreData = [...res.resData, ...deductChannelScoreList];
      });
    },
    handleClickGrade (channelCode, regulationId, scoreType) {
      this.$router.push({
        path: '/regulation/' + channelCode,
        // name: 'RegulationSurvey',
        query: {
          store_code: this.storeCode,
          regulation_id: regulationId,
          score_type: scoreType
        }
      });
    },
    handleClickDeductScore (channelCode, regulationId) {
      // console.log(channelCode, regulationId);
      this.$router.push({
        path: '/regulation/' + channelCode,
        query: {
          store_code: this.storeCode,
          regulation_id: regulationId
        }
      });
    },
    handleAffixChange (affixed) {
      console.log(affixed);
    }
  }

};
</script>

<style lang="less" scoped>
@import './Factor.less';
</style>
