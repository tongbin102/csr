/* eslint-disable quote-props */
import Mock from 'mockjs2';

const channelList = {
  resTimestamp: '20201105155510785',
  resCode: 200,
  resMsg: '成功',
  traceID: null,
  spanID: null,
  signature: null,
  resData: [
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '1',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T04:31:03.000+00:00',
      updateTime: '2020-10-30T04:31:03.000+00:00',
      validInd: true,
      code: 'survey',
      name: '客户调研'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '2',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T04:31:03.000+00:00',
      updateTime: '2020-10-30T04:31:03.000+00:00',
      validInd: true,
      code: 'monitor',
      name: '过程监控'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '3',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T04:31:03.000+00:00',
      updateTime: '2020-10-30T04:31:03.000+00:00',
      validInd: true,
      code: 'assistant',
      name: '服务助手'
    }
  ],
  ext: null,
  requestUrl: null
};
Mock.mock(/\/csr-api\/channelApi\/findAll/, 'get', channelList);
