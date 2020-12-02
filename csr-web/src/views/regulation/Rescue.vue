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
    <a-row style="margin: 24px 0; font-weight: bold;">
      <a-col :span="24">道路救援</a-col>
    </a-row>
    <a-row>
      <a-col :span="24">分析要点：{{ questionRescue.analysisPoint }}</a-col>
      <a-col :span="24">KPI指标：{{ questionRescue.kpi }}</a-col>
      <a-col :span="24">
        KPI说明：
        <span v-html="questionRescue.kpiDescription"></span>
      </a-col>
    </a-row>
    <a-row style="margin: 24px 0; font-weight: bold;">
      <a-col :span="24"><span style="color: #FF3030">评分结果：</span><span>{{ questionRescue.score }}</span></a-col>
    </a-row>
    <a-row>
      <a-col><span>评分标准</span></a-col>
    </a-row>
    <a-table :columns="standardColumns" :data-source="[questionRescue]" :pagination="false" bordered>
    </a-table>
  </div>
</template>
<script>
import moment from 'moment';
import { getRegulationById } from '@/api/regulation';
import { getQuestionRescueList } from '@/api/question';

export default {
  data () {
    const standardColumns = [
      {
        title: '不达标后扣分',
        dataIndex: 'deduct',
        key: 'deduct',
        align: 'center'
      }
    ];
    return {
      title: '',
      period: '',
      storeCode: '',
      elementName: '',
      regulationId: '',
      regulationDescription: '',
      questionRescue: {},
      questionRescueList: [],
      standardColumns
    };
  },
  mounted () {
    this.initialData();
  },
  methods: {
    initialData () {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeCode = this.$route.query.store_code;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        this.getRegulationById();
        this.getQuestionRescueList({
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
    getQuestionRescueList (params = {}) {
      getQuestionRescueList(params).then(res => {
        this.questionRescueList = res.resData;
        if (this.questionRescueList) {
          this.questionRescue = this.questionRescueList[0];
          this.questionRescue = this.questionRescueList[0];
          // console.log(this.questionRescue);
        }
      });
    }
  }
};
</script>
<style lang="less" scoped>
@import './Regulation.less';
</style>
