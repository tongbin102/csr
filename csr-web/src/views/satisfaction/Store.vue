<template>
  <div>
    <a-row>
      <a-col :span="12">{{ title }}</a-col>
      <a-col :span="12"><span style="float:right">评估期：{{ month }}</span></a-col>
    </a-row>

    <div class="content">
      <a-table
        :columns="scoreColumns"
        :data-source="scoreData"
        :pagination="false"
        :loading="scoreLoading"
        :customHeaderRow="setCustomHeaderRow"
        :customRow="setCustomRow">
        <template slot="score" slot-scope="text, record, index">
          <span v-if="index === 0">{{ storeName }}总得分：{{ record.score }}</span>
          <a-button class="childLink" type="link" v-else @click="handleClickStore(record.factorId)">
            <span style="text-decoration: underline;">{{ record.factorName }}得分：{{ record.score }}</span>
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
        <a-table
          :columns="scoreChannelColumns"
          :data-source="scoreChannelData"
          :pagination="false"
          :loading="scoreChannelLoading"
          :customHeaderRow="setCustomHeaderRow"
          :customRow="setCustomRow">
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
        <a-table
          :columns="scoreFactorColumns"
          :data-source="scoreFactorData"
          :pagination="false"
          :loading="scoreFactorLoading"
          :customHeaderRow="setCustomHeaderRow"
          :customRow="setCustomRow">
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
import { getScoreFactorInfoForStore, getScoreChannelInfo, getScoreFactorInfo } from '@/api/score';
import { getChannelList1 } from '@/api/channel';
import { getAllFactor } from '@/api/factor';
import { getStoreById } from '@/api/store';

