import storage from 'store';
import { login, getInfo, logout } from '@/api/login';
import { ACCESS_TOKEN } from '@/store/mutation-types';
import { welcome } from '@/utils/util';

const user = {
  state: {
    token: '',
    name: '',
    welcome: '',
    avatar: '',
    roles: [],
    info: {}
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
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
          .then(response => {
            const token = response.resData;
            storage.set(ACCESS_TOKEN, token, 7 * 24 * 60 * 60 * 1000);
            commit('SET_TOKEN', token);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },

    // 获取用户信息
    GetInfo ({ commit }) {
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
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
        }).catch(error => {
          reject(error);
        });
      });
    },

    // 登出
    Logout ({ commit, state }) {
      return new Promise((resolve) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '');
          commit('SET_ROLES', []);
          storage.remove(ACCESS_TOKEN);
          resolve();
        }).catch(() => {
          resolve();
        }).finally(() => {
        });
      });
    }

  }
};

export default user;
