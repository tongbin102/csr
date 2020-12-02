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

      <a-table :columns="scoreChannelColumns" :data-source="scoreChannelData" :pagination="false" :loading="scoreChannelLoading">
      </a-table>
      <a-row>
        <a-col :span="12">
          <!-- <v-chart :options="chartOptions.total" :auto-resize="true"></v-chart> -->
          <div class="Echarts">
            <div id="totalChart" :style="{padding: '5px', width: '100%', height: '200px'}"></div>
          </div>
          <!-- <bar :data="barData.barTotal" title="总分" style="height:100px;" /> -->
          <!-- <mini-bar :data="barData.barTotal" /> -->
        </a-col>
        <a-col :span="12" v-for="channel in channelList1" :key="channel.id">
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
      barColor

    };
  },
  mounted () {
    this.initialData();
    window.onresize = () => {
      //  根据窗口大小调整曲线大小
      this.totalChart.resize();
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
          title: { text: '总分' },
          tooltip: {},
          xAxis: {
            data: []
          },
          yAxis: {},
          series: [{
            name: '得分',
            type: 'bar',
            itemStyle: {
              color: this.barColor
            },
            label: {
              fontSize: 6
            },
            data: []
          }]
        };
        const xAxisData = [];
        const seriesData = [];
        scoreChannelInfo.forEach(scoreChannel => {
          xAxisData.push(moment(scoreChannel.period).format('M月'));
          seriesData.push(parseInt(scoreChannel.score) || 0);
        });
        totalOptions.xAxis.data = xAxisData;
        totalOptions.series[0].data = seriesData;
        this.chartOptions.total = totalOptions;
        this.initTotalChart();

        this.channelList1.forEach(channel => {
          const channelOptions = {
            title: { text: channel.name },
            tooltip: {},
            xAxis: {
              data: []
            },
            yAxis: {},
            series: [{
              name: '得分',
              type: 'bar',
              itemStyle: {
                color: this.barColor
              },
              label: {
                fontSize: 6
              },
              data: []
            }]
          };
          const xAxisData = [];
          const seriesData = [];

          scoreChannelInfo.forEach(scoreChannel => {
            xAxisData.push(moment(scoreChannel.period).format('M月'));
            seriesData.push(parseInt(scoreChannel.score) || 0);
          });
          channelOptions.xAxis.data = xAxisData;
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
      console.log(this.chartOptions.total);
      const totalChart = this.$echarts.init(document.getElementById('totalChart'));
      totalChart.setOption(this.chartOptions.total);
    },
    initChannelChart (channelId) {
      const channelChart = this.$echarts.init(document.getElementById('channelChart' + channelId));
      channelChart.setOption(this.chartOptions['channelOptions' + channelId]);
    }
  }

};
</script>

<style lang="less" scoped>
.content {
  border: 1px solid;
}
</style>
