<template>
  <div>
    <!-- <a-breadcrumb separator="">
      <a-breadcrumb-item>{{ elementName }}</a-breadcrumb-item>
      <a-breadcrumb-separator>——</a-breadcrumb-separator>
      <a-breadcrumb-item>{{ regulationDescription }}</a-breadcrumb-item>
    </a-breadcrumb> -->
    <a-row>
      <a-col :span="8">
        <span>{{ title }}</span>
      </a-col>
    </a-row>
    <a-divider></a-divider>
    <a-row style="margin: 24px 0; font-weight: bold;">
      <a-col :span="24">数据准确性</a-col>
    </a-row>
    <a-row>
      <a-col :span="24">分析要点：{{ questionData.analysisPoint }}</a-col>
      <a-col :span="24">KPI指标：{{ questionData.kpi }}</a-col>
      <a-col :span="24">KPI说明：<span v-html="questionData.kpiDescription"></span></a-col>
    </a-row>
    <a-row style="margin: 24px 0; font-weight: bold;">
      <a-col :span="24"><span style="color: #FF3030">评分结果：</span><span>{{ questionData.score }}</span></a-col>
    </a-row>
    <a-row>
      <a-col><span>评分标准</span></a-col>
    </a-row>
    <a-table :columns="standardColumns" :data-source="[questionData]" :pagination="false" bordered>
    </a-table>
  </div>
</template>
<script>
import moment from 'moment';
import { getRegulationVoById } from '@/api/regulation';
import { getQuestionDataList } from '@/api/question';

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
      storeId: '',
      elementId: '',
      regulationId: '',
      questionData: {},
      questionDataList: [],
      standardColumns
    };
  },
  mounted () {
    this.initialData();
  },
  methods: {
    initialData () {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeId = this.$route.query.store_id;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        this.getTitle();
        this.getQuestionDataList({
          period: this.period,
          store_id: this.storeId,
          regulation_id: this.regulationId
        });
      }
    },
    getTitle () {
      getRegulationVoById(this.regulationId).then(res => {
        const regulationVo = res.resData;
        this.title = regulationVo.elementName + '——' + regulationVo.description;
      });
    },
    getQuestionDataList (params = {}) {
      getQuestionDataList(params).then(res => {
        this.questionDataList = res.resData;
        if (this.questionDataList) {
          this.questionData = this.questionDataList[0];
          this.questionData = this.questionDataList[0];
          // console.log(this.questionData);
        }
      });
    }
  }
};
</script>

<style>
</style>
