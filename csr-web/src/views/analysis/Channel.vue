<template>
  <div>
    <a-row>
      <a-col :span="24" style="font-weight: bold;">满意度趋势分析</a-col>
    </a-row>
    <div class="analysis-content">
      <a-row class="title">
        <a-col :span="6">
          <span>分渠道得分</span>
        </a-col>
        <a-col :span="6" :offset="12">
          <span>趋势分析</span>
        </a-col>
      </a-row>

      <a-table
        id="scoreChannelTable"
        table-layout="fixed"
        :columns="scoreChannelColumns"
        :data-source="scoreChannelData"
        :pagination="false"
        :loading="scoreChannelLoading">
      </a-table>

      <a-row>
        <a-col :xs="24" :sm="12">
          <!-- <v-chart :options="chartOptions.total" :auto-resize="true"></v-chart> -->
          <div class="Echarts">
            <div id="totalChart" :style="{padding: '5px', width: '100%', height: '200px'}"></div>
          </div>
        </a-col>
        <a-col :xs="24" :sm="12" v-for="channel in channelList1" :key="channel.id">
          <div class="Echarts">
            <div :id="'channelChart'+ channel.id" :style="{padding: '5px', width: '100%', height: '200px'}"></div>
          </div>
          <!-- <bar :data="barData['barChannel' + channel.id]" :title="channel.name" :extend="barExtend" /> -->
        </a-col>
      </a-row>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
import { getScoreChannelInfoByPeriods } from '@/api/score';
import { getChannelList1 } from '@/api/channel';

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
      channelList1: [],
      scoreChannelLoading: false,
      scoreChannelColumns: [],
      scoreChannelData: [],
      barData: {},
      totalOptions: {},
      chartOptions: {},
      barColor,
      totalChart: {},
      channelChartList: []
    };
  },
  mounted () {
    this.initialData();
    window.onresize = () => {
      //  根据窗口大小调整曲线大小
      this.totalChart.resize();
      this.channelChartList.forEach(channelChart => {
        channelChart.resize();
      });
    };
  },
  methods: {
    initialData () {
      this.getScoreChannelColumn();
      this.period = moment().add('month', 0).format('yyyyMM');
      this.scopeId = this.$route.query.scope_id;
      this.code = this.$route.query.code;
      const beginPeriod = moment().subtract(5, 'month').format('yyyyMM');
      this.getScoreChannelInfoByPeriods({
        begin_period: beginPeriod,
        end_period: this.period,
        scope_id: this.scopeId,
        store_code: this.code
      });
    },
    getScoreChannelInfoByPeriods (params = {}) {
      this.scoreChannelLoading = true;
      getScoreChannelInfoByPeriods(params).then(res => {
        // console.log(res.resData);
        const scoreChannelInfo = res.resData;
        this.scoreChannelLoading = false;
        this.scoreChannelData = [scoreChannelInfo[scoreChannelInfo.length - 1].scoreChannel];

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
        scoreChannelInfo.forEach(scoreChannel => {
          const month = moment(scoreChannel.period).format('M月');
          xAxisData.push({ value: month });
          seriesData.push([month, parseInt(scoreChannel.score) || null]);
        });
        totalOptions.xAxis[0].data = xAxisData;
        totalOptions.series[0].data = seriesData;
        this.chartOptions.total = totalOptions;
        this.initTotalChart();

        this.channelList1.forEach(channel => {
          const channelOptions = {
            title: {
              text: channel.name,
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

          scoreChannelInfo.forEach(scoreChannel => {
            const month = moment(scoreChannel.period).format('M月');
            xAxisData.push({ value: month });
            seriesData.push([month, parseInt(scoreChannel.scoreChannel[channel.name]) || null]);
          });
          channelOptions.xAxis[0].data = xAxisData;
          channelOptions.series[0].data = seriesData;
          this.chartOptions['channelOptions' + channel.id] = channelOptions;
          this.initChannelChart(channel.id);
        });
        // console.log(this.barData);
      });
    },
    getScoreChannelColumn (params = {}) {
      getChannelList1(params).then(res => {
        this.channelList1 = res.resData;
        const scoreChannelColumns = [];
        res.resData.forEach((channel) => {
          scoreChannelColumns.push({
            title: channel.name,
            dataIndex: channel.name,
            key: channel.id,
            width: '33%',
            align: 'center'
          });
        });
        this.scoreChannelColumns = scoreChannelColumns;
      });
    },
    initTotalChart () {
      // console.log(this.chartOptions.total);
      const totalChart = this.$echarts.init(document.getElementById('totalChart'));
      totalChart.setOption(this.chartOptions.total);
      this.totalChart = totalChart;
    },
    initChannelChart (channelId) {
      const channelChart = this.$echarts.init(document.getElementById('channelChart' + channelId));
      channelChart.setOption(this.chartOptions['channelOptions' + channelId]);
      this.channelChartList.push(channelChart);
    }
  }

};
</script>

<style lang="less" scoped>
@import './Analysis.less';
</style>
