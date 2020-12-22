<template>
  <div>
    <a-affix :offset-top="top" @change="handleAffixChange">
      <div style="background-color: #FFFFFF">
        <a-row class="title">
          <a-col :span="24">代码：<span>{{ storeCode }}</span></a-col>
          <a-col :span="24">名称：<span>{{ storeName }}</span></a-col>
          <a-col :span="8"><span>{{ elementName }}</span></a-col>
          <a-col :span="1"></a-col>
          <a-col :span="14" offset="1"><span v-html="regulationDescription"></span></a-col>
        </a-row>
        <a-divider></a-divider>
      </div>
    </a-affix>

    <a-row class="channelName">
      <a-col :span="24" style="color: #29D4D4;">客户深度调研</a-col>
    </a-row>
    <div v-for="questionSurvey in questionSurveyList" :key="questionSurvey.id">
      <a-row>
        <a-col :span="24">
          <span>{{ questionSurvey.seriesNo }} </span>
          <span v-html="questionSurvey.description"></span>
        </a-col>
      </a-row>
      <a-divider></a-divider>
      <div>
        <a-row v-if="questionSurvey.scoreItem =='综合分'">
          <a-col :span="24">
            <span style="font-size: 12px; font-style: italic; color: #0000FF">综合得分S=A分值*A占比%+B分值*B占比%+C分值*C占比%+D分值*D占比%+E分值*E占比%</span>
          </a-col>
        </a-row>
        <a-row style="margin: 24px 0;">
          <a-col :span="8">
            <span>本期得分：{{ questionSurvey.grade }}</span>
          </a-col>
          <a-col :span="8">
            <span>评分标准</span>
          </a-col>
        </a-row>
        <a-row v-if="questionSurvey.scoreItem == '综合分'">
          <a-col :xs="24" :sm="16">
            <a-table
              id="questionTable"
              :table-layout="tableLayout"
              :columns="regulationColumns1"
              :data-source="[ questionSurvey ]"
              :pagination="false"
              bordered>
            </a-table>
          </a-col>
        </a-row>
        <a-row v-if="questionSurvey.scoreItem != '综合分'">
          <a-col :xs="24" :sm="16">
            <a-table
              id="questionTable"
              :table-layout="tableLayout"
              :columns="regulationColumns2"
              :data-source="[ questionSurvey ]"
              :pagination="false"
              bordered>
            </a-table>
            <a-row>
              <a-col :span="24">备注：1代表选项A、2代表选项B、3代表选项C、4代表选项D、5代表选项E</a-col>
            </a-row>
          </a-col>
        </a-row>
      </div>
    </div>
  </div>
</template>
<script>
import moment from 'moment';
import { getStoreByCode } from '@/api/store';
import { getRegulationById } from '@/api/regulation';
import { getQuestionSurveyList } from '@/api/question';

export default {
  data () {
    const regulationColumns1 = [
      {
        title: 'A',
        dataIndex: 'answer1',
        key: 'answer1',
        align: 'center',
        width: '5%'
      },
      {
        title: 'B',
        dataIndex: 'answer2',
        key: 'answer2',
        align: 'center',
        width: '5%'
      },
      {
        title: 'C',
        dataIndex: 'answer3',
        key: 'answer3',
        align: 'center',
        width: '5%'
      },
      {
        title: 'D',
        dataIndex: 'answer4',
        key: 'answer4',
        align: 'center',
        width: '5%'
      },
      {
        title: 'E',
        dataIndex: 'answer5',
        key: 'answer5',
        align: 'center',
        width: '5%'
      },
      {
        title: '计分答案项',
        dataIndex: 'scoreItem',
        key: 'scoreItem',
        align: 'center',
        width: '20%'
      },
      {
        title: '公式号',
        dataIndex: 'formula',
        key: 'formula',
        align: 'center',
        width: '15%'
      },
      {
        title: '优秀',
        dataIndex: 'excellent',
        key: 'excellent',
        align: 'center',
        width: '10%'
      },
      {
        title: '优良',
        dataIndex: 'good',
        key: 'good',
        align: 'center',
        width: '10%'
      },
      {
        title: '达标',
        dataIndex: 'standard',
        key: 'standard',
        align: 'center',
        width: '10%'
      },
      {
        title: '薄弱',
        dataIndex: 'weak',
        key: 'weak',
        align: 'center',
        width: '10%'
      }
    ];
    const regulationColumns2 = [
      {
        title: '计分答案项',
        dataIndex: 'scoreItem',
        key: 'scoreItem',
        align: 'center',
        width: '25%'
      },
      {
        title: '公式号',
        dataIndex: 'formula',
        key: 'formula',
        align: 'center',
        width: '15%'
      },
      {
        title: '优秀',
        dataIndex: 'excellent',
        key: 'excellent',
        align: 'center',
        width: '15%'
      },
      {
        title: '优良',
        dataIndex: 'good',
        key: 'good',
        align: 'center',
        width: '15%'
      },
      {
        title: '达标',
        dataIndex: 'standard',
        key: 'standard',
        align: 'center',
        width: '15%'
      },
      {
        title: '薄弱',
        dataIndex: 'weak',
        key: 'weak',
        align: 'center',
        width: '15%'
      }
    ];
    return {
      top: 0,
      tableLayout: 'fixed',
      title: '',
      period: '',
      storeCode: '',
      storeName: '',
      elementName: '',
      regulationId: '',
      regulationDescription: '',
      questionSurveyList: [],
      regulationColumns1,
      regulationColumns2
    };
  },
  created () {
    this.initialData();
  },
  methods: {
    async initialData () {
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
        this.getQuestionSurveyList({
          period: this.period,
          store_code: this.storeCode,
          regulation_id: this.regulationId
        });
      }
    },
    getQuestionSurveyList (params = {}) {
      getQuestionSurveyList(params).then(res => {
        this.questionSurveyList = res.resData;
      });
    },
    handleAffixChange (affixed) {
      console.log(affixed);
    }
  }
};
</script>
<style lang="less" scoped>
@import './Regulation.less';
</style>
