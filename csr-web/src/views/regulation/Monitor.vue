<template>
  <div>
    <!-- <a-breadcrumb separator="">
      <a-breadcrumb-item>{{ elementName }}</a-breadcrumb-item>
      <a-breadcrumb-separator>——</a-breadcrumb-separator>
      <a-breadcrumb-item>{{ regulationDescription }}</a-breadcrumb-item>
    </a-breadcrumb> -->
    <a-row class="title">
      <a-col :span="8"><span>{{ elementName + " —— " }}</span></a-col>
      <a-col :span="16"><span v-html="regulationDescription"></span></a-col>
    </a-row>
    <a-divider></a-divider>
    <a-row style="margin: 24px 0; font-weight: bold; color: #09CDFF;">
      <a-col :span="8">过程监控{{ questionMonitor.seriesNo }}</a-col>
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
      title: '',
      period: '',
      storeCode: '',
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
    initialData (regulationId) {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeCode = this.$route.query.store_code;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        this.getRegulationById();
        this.getQuestionMonitorList({
          period: this.period,
          store_code: this.storeCode,
          regulation_id: this.regulationId
        });
      }
    },
    getRegulationById () {
      getRegulationById(this.regulationId).then(res => {
        const regulation = res.resData;
        this.elementName = regulation.elementCode.split(';')[1];
        this.regulationDescription = regulation.description;
      });
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
