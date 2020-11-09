import Mock from 'mockjs2';
// import { builder } from '../util';

const userInfo = {
  resTimestamp: '20201103224924773',
  resCode: 200,
  resMsg: '成功',
  traceID: null,
  spanID: null,
  signature: null,
  resData: {
    pageSize: 10,
    pageNo: -2,
    recordCount: 0,
    id: '1',
    creator: '1',
    updater: '1',
    createTime: '2020-10-30T01:52:38.000+00:00',
    updateTime: '2020-10-30T01:52:38.000+00:00',
    validInd: true,
    username: 'admin',
    name: 'admin',
    password: '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.',
    roleId: 1,
    ref: null,
    sex: '3',
    address: null,
    birthDate: null,
    phone: null,
    iconUrl: null,
    description: null,
    roleName: 'vendor',
    authorities: [
      {
        authority: 'ROLE_USER'
      }
    ],
    enabled: true,
    accountNonExpired: true,
    credentialsNonExpired: true,
    accountNonLocked: true
  },
  ext: null,
  requestUrl: null
};

// const userNav = {};

Mock.mock(/\/csr-api\/token/, 'get', userInfo);
// Mock.mock(/\/csr-api\/user\/info/, 'get', info);
// Mock.mock(/\/csr-api\/user\/nav/, 'get', userNav);
