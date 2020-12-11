import axios from 'axios';
import store from '@/store';
import storage from 'store';
import notification from 'ant-design-vue/es/notification';
import { VueAxios } from './axios';
import { ACCESS_TOKEN, REFRESH_TOKEN, CREATE_AT, TOKEN_PREFIX } from '@/store/mutation-types';
import { doRefreshToken } from '@/api/login';
import { isAccessTokenExpired, isRefreshTokenExpired } from '@/utils/util';
// import { ACCESS_TOKEN, REFRESH_TOKEN, TOKEN_PREFIX } from '@/store/mutation-types';
// import { refreshToken } from '@/api/login';

// 是否有请求正在刷新token
window.isRefreshing = false;

// 被挂起的请求数组
let refreshSubscribers = [];

// push所有请求到数组中
function subscribeTokenRefresh (cb) {
  refreshSubscribers.push(cb);
}

// 刷新请求（refreshSubscribers数组中的请求得到新的token之后会自执行，用新的token去请求数据）
function onRefreshed (token) {
  refreshSubscribers.map(cb => cb(token));
}

// 创建 axios 实例
const request = axios.create({
  // API 请求的默认前缀
  baseURL: process.env.VUE_APP_API_BASE_URL,
  timeout: 900000 // 请求超时时间
});

// 异常拦截处理器
const errorHandler = error => {
  console.log('error');
  if (error.response) {
    const data = error.response.data;
    // 从 localstorage 获取 token
    const token = storage.get(ACCESS_TOKEN);
    console.log('token: ', token);
    if (error.response.status === 403) {
      notification.error({
        message: 'Forbidden',
        description: data.message
      });
    }
    // if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
    // notification.error({
    //   message: 'Unauthorized',
    //   description: 'Authorization verification failed'
    // });
    if (token) {
      store.commit('SET_ACCESS_TOKEN', '');
      store.commit('SET_REFRESH_TOKEN', '');
      store.commit('SET_ROLES', []);
      storage.remove(ACCESS_TOKEN);
      storage.remove(REFRESH_TOKEN);
      storage.remove(CREATE_AT);
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    }
    // }
  }
  return Promise.reject(error);
};

// request interceptor
request.interceptors.request.use(config => {
  const accessToken = storage.get(ACCESS_TOKEN);
  if (accessToken) {
    // if (config.url.indexOf('/token/refresh') === -1) {
    config.headers.Authorization = TOKEN_PREFIX + accessToken;
    // }
    // 判断refreshToken是否过期
    if (isRefreshTokenExpired() && config.url.indexOf('/user/login') === -1) {
      // 登出
      console.log('Refresh Token Expired!');

      store.commit('SET_ACCESS_TOKEN', '');
      store.commit('SET_REFRESH_TOKEN', '');
      store.commit('SET_ROLES', []);
      storage.remove(ACCESS_TOKEN);
      storage.remove(REFRESH_TOKEN);
      storage.remove(CREATE_AT);
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    }
    // 判断token是否将要过期
    if (isAccessTokenExpired() && config.url.indexOf('/token/refresh') === -1) {
      if (!window.isRefreshing) {
        window.isRefreshing = true;
        const refreshToken = storage.get(REFRESH_TOKEN);
        doRefreshToken(refreshToken).then(res => {
          window.isRefreshing = false;
          if (res.resCode === 200) {
            const newAccessToken = res.resData;
            config.headers.Authorization = TOKEN_PREFIX + newAccessToken;
            storage.set(ACCESS_TOKEN, newAccessToken);
            // 执行数组里的函数,重新发起被挂起的请求
            onRefreshed(newAccessToken);
            // 执行onRefreshed函数后清空数组中保存的请求
            refreshSubscribers = [];
          } else {
            store.commit('SET_ACCESS_TOKEN', '');
            store.commit('SET_REFRESH_TOKEN', '');
            store.commit('SET_ROLES', []);
            storage.remove(ACCESS_TOKEN);
            storage.remove(REFRESH_TOKEN);
            storage.remove(CREATE_AT);
            setTimeout(() => {
              window.location.reload();
            }, 1000);
          }
        });
      }
      /* 把请求(token)=>{....}都push到一个数组中 */
      const retry = new Promise((resolve, reject) => {
        /* (token) => {...}这个函数就是回调函数 */
        subscribeTokenRefresh(token => {
          config.headers.Authorization = TOKEN_PREFIX + token;
          /* 将请求挂起 */
          resolve(config);
        });
      });
      return retry;
    } else {
      // 未登录直接返回配置信息
      return config;
    }
  }

  return config;
}, errorHandler);

// response interceptor
request.interceptors.response.use(response => {
  if (response.data.resCode === 200) {
    return response.data;
  }
}, errorHandler);

const installer = {
  vm: {},
  install (Vue) {
    Vue.use(VueAxios, request);
  }
};

export default request;

export { installer as VueAxios, request as axios };
