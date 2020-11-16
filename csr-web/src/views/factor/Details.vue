<template>
  <a-tabs v-model="activeKey" type="card">
    <a-tab-pane v-for="pane in panes" :key="pane.key" :tab="pane.title" :closable="pane.closable">

      <a-table :columns="scoreFactorColumns" :data-source="scoreFactorData" :pagination="false" :loading="scoreFactorLoading">
        <template slot="score" slot-scope="text, record, index">
          <span v-if="index === 0">{{ record.factorName + '：' + record.score }}</span>
          <a-button type="link" v-else @click="handleClickStore(record.factorId)">{{ record.factorName }}得分：{{ record.score }} </a-button>
        </template>
        <span slot="scoreTitle"></span>

        <template slot="rankCountry" slot-scope="text, record">
          <span class="increase" v-if="record.rankCountryDiff > 0">{{ record.rankCountry + ' 上升+' + record.rankCountryDiff }}</span>
          <span class="hold" v-if="record.rankCountryDiff === 0">{{ record.rankCountry + ' 持平' }}</span>
          <span class="decrease" v-if="record.rankCountryDiff < 0">{{ record.rankCountry + ' 下降' + record.rankCountryDiff }}</span>
        </template>
        <template slot="rankScope" slot-scope="text, record">
          <span class="increase" v-if="record.rankScopeDiff > 0">{{ record.rankScope + ' 上升+' + record.rankScopeDiff }}</span>
          <span class="hold" v-if="record.rankScopeDiff === 0">{{ record.rankScope + ' 持平' }}</span>
          <span class="decrease" v-if="record.rankScopeDiff < 0">{{ record.rankScope + ' 下降' + record.rankScopeDiff }}</span>
        </template>
        <template slot="diff" slot-scope="text, record">
          <span class="increase" v-if="record.scoreDiff > 0">{{ '提高+' + record.scoreDiff }}</span>
          <span class="hold" v-if="record.scoreDiff === 0">持平</span>
          <span class="decrease" v-if="record.scoreDiff < 0">{{ '降低' + record.scoreDiff }}</span>
        </template>
      </a-table>

      <a-divider></a-divider>

      <a-table :columns="regulationScoreColumns" :data-source="regulationScoreData" :pagination="false" :loading="regulationScoreLoading">
        <template slot="element" slot-scope="text, record">
          <span>{{ record.elementName || '' }}</span>
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
          <a-button :key="channel.id" type="link" @click="handleClickGrade(channel.code, record.id, 1)" size="small">
            <span
              v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] === '优秀'"
              style="color: #09CDFF">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] || '' }}</span>
            <span
              v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] === '优良'"
              style="color: #2DD42D">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] || '' }}</span>
            <span
              v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] === '达标'"
              style="color: #FFD27A">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] || '' }}</span>
            <span
              v-if="record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] === '薄弱'"
              style="color: #FF3030">{{ record.regulationScoreChannelMap['evaluateChannelScore' + channel.id] || '' }}</span>
          </a-button>
        </template>
        <template v-for="channel in channelList" :slot="'bonusChannelScore' + channel.id" slot-scope="text,record">
          <a-button :key="channel.id" type="link" @click="handleClickGrade(channel.code, record.id, 2)" size="small">
            <span
              v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.id] === '优秀'"
              style="color: #09CDFF">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.id] || '' }}</span>
            <span
              v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.id] === '优良'"
              style="color: #2DD42D">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.id] || '' }}</span>
            <span
              v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.id] === '达标'"
              style="color: #FFD27A">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.id] || '' }}</span>
            <span
              v-if="record.regulationScoreChannelMap['bonusChannelScore' + channel.id] === '薄弱'"
              style="color: #FF3030">{{ record.regulationScoreChannelMap['bonusChannelScore' + channel.id] || '' }}</span>
          </a-button>
        </template>
      </a-table>

      <a-divider></a-divider>

      <a-table :columns="deductChannelScoreColumns" :data-source="deductChannelScoreData" :pagination="false" :showHeader="false">
        <template slot="deductScore" slot-scope="text, record">
          <a-button type="link" @click="handleClickDeductScore(record.channelCode, record.regulationId)">{{ record.deductScore }}</a-button>
        </template>
      </a-table>

    </a-tab-pane>
  </a-tabs>
</template>

