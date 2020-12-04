<template>
  <div>
    <a-row>
      <a-col :span="24" style="font-weight: bold;">满意度趋势分析</a-col>
    </a-row>
    <div class="analysis-content">
      <a-row class="title">
        <a-col :span="6">
          <span>分因子得分</span>
        </a-col>
        <a-col :span="6" :offset="12">
          <span>趋势分析</span>
        </a-col>
      </a-row>

      <a-table
        id="scoreFactorTable"
        table-layout="fixed"
        :columns="scoreFactorColumns"
        :data-source="scoreFactorData"
        :pagination="false"
        :loading="scoreFactorLoading">
      </a-table>
      <a-row>
        <a-col :xs="24" :sm="12">
          <!-- <v-chart :options="chartOptions.total" :auto-resize="true"></v-chart> -->
          <div class="Echarts">
            <div id="totalChart" :style="{padding: '5px', width: '100%', height: '200px'}"></div>
          </div>
          <!-- <bar :data="barData.barTotal" title="总分" style="height:100px;" /> -->
          <!-- <mini-bar :data="barData.barTotal" /> -->
        </a-col>
        <a-col :xs="24" :sm="12" v-for="factor in factorList1" :key="factor.id">
          <div class="Echarts">
            <div :id="'factorChart'+ factor.id" :style="{padding: '5px', width: '100%', height: '200px'}"></div>
          </div>
          <!-- <bar :data="barData['barChannel' + channel.id]" :title="channel.name" :extend="barExtend" /> -->
        </a-col>
      </a-row>

    </div>
  </div>
</template>

<script>
import moment from 'moment';
import { getScoreFactorInfoByPeriods } from '@/api/score';
import { getAllFactor } from '@/api/factor';

export default {
  data () {
    const barColor = '#00CCFF';
    this.barExtend = {
      series: {
        label: { show: true, position: 'top' }
      }
    };
    return {
      period: '',
      scopeId: '',
      code: '',
      factorList1: [],
      scoreFactorLoading: false,
      scoreFactorColumns: [],
      scoreFactorData: [],
      barData: {},
      totalOptions: {},
      chartOptions: {},
      barColor,
      totalChart: {},
      factorChartList: []
    };
  },
  mounted () {
    this.initialData();
    window.onresize = () => {
      //  根据窗口大小调整曲线大小
      this.totalChart.resize();
      this.factorChartList.forEach(factorChart => {
        factorChart.resize();
      });
    };
  },
  methods: {
    initialData () {
      this.getScoreFactorColumn();
      this.period = moment().add('month', 0).format('yyyyMM');
      this.scopeId = this.$route.query.scope_id;
      this.code = this.$route.query.code;
      const beginPeriod = moment().subtract(5, 'month').format('yyyyMM');
      this.getScoreFactorInfoByPeriods({
        begin_period: beginPeriod,
        end_period: this.period,
        scope_id: this.scopeId,
        store_code: this.code
      });
    },
    getScoreFactorInfoByPeriods (params = {}) {
      this.scoreFactorLoading = true;
      getScoreFactorInfoByPeriods(params).then(res => {
        const scoreFactorInfo = res.resData;
        this.scoreFactorLoading = false;
        this.scoreFactorData = [scoreFactorInfo[scoreFactorInfo.length - 1].scoreFactor];

        const totalOptions = {
          title: {
            text: '总分',
            left: 'center',
            top: 0
          },
          tooltip: {
            formatter: ' {a}: ({c})'
          },
          grid: [
            { x: '14%', y: '14%', width: '76%', height: '76%' }
          ],
          xAxis: [
            {
              gridIndex: 0,
              type: 'category',
              axisLabel: {
                interval: 0 // 强制显示文字
              },
              data: []
            }
          ],
          yAxis: [
            {
              show: true,
              type: 'value',
              gridIndex: 0,
              splitLine: {
                show: true
              }
            }
          ],
          series: [
            {
              name: '得分',
              type: 'bar',
              xAxisIndex: 0,
              yAxisIndex: 0,
              label: {
                show: true,
                color: '#313131',
                position: 'inside'
              },
              itemStyle: {
                color: this.barColor
              },
              data: []
            }
          ]
        };
        const xAxisData = [];
        const seriesData = [];
        scoreFactorInfo.forEach(scoreFactor => {
          const month = moment(scoreFactor.period).format('M月');
          xAxisData.push({ value: month });
          seriesData.push([month, parseInt(scoreFactor.score) || null]);
        });
        totalOptions.xAxis[0].data = xAxisData;
        totalOptions.series[0].data = seriesData;
        this.chartOptions.total = totalOptions;
        this.initTotalChart();

        this.factorList1.forEach(factor => {
          const factorOptions = {
            title: {
              text: factor.name,
              left: 'center',
              top: 0
            },
            tooltip: {
              formatter: ' {a}: ({c})'
            },
            grid: [
              { x: '14%', y: '14%', width: '76%', height: '76%' }
            ],
            xAxis: [
              {
                gridIndex: 0,
                type: 'category',
                axisLabel: {
                  interval: 0 // 强制显示文字
                },
                data: []
              }
            ],
            yAxis: [
              {
                show: true,
                type: 'value',
                gridIndex: 0,
                splitLine: {
                  show: true
                }
              }
            ],
            series: [{
              name: '得分',
              type: 'bar',
              xAxisIndex: 0,
              yAxisIndex: 0,
              label: {
                show: true,
                color: '#313131',
                position: 'inside'
              },
              itemStyle: {
                color: this.barColor
              },
              data: []
            }]
          };
          const xAxisData = [];
          const seriesData = [];

          scoreFactorInfo.forEach(scoreFactor => {
            const month = moment(scoreFactor.period).format('M月');
            xAxisData.push({ value: month });
            seriesData.push([month, parseInt(scoreFactor.scoreFactor[factor.name]) || null]);
          });
          factorOptions.xAxis[0].data = xAxisData;
          factorOptions.series[0].data = seriesData;
          this.chartOptions['factorOptions' + factor.id] = factorOptions;
          this.initFactorChart(factor.id);
        });
      });
    },
    getScoreFactorColumn (params = {}) {
      getAllFactor(params).then(res => {
        this.factorList1 = res.resData;
        const scoreFactorColumns = [];
        res.resData.forEach((factor) => {
          scoreFactorColumns.push({
            title: factor.name,
            dataIndex: factor.name,
            key: factor.id,
            width: '16%',
            align: 'center'
          });
        });
        this.scoreFactorColumns = scoreFactorColumns;
      });
    },
    initTotalChart () {
      // console.log(this.chartOptions.total);
      const totalChart = this.$echarts.init(document.getElementById('totalChart'));
      totalChart.setOption(this.chartOptions.total);
      this.totalChart = totalChart;
    },
    initFactorChart (factorId) {
      const factorChart = this.$echarts.init(document.getElementById('factorChart' + factorId));
      factorChart.setOption(this.chartOptions['factorOptions' + factorId]);
      this.factorChartList.push(factorChart);
    }
  }

};
</script>

<style lang="less" scoped>
@import './Analysis.less';
</style>
