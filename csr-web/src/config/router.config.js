// eslint-disable-next-line
import { UserLayout, BaseLayout, BasicLayout, BlankLayout, SatisfactionLayout } from '@/layouts';

const RouteView = {
  name: 'RouteView',
  render: h => h('router-view')
};

export const asyncRouterMap = [
  // Satisfaction
  {
    path: '/satisfaction',
    name: 'Satisfaction',
    component: BaseLayout,
    meta: { title: '满意度情况' },
    redirect: '/satisfaction/country',
    children: [
      {
        path: '/satisfaction/country',
        name: 'SatisfactionCountry',
        component: () => import('@/views/satisfaction/Country'),
        meta: {
          title: '满意度情况',
          keepAlive: false,
          permission: ['admin', 'vendor', 'region', 'province', 'city', 'superior', 'store']
        }
      },
      {
        path: '/satisfaction/region',
        name: 'SatisfactionRegion',
        component: () => import('@/views/satisfaction/Region'),
        meta: {
          title: '满意度情况',
          keepAlive: false,
          permission: ['admin', 'vendor', 'region', 'province', 'city', 'superior', 'store']
        }
      },
      {
        path: '/satisfaction/province',
        name: 'SatisfactionProvince',
        component: () => import('@/views/satisfaction/Province'),
        meta: {
          title: '满意度情况',
          keepAlive: false,
          permission: ['admin', 'vendor', 'region', 'province', 'city', 'superior', 'store']
        }
      },
      {
        path: '/satisfaction/city',
        name: 'SatisfactionCity',
        component: () => import('@/views/satisfaction/City'),
        meta: {
          title: '满意度情况',
          keepAlive: false,
          permission: ['admin', 'vendor', 'region', 'province', 'city', 'superior', 'store']
        }
      },
      {
        path: '/satisfaction/superior',
        name: 'SatisfactionSuperior',
        component: () => import('@/views/satisfaction/Superior'),
        meta: {
          title: '满意度情况',
          keepAlive: false,
          permission: ['admin', 'vendor', 'region', 'province', 'city', 'superior', 'store']
        }
      },
      {
        path: '/satisfaction/store',
        name: 'SatisfactionStore',
        component: () => import('@/views/satisfaction/Store'),
        meta: {
          title: '满意度情况',
          keepAlive: false,
          permission: ['admin', 'vendor', 'region', 'province', 'city', 'superior', 'store']
        }
      }
    ]
  },
  {
    path: '/factor',
    name: 'Factor',
    component: BaseLayout,
    meta: { title: '业务模块' },
    redirect: '/factor/Details',
    children: [
      {
        path: '/factor/details',
        name: 'FactorDetails',
        component: () => import('@/views/factor/Details'),
        meta: {
          title: '业务模块明细',
          keepAlive: false,
          permission: ['admin', 'vendor', 'province', 'area', 'city', 'superior', 'store']
        }
      }
    ]
  },
  // Admin
  {
    path: '/admin',
    name: 'admin',
    component: BasicLayout,
    meta: { title: '配置文件上传' },
    redirect: '/admin/upload',
    children: [
      {
        path: '/admin/upload',
        name: 'upload',
        component: () => import('@/views/admin/Upload'),
        meta: { title: 'menu.dashboard.analysis', keepAlive: false, permission: ['admin'] }
      }
    ]
  },

  // Exception
  {
    path: '/exception',
    name: 'exception',
    component: RouteView,
    redirect: '/exception/403',
    meta: { title: '异常页', icon: 'warning', permission: ['exception'] },
    children: [
      {
        path: '/exception/403',
        name: 'Exception403',
        component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/403'),
        meta: { title: '403', permission: ['exception'] }
      },
      {
        path: '/exception/404',
        name: 'Exception404',
        component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404'),
        meta: { title: '404', permission: ['exception'] }
      },
      {
        path: '/exception/500',
        name: 'Exception500',
        component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/500'),
        meta: { title: '500', permission: ['exception'] }
      }
    ]
  },

  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
];

/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
      }
    ]
  },
  {
    path: '/403',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/403')
  },
  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404')
  },
  {
    path: '/500',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/500')
  }
];