<script>
import moment from 'moment';
import merge from 'webpack-merge';
import { getAllFactor } from '@/api/factor';
import { getScoreFactorInfoForFactor } from '@/api/score';
import { getChannelList1, getChannelList2 } from '@/api/channel';
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
      storeId: '',
      factorId: '',
      month: moment().add('month', 0).format('yyyy年MM月'),
      period: moment().add('month', 0).format('yyyyMM'),
      lastPeriod: moment().subtract(1, 'month').format('yyyyMM'),
      // 考核渠道列表
      channelList: [],
      // 扣分渠道列表
      channelList2: [],
      activeKey: this.factorId,
      panes: [],
      scoreFactorLoading: false,
      scoreFactorColumns: [],
      scoreFactorData: [],
      regulationScoreLoading: false,
      regulationScoreColumns: [],
      regulationScoreData: [],
      deductChannelScoreColumns,
      deductChannelScoreData: []
    };
  },
  async created () {
    this.initialData();
    this.tabActiveKey = this.$route.query.factor_id;
    await this.fetchChannelList2({
      ctype: 2
    });

    this.fetchFactorColumns();
    this.fetchFactorData({
      store_id: this.storeId,
      factor_id: this.factorId,
      current_period: this.period,
      last_period: this.lastPeriod
    });
    this.fetchRegulationScoreColumns();
    this.fetchRegulationScoreData({
      store_id: this.storeId,
      period: this.period,
      factor_id: this.factorId
    });

    this.$watch('$route', (route) => {
      const factorId = route.query.factor_id;
      this.tabActiveKey = factorId;
      this.factorId = factorId;
      this.fetchFactorData({
        store_id: this.storeId,
        factor_id: this.factorId,
        current_period: this.period,
        last_period: this.lastPeriod
      });
      this.fetchRegulationScoreData({
        store_id: this.storeId,
        period: this.period,
        factor_id: this.factorId
      });
    });
  },
  watch: {
    activeKey: function (val) {
      // this.$route.query.factorId = val;
      this.$router.push({
        // path: '/factor/Details',
        query: merge(this.$route.query, { factor_id: val })
      });
    }
  },
  methods: {
    initialData () {
      this.storeId = this.$route.query.store_id;
      this.factorId = this.$route.query.factor_id;
        getAllFactor().then(res => {
          const factorList = res.resData;
          const panes = [];
          factorList.forEach(factor => {
            panes.push({
              title: factor.name,
              content: factor.name,
              key: factor.id
            });
          });
          this.panes = panes;
          this.activeKey = this.$route.query.factor_id;
        });
    },
    fetchFactorColumns () {
      const scoreFactorColumns = [
        {
          dataIndex: 'score',
          key: 'score',
          slots: { title: 'scoreTitle' },
          scopedSlots: { customRender: 'score' },
          width: '40%'
        },
        {
          title: '全国排名',
          dataIndex: 'rankCountry',
          key: 'rankCountry',
          scopedSlots: { customRender: 'rankCountry' },
          width: '20%'
        },
        {
          title: '区域排名',
          dataIndex: 'rankScope',
          key: 'rankScope',
          scopedSlots: { customRender: 'rankScope' },
          width: '20%'
        },
        {
          title: '环比上期',
          dataIndex: 'diff',
          key: 'diff',
          scopedSlots: { customRender: 'diff' },
          width: '20%'
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
    fetchChannelList2 (params = {}) {
      getChannelList2().then(res => {
        this.channelList2 = res.resData;
      });
    },
    fetchRegulationScoreColumns () {
      getChannelList1().then(res => {
        this.channelList = res.resData;
        const channelList = this.channelList;
        const regulationScoreColumns = [
          {
            title: '要素',
            dataIndex: 'element',
            key: 'element',
            scopedSlots: { customRender: 'element' },
            width: '25%'
          },
          {
            title: '细则',
            dataIndex: 'regulation',
            key: 'regulation',
            scopedSlots: { customRender: 'regulation' },
            width: '15%'
          },
          {
            title: '综合得分',
            dataIndex: 'comprehensiveScore',
            key: 'comprehensiveScore',
            // width: '15%',
            children: [
              {
                title: '考核项',
                dataIndex: 'evaluateScore',
                key: 'evaluateScore',
                scopedSlots: { customRender: 'evaluateScore' },
                width: '7.5%'
              },
              {
                title: '加分项',
                dataIndex: 'bonusScore',
                key: 'bonusScore',
                scopedSlots: { customRender: 'bonusScore' },
                width: '7.5%'
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
          // console.log(elementScoreColumns[2].children[1]);
          evaluateChannelScoreChildren.push({
            title: channel.name,
            dataIndex: 'evaluateChannelScore' + channel.id,
            key: 'evaluateChannelScore' + channel.id,
            scopedSlots: { customRender: 'evaluateChannelScore' + channel.id },
            width: '7.5%'
          });
        });
        regulationScoreColumns[3].children = evaluateChannelScoreChildren;
        const bonusChannelScoreChildren = [];
        channelList.forEach((channel) => {
          // console.log(elementScoreColumns[2].children[1]);
          bonusChannelScoreChildren.push({
            title: channel.name,
            dataIndex: 'bonusChannelScore' + channel.id,
            key: 'bonusChannelScore' + channel.id,
            scopedSlots: { customRender: 'bonusChannelScore' + channel.id },
            width: '7.5%'
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
            if (score.regulationScoreChannelMap.hasOwnProperty('deductChannelScore' + channel.id)) {
              deductChannelScoreData.push({
                deductName: channel.name,
                deductScore: score.regulationScoreChannelMap['deductChannelScore' + channel.id],
                channelId: channel.id,
                channelCode: channel.code,
                regulationId: score.id
              });
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
          store_id: this.storeId,
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
          store_id: this.storeId,
          regulation_id: regulationId
        }
      });
    }
  }

};
</script>

<style>
</style>
