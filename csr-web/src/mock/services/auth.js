import Mock from 'mockjs2';
import {builder, getBody} from '../util';

const username = ['admin'];
const password = ['123456'];

const auth = (options) => {
  const body = getBody(options);
  console.log('mock: body', body);
  if (!username.includes(body.username) || !password.includes(body.password)) {
    return builder({isLogin: true}, '账户或密码错误', 401);
  }
  return {
    resTimestamp: '20201103224910316',
    resCode: 200,
    resMsg: '成功',
    traceID: null,
    spanID: null,
    signature: null,
    resData:
      'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwNDQxNTU1NSwiaWF0IjoxNjA0NDE0OTUwfQ.Cq9G7UzOfkbT7nideALiHtnWRB7EQDzreabJaji5nllwTdJWn_XZJhEpmIkmy-6pFJI4VDFb6sHZuMPw2a_HWA',
    ext: null,
    requestUrl: null
  };
};

const logout = () => {
  return builder({}, '[测试接口] 注销成功');
};

const smsCaptcha = () => {
  return builder({captcha: Mock.mock('@integer(10000, 99999)')});
};

const twofactor = () => {
  return builder({stepCode: Mock.mock('@integer(0, 1)')});
};

// Mock.mock(/\/auth\/login/, 'post', login);
Mock.mock(/\/csr-api\/auth/, 'post', auth);
Mock.mock(/\/auth\/logout/, 'post', logout);
Mock.mock(/\/account\/sms/, 'post', smsCaptcha);
Mock.mock(/\/auth\/2step-code/, 'post', twofactor);
