import request from '@/utils/request';
import { TOKEN_PREFIX } from '@/store/mutation-types';

const userApi = {
  Login: '/auth',
  RefreshToken: '/token/refresh',
  // Logout: '/logout',
  DeleteToken: '/token',
  ForgePassword: '/auth/forget-password',
  Register: '/auth/register',
  twoStepCode: '/auth/2step-code',
  SendSms: '/account/sms',
  SendSmsErr: '/account/sms_err',
  // get my info
  UserInfo: '/user/info',
  UserMenu: '/user/nav',
  UserToken: '/token',
  ResetUserPassword: '/userApi/resetPassword',
  ChangeUserPassword: '/userApi/changePassword',
  SendValidationEmail: '/validateApi/sendValidationEmail',
  ResetPassword: '/validateApi/resetPassword'
};

/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */
export function login (parameter) {
  return request({
    // baseURL: '/csr-api',
    url: userApi.Login,
    method: 'post',
    data: parameter
  });
}

export function getSmsCaptcha (parameter) {
  return request({
    url: userApi.SendSms,
    method: 'post',
    data: parameter
  });
}

export function getInfo () {
  return request({
    // url: userApi.UserInfo,
    url: userApi.UserToken,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  });
}

export function getCurrentUserNav () {
  return request({
    url: userApi.UserMenu,
    method: 'get'
  });
}

export function doRefreshToken (token) {
  return request({
    url: userApi.RefreshToken + '/' + TOKEN_PREFIX + token,
    method: 'get'
  });
}

export function logout (token) {
  return request({
    // url: userApi.Logout,
    // method: 'post',
    url: userApi.DeleteToken + '/' + TOKEN_PREFIX + token,
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  });
}

/**
 * get user 2step code open?
 * @param parameter {*}
 */
export function get2step (parameter) {
  return request({
    url: userApi.twoStepCode,
    method: 'post',
    data: parameter
  });
}

export function resetUserPassword (parameter) {
  return request({
    url: userApi.ResetUserPassword,
    method: 'put',
    params: parameter
  });
}

export function changeUserPassword (parameter) {
  return request({
    url: userApi.ChangeUserPassword,
    method: 'put',
    params: parameter
  });
}

export function sendValidationEmail (parameter) {
  return request({
    url: userApi.SendValidationEmail,
    method: 'post',
    data: parameter
    //   headers: {
    //     'Content-Type': 'multipart/form-data'
    //   }
  });
}

export function resetPassword (parameter) {
  return request({
    url: userApi.ResetPassword,
    method: 'post',
    data: parameter
  });
}
