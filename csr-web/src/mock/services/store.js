/* eslint-disable quote-props */
import Mock from 'mockjs2';

const store = {
  resTimestamp: '20201105232509448',
  resCode: 200,
  resMsg: '成功',
  traceID: null,
  spanID: null,
  signature: null,
  resData: {
    pageSize: 10,
    pageNo: -1,
    recordCount: 0,
    id: '2',
    creator: null,
    updater: null,
    createTime: null,
    updateTime: null,
    validInd: null,
    code: 'A00101',
    name: '上海五菱店',
    superior: 'A001',
    scale: '2类',
    region: '华南大区',
    province: '上海',
    city: '上海'
  },
  ext: null,
  requestUrl: null
};
Mock.mock(/\/csr-api\/storeApi\/findById/, 'get', store);
