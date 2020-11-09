import Mock from 'mockjs2';

const factorList = {
  resTimestamp: '20201105155504275',
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
      createTime: '2020-10-30T06:02:37.000+00:00',
      updateTime: '2020-10-30T06:02:37.000+00:00',
      validInd: true,
      code: null,
      name: '预约'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '2',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T06:02:37.000+00:00',
      updateTime: '2020-10-30T06:02:37.000+00:00',
      validInd: true,
      code: null,
      name: '服务流程'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '3',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T06:02:37.000+00:00',
      updateTime: '2020-10-30T06:02:37.000+00:00',
      validInd: true,
      code: null,
      name: '交付效率'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '4',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T06:02:37.000+00:00',
      updateTime: '2020-10-30T06:02:37.000+00:00',
      validInd: true,
      code: null,
      name: '硬件设施'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '5',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T06:02:37.000+00:00',
      updateTime: '2020-10-30T06:02:37.000+00:00',
      validInd: true,
      code: null,
      name: '服务质量'
    },
    {
      pageSize: 10,
      pageNo: 0,
      recordCount: 0,
      id: '6',
      creator: '1',
      updater: '1',
      createTime: '2020-10-30T06:02:37.000+00:00',
      updateTime: '2020-10-30T06:02:37.000+00:00',
      validInd: true,
      code: null,
      name: '服务价值'
    }
  ],
  ext: null,
  requestUrl: null
};
Mock.mock(/\/csr-api\/factorApi\/findAll/, 'get', factorList);
