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
          <bar :data="barData.barTotal" title="总分" />
        </a-col>
        <a-col :span="12" v-for="channel in channelList1" :key="channel.id">
          <bar :data="barData['barChannel' + channel.id]" :title="channel.name" :extend="barExtend" />
        </a-col>
      </a-row>

    </div>
  </div>
</template>

<script>
import moment from 'moment';
import Bar from '@/components/Charts/Bar';
import { getScoreChannelInfoByPeriods } from '@/api/score';
import { getChannelList1 } from '@/api/channel';

export default {
  components: {
    Bar
  },
  data () {
    this.barExtend = {
        series: {
          label: { show: true, position: 'top' }
        }
      };
    return {
      period: '',
      scopeId: '',
      storeId: '',
      channelList1: [],
      scoreChannelLoading: false,
      scoreChannelColumns: [],
      scoreChannelData: [],
      barData: {}

    };
  },
  mounted () {
    this.initialData();
  },
  methods: {
    initialData () {
      this.getScoreChannelColumn();
      this.period = moment().add('month', 0).format('yyyyMM');
      this.scopeId = this.$route.query.scope_id;
      this.storeId = this.$route.query.store_id;
      const beginPeriod = moment().subtract(5, 'month').format('yyyyMM');
      this.getScoreChannelInfoByPeriods({
        begin_period: beginPeriod,
        end_period: this.period,
        scope_id: this.scopeId,
        store_id: this.storeId
      });
    },
    getScoreChannelInfoByPeriods (params = {}) {
      this.scoreChannelLoading = true;
      getScoreChannelInfoByPeriods(params).then(res => {
        const scoreChannelInfo = res.resData;
        this.scoreChannelLoading = false;
        this.scoreChannelData = [scoreChannelInfo[scoreChannelInfo.length - 1].scoreChannel];

        const barTotal = [];
        scoreChannelInfo.forEach(scoreChannel => {
          barTotal.push({
            x: scoreChannel.period,
            y: scoreChannel.score || 0
          });
        });
        this.barData.barTotal = barTotal;

        this.channelList1.forEach(channel => {
          const barChannel = [];
          // console.log(scoreChannelInfo);

          scoreChannelInfo.forEach(info => {
            const scoreChannel = info.scoreChannel;
            barChannel.push({
              x: info.period,
              // eslint-disable-next-line no-prototype-builtins
              y: scoreChannel.hasOwnProperty([channel.id]) ? scoreChannel[channel.id] : 0
            });
          });
          this.barData['barChannel' + channel.id] = barChannel;
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
            dataIndex: channel.id,
            key: channel.id,
            width: '33%',
            align: 'center'
          });
        });
        this.scoreChannelColumns = scoreChannelColumns;
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
