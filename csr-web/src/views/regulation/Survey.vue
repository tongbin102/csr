<template>
  <div>
    <a-row class="title">
      <a-col :span="8"><span>{{ elementName + " ——" }}</span></a-col>
      <a-col :span="1"></a-col>
      <a-col :span="14" offset="1"><span v-html="regulationDescription"></span></a-col>
    </a-row>

    <a-divider></a-divider>

    <a-row class="channelName">
      <a-col :span="8" style="color: #29D4D4">客户深度调研</a-col>
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
            <span style="font-size: 12px; font-style: italic; color: #0000FF">综合得分S=答案1分值*答案1占比%+答案2分值*答案2占比%+答案3分值*答案3占比%+答案4分值*答案4占比%+答案5分值*答案5占比%</span>
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
            <a-table :columns="regulationColumns1" :data-source="[ questionSurvey ]" :pagination="false" bordered>
            </a-table>
          </a-col>
        </a-row>
        <a-row v-if="questionSurvey.scoreItem != '综合分'">
          <a-col :xs="24" :sm="16">
            <a-table table-layout="fixed" :columns="regulationColumns2" :data-source="[ questionSurvey ]" :pagination="false" bordered>
            </a-table>
          </a-col>
        </a-row>
      </div>
    </div>
  </div>
</template>
<script>
import moment from 'moment';
import { getRegulationById } from '@/api/regulation';
import { getQuestionSurveyList } from '@/api/question';

export default {
  data () {
    const regulationColumns1 = [
      {
        title: '答案1',
        dataIndex: 'answer1',
        key: 'answer1'
      },
      {
        title: '答案2',
        dataIndex: 'answer2',
        key: 'answer2'
      },
      {
        title: '答案3',
        dataIndex: 'answer3',
        key: 'answer3'
      },
      {
        title: '答案4',
        dataIndex: 'answer4',
        key: 'answer4'
      },
      {
        title: '答案5',
        dataIndex: 'answer5',
        key: 'answer5'
      },
      {
        title: '积分答案项',
        dataIndex: 'scoreItem',
        key: 'scoreItem'
      },
      {
        title: '公式号',
        dataIndex: 'formula',
        key: 'formula'
      },
      {
        title: '优秀',
        dataIndex: 'excellent',
        key: 'excellent'
      },
      {
        title: '优良',
        dataIndex: 'good',
        key: 'good'
      },
      {
        title: '达标',
        dataIndex: 'standard',
        key: 'standard'
      },
      {
        title: '薄弱',
        dataIndex: 'weak',
        key: 'weak'
      }
    ];
    const regulationColumns2 = [
      {
        title: '积分答案项',
        dataIndex: 'scoreItem',
        key: 'scoreItem'
      },
      {
        title: '公式号',
        dataIndex: 'formula',
        key: 'formula'
      },
      {
        title: '优秀',
        dataIndex: 'excellent',
        key: 'excellent'
      },
      {
        title: '优良',
        dataIndex: 'good',
        key: 'good'
      },
      {
        title: '达标',
        dataIndex: 'standard',
        key: 'standard'
      },
      {
        title: '薄弱',
        dataIndex: 'weak',
        key: 'weak'
      }
    ];
    return {
      title: '',
      period: '',
      storeCode: '',
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
    initialData () {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeCode = this.$route.query.store_code;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        this.getRegulationById();
        this.getQuestionSurveyList({
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
    getQuestionSurveyList (params = {}) {
      getQuestionSurveyList(params).then(res => {
        this.questionSurveyList = res.resData;
      });
    }
  }
};
</script>
<style lang="less" scoped>
@import './Regulation.less';
</style>