export default {
  data () {
    const scoreColumns = [
        {
          dataIndex: 'score',
          key: 'score',
          align: 'left',
          slots: { title: 'scoreTitle' },
          scopedSlots: { customRender: 'score' },
          customCell: function (record, index) {
            return {
              style: {
                padding: 0,
                width: '40%',
                fontSize: '10px'
              }
            };
          },
          customHeaderCell: function () {
            return {
              style: {
                padding: 0,
                fontSize: '10px'
              }
            };
          }
        },
        {
          title: '全国排名',
          dataIndex: 'rankCountry',
          key: 'rankCountry',
          align: 'center',
          scopedSlots: { customRender: 'rankCountry' },
          customCell: function (record, index) {
            return {
              style: {
                padding: 0,
                width: '20%',
                fontSize: '8px',
                color: record.rankCountryDiff > 0 ? '#31D582' : record.rankCountryDiff < 0 ? '#FF4B4B' : ''
              }
            };
          },
          customHeaderCell: function () {
            return {
              style: {
                padding: 0,
                fontSize: '10px'
              }
            };
          }
        },
        {
          title: '区域排名',
          dataIndex: 'rankScope',
          key: 'rankScope',
          scopedSlots: { customRender: 'rankScope' },
          customCell: function (record, index) {
            return {
              style: {
                padding: 0,
                width: '20%',
                fontSize: '8px',
                color: record.rankScopeDiff > 0 ? '#31D582' : record.rankScopeDiff < 0 ? '#FF4B4B' : ''
              }
            };
          },
          customHeaderCell: function () {
            return {
              style: {
                padding: 0,
                fontSize: '10px'
              }
            };
          }
        },
        {
          title: '环比上期',
          dataIndex: 'diff',
          key: 'diff',
          scopedSlots: { customRender: 'diff' },
          customCell: function (record, index) {
            return {
              style: {
                padding: 0,
                width: '20%',
                fontSize: '8px',
                color: record.scoreDiff > 0 ? '#31D582' : record.scoreDiff < 0 ? '#FF4B4B' : ''
              }
            };
          },
          customHeaderCell: function () {
            return {
              style: {
                padding: 0,
                fontSize: '10px'
              }
            };
          }
        }
      ];
    return {
      title: '',
      scopeId: 6,
      id: '',
      storeName: '',
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
      factorList: []
    };
  },
  mounted () {
    this.initialData();
    this.fetchColumns();
    this.fetchScoreData({
      scope_id: this.scopeId,
      store_id: this.id,
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
      this.id = this.$route.query.id;
      this.month = moment().add('month', 0).format('yyyy年MM月');
      this.period = moment().add('month', 0).format('yyyyMM');
      this.lastPeriod = moment().subtract(1, 'month').format('yyyyMM');

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
          customCell: function (record, index) {
            return {
              style: {
                padding: 0,
                width: '10%',
                fontSize: '10px'
              }
            };
          },
          customHeaderCell: function () {
            return {
              style: {
                padding: 0,
                fontSize: '8px'
              }
            };
          }
        });
        const channelList = res.resData;
        this.channelList = channelList;
        channelList.forEach((channel) => {
          scoreChannelColumns.push({
            title: channel.name,
            dataIndex: channel.id,
            key: channel.id,
            align: 'center',
            width: (90 / channelList.length) + '%',
            customCell: function (record, index) {
              return {
                style: {
                  padding: 0,
                  fontSize: '10px'
                }
              };
            },
            customHeaderCell: function () {
              return {
                style: {
                  padding: 0,
                  fontSize: '6px'
                }
              };
            }
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
          align: 'center',
          customCell: function (record, index) {
            return {
              style: {
                padding: 0,
                width: '10%',
                fontSize: '10px'
              }
            };
          },
          customHeaderCell: function () {
            return {
              style: {
                padding: 0,
                fontSize: '6px'
              }
            };
          }
        });
        const factorList = res.resData;
        this.factorList = factorList;
        factorList.forEach((factor) => {
          scoreFactorColumns.push({
            title: factor.name,
            dataIndex: factor.id,
            key: factor.id,
            align: 'center',
            customCell: function (record, index) {
              return {
                style: {
                  padding: 0,
                  width: (90 / factorList.length) + '%',
                  fontSize: '10px'
                }
              };
            },
            customHeaderCell: function () {
              return {
                style: {
                  padding: 0,
                  fontSize: '6px'
                }
              };
            }
          });
        });
        this.scoreFactorColumns = scoreFactorColumns;
      });
    },
    fetchScoreData (params = {}) {
      this.scoreLoading = true;
      getScoreFactorInfoForStore(params).then(res => {
        this.scoreLoading = false;
        this.scoreData = [...res.resData.totalScoreList, ...res.resData.scoreFactorList];
      });
    },
    setCustomHeaderRow (a, b, c) {
      return {
        style: {
          fontSize: '8px',
          height: '40px',
          lineHeight: '40px',
          wordWrap: 'break-word',
          wordBreak: 'normal'
        }
      };
    },
    setCustomRow (record) {
      return {
        style: {
          height: '40px',
          lineHeight: '40px'
          // borderBottom: 'none'
        }
      };
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
    handleClickStore (factorId) {
      this.$router.push({
        path: '/factor/Details',
        query: {
          store_id: this.id,
          factor_id: factorId
        }
      });
    },
    handleClickChannelAnalysis () {
      this.$router.push({
        path: '/analysis/channel',
        query: {
          scope_id: this.scopeId,
          store_id: this.id
        }
      });
    },
    handleClickFactorAnalysis () {
      this.$router.push({
        path: '/analysis/factor',
        query: {
          scope_id: this.scopeId,
          store_id: this.id
        }
      });
    }
  }

};
</script>
<style lang="less" scoped>
.content {
  border: 1px solid #999;
  padding: 0;
}

.childLink {
  padding: 6px;
  font-size: 8px;
  color: #47DADA;
}

.channelInfo, .factorInfo {
  padding-top: 20px;
}

.channelInfo .channelTitle,  .factorInfo .factorTitle {
  margin: 0 16px;
  border-bottom: 1px solid;
}

</style>
