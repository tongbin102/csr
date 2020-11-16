<template>
  <div>
    <a-row>
      <a-col :span="24">满意度趋势分析</a-col>
    </a-row>
    <div class="content">
      <a-row>
        <a-col :span="12">分渠道得分</a-col>
      </a-row>

      <a-divider></a-divider>

      <a-table :columns="scoreFactorColumns" :data-source="scoreFactorData" :pagination="false" :loading="scoreFactorLoading">
      </a-table>
      <a-row>
        <a-col :span="12"><bar :data="barData.barTotal" title="总分" /></a-col>
        <a-col :span="12" v-for="factor in factorList1" :key="factor.id">
          <bar :data="barData['barFactor' + factor.id]" :title="factor.name" />
        </a-col>
      </a-row>

    </div>
  </div>
</template>

<script>
import moment from 'moment';
import Bar from '@/components/Charts/Bar';
import { getScoreFactorInfoByPeriods } from '@/api/score';
import { getAllFactor } from '@/api/factor';

export default {
  components: {
    Bar
  },
  data () {
    return {
      period: '',
      scopeId: '',
      storeId: '',
      factorList1: [],
      scoreFactorLoading: false,
      scoreFactorColumns: [],
      scoreFactorData: [],
      barData: {}
    };
  },
  mounted () {
    this.initialData();
  },
  methods: {
    initialData () {
      this.getScoreFactorColumn();
      this.period = moment().add('month', 0).format('yyyyMM');
      this.scopeId = this.$route.query.scope_id;
      this.storeId = this.$route.query.store_id;
      const beginPeriod = moment().subtract(5, 'month').format('yyyyMM');
      this.getScoreFactorInfoByPeriods({
        begin_period: beginPeriod,
        end_period: this.period,
        scope_id: this.scopeId,
        store_id: this.storeId
      });
    },
    getScoreFactorInfoByPeriods (params = {}) {
      this.scoreFactorLoading = true;
      getScoreFactorInfoByPeriods(params).then(res => {
        const scoreFactorInfo = res.resData;
        this.scoreFactorLoading = false;
        this.scoreFactorData = [scoreFactorInfo[scoreFactorInfo.length - 1].scoreFactor];

        const barTotal = [];
        scoreFactorInfo.forEach(scoreFactor => {
          barTotal.push({
            x: scoreFactor.period,
            y: scoreFactor.score || 0
          });
        });
        this.barData.barTotal = barTotal;

        this.factorList1.forEach(factor => {
          const barFactor = [];
          // console.log(scoreFactorInfo);

          scoreFactorInfo.forEach(info => {
            const scoreFactor = info.scoreFactor;
            barFactor.push({
              x: info.period,
              // eslint-disable-next-line no-prototype-builtins
              y: scoreFactor.hasOwnProperty([factor.id]) ? scoreFactor[factor.id] : 0
            });
          });
          this.barData['barFactor' + factor.id] = barFactor;
        });
          // console.log(this.barData);
      });
    },
    getScoreFactorColumn (params = {}) {
      getAllFactor(params).then(res => {
        this.factorList1 = res.resData;
        const scoreFactorColumns = [];
        res.resData.forEach((factor) => {
          scoreFactorColumns.push({
            title: factor.name,
            dataIndex: factor.id,
            key: factor.id,
            width: '16%',
            align: 'center'
          });
        });
        this.scoreFactorColumns = scoreFactorColumns;
      });
    }
  }

};
</script>

<style lang="less" scoped>
.content {
  border: 1px solid;
}
</style>
