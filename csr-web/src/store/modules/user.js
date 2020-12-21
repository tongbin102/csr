import storage from 'store';
import { login, getInfo, resetPassword, logout } from '@/api/login';

import { ACCESS_TOKEN, REFRESH_TOKEN, CREATE_AT } from '@/store/mutation-types';
import { welcome } from '@/utils/util';

const user = {
  state: {
    accessToken: '',
    refreshToken: '',
    // token: '',
    name: '',
    welcome: '',
    avatar: '',
    roles: [],
    info: {}
  },

  mutations: {
    SET_ACCESS_TOKEN: (state, token) => {
      state.accessToken = token;
    },
    SET_REFRESH_TOKEN: (state, token) => {
      state.refreshToken = token;
    },
    SET_NAME: (state, { name, welcome }) => {
      state.name = name;
      state.welcome = welcome;
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar;
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles;
    },
    SET_INFO: (state, info) => {
      state.info = info;
    }
  },

  actions: {
    // 登录
    Login ({ commit }, loginInfo) {
      return new Promise((resolve, reject) => {
        login(loginInfo)
          .then(res => {
            if (res.resCode === 200) {
              // console.log(res);
              // const token = res.resData;
              // storage.set(ACCESS_TOKEN, token.accesstoken, 60 * 60 * 1000);
              const data = res.resData;
              const accessToken = data.accessToken;
              const refreshToken = data.refreshToken;
              storage.set(ACCESS_TOKEN, accessToken, 60 * 1000);
              storage.set(REFRESH_TOKEN, refreshToken, 7 * 24 * 60 * 60 * 1000);
              storage.set(CREATE_AT, new Date().getTime());
              commit('SET_ACCESS_TOKEN', accessToken);
              commit('SET_REFRESH_TOKEN', refreshToken);
              resolve(res.resData);
            } else {
              reject(res.resData);
            }
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    // 获取用户信息
    GetInfo ({ commit }) {
      return new Promise((resolve, reject) => {
        getInfo()
          .then(response => {
            const userInfo = response.resData;
            if (userInfo.roleId && userInfo.roleName && userInfo.authorities.length > 0) {
              // const role = {};
              // role.permissionId = result.roleId;
              // role.permissions = result.authorities;
              // role.permissions.map(permission => {
              //   if (permission.actionEntitySet != null && per.actionEntitySet.length > 0) {
              //     const action = permission.actionEntitySet.map(action => {
              //       return action.action;
              //     });
              //     permission.actionList = action;
              //   }
              // });
              // role.permissionList = role.permissions.map(permission => { return permission.permissionId; });
              commit('SET_ROLES', userInfo.roleName);
              commit('SET_INFO', userInfo);
            } else {
              // reject(new Error('getInfo: roles must be a non-null array !'));
            }

            commit('SET_NAME', { name: userInfo.name, welcome: welcome() });
            commit('SET_AVATAR', userInfo.avatar);

            resolve(response);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    ResetPassword ({ commit }, resetInfo) {
      return new Promise((resolve, reject) => {
        resetPassword(resetInfo)
          .then(res => {
            console.log(res);
            if (res.resCode === 200) {
              // console.log(res);
              // const token = res.resData;
              // storage.set(ACCESS_TOKEN, token.accesstoken, 60 * 60 * 1000);
              const data = res.resData;
              const accessToken = data.accessToken;
              const refreshToken = data.refreshToken;
              storage.set(ACCESS_TOKEN, accessToken, 60 * 1000);
              storage.set(REFRESH_TOKEN, refreshToken, 7 * 24 * 60 * 60 * 1000);
              storage.set(CREATE_AT, new Date().getTime());
              commit('SET_ACCESS_TOKEN', accessToken);
              commit('SET_REFRESH_TOKEN', refreshToken);
              resolve(res.resData);
            } else {
              reject(res.resData);
            }
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    // 登出
    Logout ({ commit, state }) {
      return new Promise(resolve => {
        console.log('refreshToken: ', state.refreshToken);
        logout(state.refreshToken)
          .then(() => {
            commit('SET_ACCESS_TOKEN', '');
            commit('SET_REFRESH_TOKEN', '');
            commit('SET_ROLES', []);
            storage.remove(ACCESS_TOKEN);
            storage.remove(REFRESH_TOKEN);
            storage.remove(CREATE_AT);
            resolve();
          })
          .catch(() => {
            resolve();
          })
          .finally(() => {
          });
      });
    }
  }
};

export default user;
