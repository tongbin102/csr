import router from './router';
import store from './store';
import storage from 'store';
import NProgress from 'nprogress'; // progress bar
import '@/components/NProgress/nprogress.less'; // progress bar custom style
import notification from 'ant-design-vue/es/notification';
import { setDocumentTitle, domTitle } from '@/utils/domUtil';
import { ACCESS_TOKEN } from '@/store/mutation-types';
import { i18nRender } from '@/locales';

NProgress.configure({ showSpinner: false }); // NProgress Configuration

// const allowList = ['login', 'register', 'registerResult']; // no redirect allowList
const allowList = ['login', 'recover', 'resetPassword'];
const loginRoutePath = '/user/login';
// const changePasswordRoutePath = '/user/changePassword';
const defaultRoutePath = '/';

router.beforeEach((to, from, next) => {
  NProgress.start(); // start progress bar
  to.meta && typeof to.meta.title !== 'undefined' && setDocumentTitle(`${i18nRender(to.meta.title)} - ${domTitle}`);
  /* has token */
  if (storage.get(ACCESS_TOKEN)) {
    if (to.path === loginRoutePath) {
      next({ path: defaultRoutePath });
      NProgress.done();
    } else {
      // check login user.roles is null
      // console.log(store.getters.roles);
      if (store.getters.roles.length === 0) {
        // request login userInfo
        store
          .dispatch('GetInfo')
          .then(res => {
            const userInfo = res.resData;
            // console.log(res);
            if (res.resCode === 401) {
              // 失败时，获取用户信息失败时，调用登出，来清空历史保留信息
              // store.dispatch('Logout').then(() => {
              //   next({ path: loginRoutePath, query: { redirect: to.fullPath } });
              // });
            } else {
              // generate dynamic router
              store.dispatch('GenerateRoutes', { userInfo }).then(() => {
                // 根据roles权限生成可访问的路由表
                // 动态添加可访问路由表
                router.addRoutes(store.getters.addRouters);
                // 请求带有 redirect 重定向时，登录自动重定向到该地址
                const redirect = decodeURIComponent(from.query.redirect || to.path);
                // if (to.path === '/') {
                if (redirect === '/') {
                  const roles = store.getters.roles;
                  // console.log('roles: ', roles);
                  const userInfo = store.getters.userInfo;
                  // console.log(userInfo);
                  // if (userInfo.loginType === 0) {
                  //   console.log(changePasswordRoutePath);
                  //   next({ path: changePasswordRoutePath });
                  // } else
                  if (roles === 'admin') {
                    // 管理员账户
                    next({ path: '/admin/upload' });
                  } else if (roles === 'national') {
                    // 厂家账户
                    next({
                      path: '/satisfaction/national'
                    });
                  } else if (roles === 'region') {
                    // 大区账户
                    next({
                      path: '/satisfaction/region',
                      query: {
                        code: userInfo.ref
                      }
                    });
                  } else if (roles === 'area') {
                    // 区域顾问
                    next({
                      path: '/satisfaction/region',
                      query: {
                        code: userInfo.ref
                      }
                    });
                  } else if (roles === 'superior') {
                    // 一级店账户
                    next({
                      path: '/satisfaction/superior',
                      query: {
                        code: userInfo.ref
                      }
                    });
                  } else if (roles === 'store') {
                    // 二级店账户
                    next({
                      path: '/satisfaction/store',
                      query: {
                        code: userInfo.ref
                      }
                    });
                  }
                } else if (to.path === redirect) {
                  // set the replace: true so the navigation will not leave a history record
                  next({ ...to, replace: true });
                } else {
                  // 跳转到目的路由
                  next({ path: redirect });
                }
              });
            }
          })
          .catch(() => {
            notification.error({
              message: '错误',
              description: '请求用户信息失败，请重试'
            });
            // 失败时，获取用户信息失败时，调用登出，来清空历史保留信息
            console.log('logout222');
            // store.dispatch('Logout').then(() => {
            //   next({ path: loginRoutePath, query: { redirect: to.fullPath } });
            // });
          });
      } else {
        if (to.path === '/') {
          const roles = store.getters.roles;
          console.log('roles: ', roles);
          const userInfo = store.getters.userInfo;
          console.log(userInfo);
          if (roles === 'admin') {
            // 管理员账户
            next({ path: '/admin/upload' });
          } else if (roles === 'national') {
            next({ path: '/satisfaction/national' });
          } else if (roles === 'region') {
            next({
              path: '/satisfaction/region',
              query: {
                code: userInfo.ref
              }
            });
          } else if (roles === 'province') {
            next({
              path: '/satisfaction/province',
              query: {
                code: userInfo.ref
              }
            });
          } else if (roles === 'city') {
            next({
              path: '/satisfaction/city',
              query: {
                code: userInfo.ref
              }
            });
          } else if (roles === 'superior') {
            next({
              path: '/satisfaction/superior',
              query: {
                code: userInfo.ref
              }
            });
          } else if (roles === 'store') {
            next({
              path: '/satisfaction/store',
              query: {
                code: userInfo.ref
              }
            });
          }
        } else {
          // 跳转到目的路由
          // next({ path: redirect });
          next();
        }
        // next();
        // next();
      }
    }
  } else {
    if (allowList.includes(to.name)) {
      // 在免登录名单，直接进入
      next();
    } else {
      next({ path: loginRoutePath, query: { redirect: to.fullPath } });
      NProgress.done(); // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
});

router.afterEach(() => {
  NProgress.done(); // finish progress bar
});
