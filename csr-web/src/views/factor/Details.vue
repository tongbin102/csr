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

      <a-table :columns="elementScoreColumns" :data-source="elementScoreData" :pagination="false" :loading="elementScoreLoading">
      </a-table>
    </a-tab-pane>
  </a-tabs>
</template>

<script>
import moment from 'moment';
import merge from 'webpack-merge';
import { getAllFactor } from '@/api/factor';
import { getScoreFactorInfoForFactor } from '@/api/score';
import { getAllChannel } from '@/api/channel';

export default {
  data () {
    return {
      storeId: this.$route.query.store_id,
      factorId: this.$route.query.factor_id,
      month: moment().add('month', 0).format('yyyy年MM月'),
      period: moment().add('month', 0).format('yyyyMM'),
      lastPeriod: moment().subtract(1, 'month').format('yyyyMM'),
      activeKey: this.factorId,
      panes: [],
      scoreFactorLoading: false,
      scoreFactorColumns: [],
      scoreFactorData: [],
      elementScoreLoading: false,
      elementScoreColumns: [],
      elementScoreData: []
    };
  },
  created () {
    this.initialData();
    this.tabActiveKey = this.$route.query.factor_id;

    this.fetchFactorColumns();
    this.fetchFactorData({
      store_id: this.storeId,
      factor_id: this.factorId,
      current_period: this.period,
      last_period: this.lastPeriod
    });
    this.fetchElementScoreColumns();

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
    fetchFactorData (params = {}) {
      this.scoreLoading = true;
      getScoreFactorInfoForFactor(params).then(res => {
        this.scoreLoading = false;
        // console.log(res.resData);
        this.scoreFactorData = res.resData.scoreFactorList;
      });
    },
    fetchFactorColumns () {
      const scoreFactorColumns = [
        {
          dataIndex: 'score',
          key: 'score',
          slots: { title: 'scoreTitle' },
          scopedSlots: { customRender: 'score' }
        },
        {
          title: '全国排名',
          dataIndex: 'rankCountry',
          key: 'rankCountry',
          scopedSlots: { customRender: 'rankCountry' }
        },
        {
          title: '区域排名',
          dataIndex: 'rankScope',
          key: 'rankScope',
          scopedSlots: { customRender: 'rankScope' }
        },
        {
          title: '环比上期',
          dataIndex: 'diff',
          key: 'diff',
          scopedSlots: { customRender: 'diff' }
        }
      ];
      this.scoreFactorColumns = scoreFactorColumns;
    },
    fetchElementScoreColumns () {
      getAllChannel().then(res => {
        const elementScoreColumns = [
          {
            title: '要素',
            dataIndex: 'element',
            key: 'element'
          },
          {
            title: '细则',
            dataIndex: 'specific',
            key: 'specific'
          },
          {
            title: '得分',
            dataIndex: 'score',
            key: 'score',
            children: [
              {
                title: '综合得分',
                dataIndex: 'comprehensiveScore',
                key: 'comprehensiveScore',
                children: [
                  {
                    title: '考核项',
                    dataIndex: 'evaluateItem',
                    key: 'evaluateItem'
                  },
                  {
                    title: '加分项',
                    dataIndex: 'extraItem',
                    key: 'extraItem'
                  }
                ]
              },
              {
                title: '基础考核分',
                dataIndex: 'basicScore',
                key: 'basicScore'
              },
              {
                title: '附加分',
                dataIndex: 'extraScore',
                key: 'extraScore'
              }
            ]
          }
        ];
        const channelList = res.resData;
        const basicScoreChildren = [];
        channelList.forEach((channel) => {
          // console.log(elementScoreColumns[2].children[1]);
          basicScoreChildren.push({
            title: channel.name,
            dataIndex: 'basic' + channel.id,
            key: 'basic' + channel.id
          });
        });
        elementScoreColumns[2].children[1].children = basicScoreChildren;
        const extraScoreChildren = [];
        channelList.forEach((channel) => {
          // console.log(elementScoreColumns[2].children[1]);
          extraScoreChildren.push({
            title: channel.name,
            dataIndex: 'extra' + channel.id,
            key: 'extra' + channel.id
          });
        });
        elementScoreColumns[2].children[2].children = extraScoreChildren;
        console.log(elementScoreColumns);
        this.elementScoreColumns = elementScoreColumns;
      });
    }
  }

};
</script>

<style>
</style>
