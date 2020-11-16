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
    <a-row style="margin: 24px 0; font-weight: bold; color: #09CDFF;">
      <a-col :span="8">过程监控</a-col>
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
import { getRegulationVoById } from '@/api/regulation';
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
      storeId: '',
      elementId: '',
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
    this.period = moment().add('month', 0).format('yyyyMM');
    this.storeId = this.$route.query.store_id;
    this.regulationId = this.$route.query.regulation_id;
    if (this.regulationId) {
      this.getTitle();
      this.getQuestionMonitorList({
        period: this.period,
        store_id: this.storeId,
        regulation_id: this.regulationId
      });
    }
  },
  methods: {
    initialData (regulationId) {
      this.period = moment().add('month', 0).format('yyyyMM');
      this.storeId = this.$route.query.store_id;
      this.regulationId = this.$route.query.regulation_id;
      if (this.regulationId) {
        this.getTitle();
        this.getQuestionMonitorList({
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
.suggestion {
  margin: 24px 0;
}

.suggestion .detail {
  border: 1px solid;
}

.description {
  margin: 24px 0;
}

.description .detail {
  border: 1px solid;
}

</style>
