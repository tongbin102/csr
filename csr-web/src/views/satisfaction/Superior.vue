<template>
  <div>
    <a-row>
      <a-col :span="12">{{ title }}</a-col>
      <a-col :span="12"><span style="float:right">评估期：{{ month }}</span></a-col>
    </a-row>

    <div class="content">
      <a-table :columns="scoreColumns" :data-source="scoreData" :pagination="false" :loading="scoreLoading">
        <template slot="score" slot-scope="text, record, index">
          <span v-if="index === 0">{{ storeName }}总得分：{{ record.score }}</span>
          <a-button class="childLink" type="link" v-else @click="handleClickStore(record.storeId)">
            <span>{{ record.storeName }}得分：{{ record.score }}</span>
          </a-button>
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
          <span class="increase" v-if="record.scoreDiff > 0">{{ '提高+' + record.scoreDiff + '分' }}</span>
          <span class="hold" v-if="record.scoreDiff === 0">持平</span>
          <span class="decrease" v-if="record.scoreDiff < 0">{{ '降低' + record.scoreDiff + '分' }}</span>
        </template>
      </a-table>
      <div class="channelInfo">
        <a-row class="channelTitle">
          <a-col :span="12">分渠道得分</a-col>
          <a-col :span="12">
            <a-button class="channelAnalysisLink" type="link" style="float: right;" @click="handleClickChannelAnalysis">
              <span>趋势分析</span>
            </a-button>
          </a-col>
        </a-row>
        <a-table :columns="scoreChannelColumns" :data-source="scoreChannelData" :pagination="false" :loading="scoreChannelLoading">
          <template slot="period" slot-scope="text, record">
            <span>{{ record.name }}</span>
          </template>
          <span slot="periodTitle"></span>
        </a-table>
      </div>
      <div class="factorInfo">
        <a-row class="factorTitle">
          <a-col :span="12">分因子得分</a-col>
          <a-col :span="12">
            <a-button class="factorAnalysisLink" type="link" style="float: right;" @click="handleClickFactorAnalysis">
              <span>趋势分析</span>
            </a-button>
          </a-col>
        </a-row>
        <a-table :columns="scoreFactorColumns" :data-source="scoreFactorData" :pagination="false" :loading="scoreFactorLoading">
          <template slot="period" slot-scope="text, record">
            <span>{{ record.name }}</span>
          </template>
          <span slot="customTitle"></span>
        </a-table>

      </div>
    </div>

  </div>
</template>
<script>
import moment from 'moment';
import { getScoreInfo, getScoreChannelInfo, getScoreFactorInfo } from '@/api/score';
import { getChannelList1 } from '@/api/channel';
import { getAllFactor } from '@/api/factor';
import { getStoreById } from '@/api/store';

export default {
  data () {
    return {
      title: '',
      scopeId: 5,
      id: this.$route.query.id,
      storeName: '',
      month: moment().add('month', 0).format('yyyy年MM月'),
      period: moment().add('month', 0).format('yyyyMM'),
      lastPeriod: moment().subtract(1, 'month').format('yyyyMM'),
      scoreLoading: false,
      scoreChannelLoading: false,
      scoreFactorLoading: false,
      scoreColumns: [
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
      ],
      scoreData: [],
      scoreChannelColumns: [],
      scoreChannelData: [],
      scoreFactorColumns: [],
      scoreFactorData: []
    };
  },
  mounted () {
    this.initialData();
    this.fetchColumns();
    this.fetchScoreData({
      scope_id: this.scopeId,
      parent_id: this.id,
      current_period: this.period,
      last_period: this.lastPeriod
    });
    this.fetchScoreChannelData({
      scope_id: this.scopeId,
      store_id: this.id,
      current_period: this.period,
      last_period: this.lastPeriod
    });
    this.fetchScoreFactorData({
      scope_id: this.scopeId,
      store_id: this.id,
      current_period: this.period,
      last_period: this.lastPeriod
    });
  },
  methods: {
    initialData () {
      getStoreById(this.id).then(res => {
        const store = res.resData;
        this.title = '所属区域：' + store.name;
        this.storeName = store.name;
      });
    },
    fetchColumns (params = {}) {
      getChannelList1(params).then(res => {
        const scoreChannelColumns = [];
        scoreChannelColumns.push({
          dataIndex: 'period',
          key: 'period',
          slots: { title: 'periodTitle' },
          width: '20%'
        });
        const channelList = res.resData;
        channelList.forEach((channel) => {
          scoreChannelColumns.push({
            title: channel.name,
            dataIndex: channel.id,
            key: channel.id,
            width: '20%'
          });
        });
        this.scoreChannelColumns = scoreChannelColumns;
      });
      getAllFactor(params).then(res => {
        const scoreFactorColumns = [];
        scoreFactorColumns.push({
          dataIndex: 'period',
          key: 'period',
          slots: { title: 'periodTitle' },
          width: '10%'
        });
        const factorList = res.resData;
        factorList.forEach((factor) => {
          scoreFactorColumns.push({
            title: factor.name,
            dataIndex: factor.id,
            key: factor.id,
            width: '15%'
          });
        });
        this.scoreFactorColumns = scoreFactorColumns;
      });
    },
    fetchScoreData (params = {}) {
      this.scoreLoading = true;
      getScoreInfo(params).then(res => {
        this.scoreLoading = false;
        this.scoreData = [...res.resData.totalScoreList, ...res.resData.childScoreList];
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
    handleClickStore (id) {
      this.$router.push({
        path: '/satisfaction/store',
        query: { id: id }
      });
    },
    handleClickChannelAnalysis () {
      console.log('click channel analysis');
    },
    handleClickFactorAnalysis () {
      console.log('click factor analysis');
    }
  }

};
</script>
<style lang="less" scoped>
.content {
  border: 1px solid #999;
  padding: 0;
}

.childLink span,
.channelAnalysisLink span,
.factorAnalysisLink span {
  text-decoration: underline;
}

.channelInfo {
  padding-top: 20px;
}

.channelInfo .channelTitle {
  margin: 0 16px;
  border-bottom: 1px solid;
}

.factorInfo {
  padding-top: 20px;
}

.factorInfo .factorTitle {
  margin: 0 16px;
  border-bottom: 1px solid;
}

.ant-table .ant-table-title .ant-row {
  border-bottom: 1px solid #999;
}

.ant-table .ant-table-title .ant-row .ant-col {
  font-size: 20px;
  font-weight: bold;
}

span.increase {
  color: #00ff00;
}

span.decrease {
  color: #ff0000;
}

span.hold {
  color: #000000;
}
</style>
