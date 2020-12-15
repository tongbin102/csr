<template>
  <div>
    <a-affix :offset-top="top" @change="handleAffixChange">
      <div style="background-color: #FFFFFF">
        <a-row class="title">
          <a-col :span="24">代码：<span>{{ storeCode }}</span></a-col>
          <a-col :span="24">名称：<span>{{ storeName }}</span></a-col>
          <a-col :span="8"><span>{{ elementName }}</span></a-col>
          <a-col :span="16"><span v-html="regulationDescription"></span></a-col>
        </a-row>
        <a-divider></a-divider>
      </div>
    </a-affix>
    <a-row class="channelName">
      <a-col :span="8" style="color: #29D4D4;">过程监控{{ questionMonitor.seriesNo }}</a-col>
      <a-col :span="12" :offset="4">区域经理评分：{{ questionMonitor.grade }}</a-col>
    </a-row>
    <div class="suggestion">
      <a-row>
        <a-col>提报材料要求</a-col>
      </a-row>
      <a-row class="detail">
        <a-col :span="24">
          <span v-html="questionMonitor.suggestion"></span>
        </a-col>
      </a-row>
    </div>
    <div class="description">
      <a-row>
        <a-col>相关说明</a-col>
      </a-row>
      <a-row class="detail">
        <a-col :span="24">
          <span v-html="questionMonitor.description"></span>
        </a-col>
      </a-row>
    </div>
    <div>
      <a-row>
        <a-col>评分标准</a-col>
      </a-row>
      <a-table :columns="standardColumns" :data-source="standardData" :pagination="false" bordered>
        <template slot="standard" slot-scope="text, record">
          <span v-html="record.standard" style="text-align: left;"></span>
        </template>
      </a-table>
    </div>
  </div>
</template>
<script>
import moment from 'moment';
import { getStoreByCode } from '@/api/store';
import { getRegulationById } from '@/api/regulation';
import { getQuestionMonitorList } from '@/api/question';

export default {
  data () {
    const standardColumns = [
      {
        title: '等级',
        dataIndex: 'grade',
        key: 'grade',
        align: 'center',
        width: '10%'
      },
      {
        title: '标准',
        // slots: { title: 'standardTitle' },
        dataIndex: 'standard',
        key: 'standard',
        scopedSlots: { customRender: 'standard' },
        align: 'left',
        width: '90%'
      }
    ];
    return {
      top: 0,
      title: '',
      period: '',
      storeCode: '',
      storeName: '',
      elementName: '',
      regulationId: '',
      regulationDescription: '',
      questionMonitor: {},
      questionMonitorList: [],
      standardColumns,
      standardData: []
    };
  },
  created () {
    this.initialData();
  },
  methods: {
    async initialData (regulationId) {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeCode = this.$route.query.store_code;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        await getStoreByCode(this.storeCode).then(res => {
          // console.log(res);
          if (res.resCode === 200) {
            this.storeName = res.resData.name;
          }
        });
        await getRegulationById(this.regulationId).then(res => {
          if (res.resCode === 200) {
            const regulation = res.resData;
            this.elementName = regulation.elementCode.split(';')[1];
            this.regulationDescription = regulation.description;
          }
        });
        this.getQuestionMonitorList({
          period: this.period,
          store_code: this.storeCode,
          regulation_id: this.regulationId
        });
      }
    },
    getQuestionMonitorList (params = {}) {
      getQuestionMonitorList(params).then(res => {
        this.questionMonitorList = res.resData;
        if (this.questionMonitorList) {
          this.questionMonitor = this.questionMonitorList[0];
          this.standardData = [
            {
              grade: '优秀',
              standard: this.questionMonitor.excellent
            },
            {
              grade: '优良',
              standard: this.questionMonitor.good
            },
            {
              grade: '达标',
              standard: this.questionMonitor.standard
            },
            {
              grade: '薄弱',
              standard: this.questionMonitor.weak
            }
          ];
        }
      });
    }
  }
};
</script>
<style lang="less" scoped>
@import './Regulation.less';
</style>
