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
import { getStoreByCode } from '@/api/store';
import { getRegulationById } from '@/api/regulation';
import { getQuestionComplainList } from '@/api/question';

export default {
  data () {
    return {
      top: 0,
      title: '',
      period: '',
      storeCode: '',
      storeName: '',
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
        await getStoreByCode(this.storeCode).then(res => {
          // console.log(res);
          if (res.resCode === 200) {
            this.storeName = res.resData.name;
          }
        });
        await getRegulationById(this.regulationId).then(res => {
          if (res.resCode === 200) {
            const regulation = res.resData;
            this.factorCode = regulation.elementCode.split(';')[0];
            this.elementName = regulation.elementCode.split(';')[1];
            this.regulationDescription = regulation.description;
          }
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
