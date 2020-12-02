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
    <div class="complain-block" v-for="complain in questionComplainList" :key="complain.id">
      <a-row class="complain-title">
        <a-col :span="12">投诉描述</a-col>
        <a-col :span="12">归属类别：{{ complain.factorCode }}</a-col>
      </a-row>
      <a-row class="complain-content">
        <a-col :span="24">
          <span>{{ complain.description }}</span>
        </a-col>
      </a-row>
    </div>
  </div>
</template>
<script>
import moment from 'moment';
import { getRegulationById } from '@/api/regulation';
import { getQuestionComplainList } from '@/api/question';

export default {
  data () {
    return {
      title: '',
      period: '',
      storeCode: '',
      elementName: '',
      factorCode: '',
      regulationId: '',
      regulationDescription: '',
      questionComplainList: []
    };
  },
  mounted () {
    this.initialData();
  },
  methods: {
    async initialData () {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeCode = this.$route.query.store_code;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        await getRegulationById(this.regulationId).then(res => {
          const regulation = res.resData;
          this.factorCode = regulation.elementCode.split(';')[0];
          this.elementName = regulation.elementCode.split(';')[1];
          this.regulationDescription = regulation.description;
        });

        this.getQuestionComplainList({
          period: this.period,
          store_code: this.storeCode,
          factor_code: this.factorCode
        });
      }
    },
    getQuestionComplainList (params = {}) {
      getQuestionComplainList(params).then(res => {
        this.questionComplainList = res.resData;
      });
    }
  }
};
</script>
<style lang="less" scoped>
// @import './Regulation.less';
.complain-block {
  margin: 20px 0;
}
.complain-title {
  height: 30px;
  line-height: 30px;;
  font-weight: bold;
}

.complain-content {
  border: 1px solid;
  padding: 10px;
}
</style>
