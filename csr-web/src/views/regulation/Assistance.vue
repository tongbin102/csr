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
      <a-col :span="24">服务助手考核 {{ questionAssistance.seriesNo }}</a-col>
    </a-row>
    <a-row>
      <a-col :span="24">分析要点：{{ questionAssistance.analysisPoint }}</a-col>
      <a-col :span="24">KPI指标：{{ questionAssistance.kpi }}</a-col>

      <a-col :span="24">
        KPI说明：
        <span v-html="questionAssistance.kpiDescription"></span>
      </a-col>
    </a-row>
    <a-row style="margin: 24px 0; font-weight: bold;">
      <a-col :span="24"><span style="color: #FF3030">评分结果：{{ questionAssistance.grade }}</span></a-col>
    </a-row>
    <a-row>
      <a-col><span>评分标准</span></a-col>
    </a-row>
    <a-table :columns="standardColumns" :data-source="[questionAssistance]" :pagination="false" bordered>
    </a-table>
  </div>
</template>
<script>
import moment from 'moment';
import { getRegulationById } from '@/api/regulation';
import { getQuestionAssistanceList } from '@/api/question';

export default {
  data () {
    const standardColumns = [
      {
        title: '优秀',
        dataIndex: 'excellent',
        key: 'excellent',
        align: 'center'
      },
      {
        title: '优良',
        dataIndex: 'good',
        key: 'good',
        align: 'center'
      },
      {
        title: '达标',
        dataIndex: 'standard',
        key: 'standard',
        align: 'center'
      },
      {
        title: '薄弱',
        dataIndex: 'weak',
        key: 'weak',
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
      questionAssistance: {},
      questionAssistanceList: [],
      standardColumns
    };
  },
  created () {
    this.initialData();
  },
  methods: {
    initialData () {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeCode = this.$route.query.store_code;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        this.getRegulationById();
        this.getQuestionAssistanceList({
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
    getQuestionAssistanceList (params = {}) {
      getQuestionAssistanceList(params).then(res => {
        this.questionAssistanceList = res.resData;
        if (this.questionAssistanceList) {
          this.questionAssistance = this.questionAssistanceList[0];
        }
      });
    }
  }
};
</script>
<style lang="less" scoped>
@import './Regulation.less';
</style>
