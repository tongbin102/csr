// eslint-disable-next-line
import { UserLayout, BaseLayout, BasicLayout, BlankLayout, SatisfactionLayout } from '@/layouts';

const RouteView = {
  name: 'RouteView',
  render: h => h('router-view')
};

export const asyncRouterMap = [
  {
    path: '/',
    name: 'index',
    component: BaseLayout,
    meta: { title: '满意度情况' },
    // redirect: '/aaaa',
    children: [
      // Satisfaction
      {
        path: '/satisfaction',
        name: 'Satisfaction',
        component: RouteView,
        meta: { title: '满意度情况' },
        redirect: '/satisfaction/national',
        children: [
          {
            path: '/satisfaction/national',
            name: 'SatisfactionNational',
            component: () => import('@/views/satisfaction/National'),
            meta: {
              title: '全国满意度情况',
              keepAlive: false,
              // permission: ['admin', 'national']
              permission: ['admin', 'national', 'region', 'area', 'superior', 'store']
            }
          },
          {
            path: '/satisfaction/region',
            name: 'SatisfactionRegion',
            component: () => import('@/views/satisfaction/Region'),
            meta: {
              title: '大区满意度情况',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area']
            }
          },
          {
            path: '/satisfaction/province',
            name: 'SatisfactionProvince',
            component: () => import('@/views/satisfaction/Province'),
            meta: {
              title: '满意度情况',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area']
            }
          },
          {
            path: '/satisfaction/city',
            name: 'SatisfactionCity',
            component: () => import('@/views/satisfaction/City'),
            meta: {
              title: '满意度情况',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area']
            }
          },
          {
            path: '/satisfaction/superior',
            name: 'SatisfactionSuperior',
            component: () => import('@/views/satisfaction/Superior'),
            meta: {
              title: '满意度情况',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area', 'superior']
            }
          },
          {
            path: '/satisfaction/store',
            name: 'SatisfactionStore',
            component: () => import('@/views/satisfaction/Store'),
            meta: {
              title: '满意度情况',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area', 'superior', 'store']
            }
          }
        ]
      },
      // Factor
      {
        path: '/factor',
        name: 'Factor',
        component: RouteView,
        meta: { title: '业务模块' },
        redirect: '/factor/details',
        children: [
          {
            path: '/factor/details',
            name: 'FactorDetails',
            component: () => import('@/views/factor/Details'),
            meta: {
              title: '业务模块明细',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area', 'superior', 'store']
            }
          }
        ]
      },
      // Regulation
      {
        path: '/regulation',
        name: 'Regulation',
        component: RouteView,
        meta: { title: '评分规则' },
        redirect: '/regulation/survey',
        children: [
          {
            path: '/regulation/survey',
            name: 'RegulationSurvey',
            component: () => import('@/views/regulation/Survey'),
            meta: {
              title: '客户调研评分规则',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'province', 'area', 'city', 'superior', 'store']
            }
          },
          {
            path: '/regulation/monitor',
            name: 'RegulationMonitor',
            component: () => import('@/views/regulation/Monitor'),
            meta: {
              title: '过程监控评分规则',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'province', 'area', 'city', 'superior', 'store']
            }
          },
          {
            path: '/regulation/assistance',
            name: 'RegulationAssistance',
            component: () => import('@/views/regulation/Assistance'),
            meta: {
              title: '服务助手评分规则',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'province', 'area', 'city', 'superior', 'store']
            }
          },
          {
            path: '/regulation/complain',
            name: 'RegulationComplain',
            component: () => import('@/views/regulation/Complain'),
            meta: {
              title: '投诉扣分',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'province', 'area', 'city', 'superior', 'store']
            }
          },
          {
            path: '/regulation/rescue',
            name: 'RegulationRescue',
            component: () => import('@/views/regulation/Rescue'),
            meta: {
              title: '道路救援扣分规则',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'province', 'area', 'city', 'superior', 'store']
            }
          },
          {
            path: '/regulation/data',
            name: 'RegulationData',
            component: () => import('@/views/regulation/Data'),
            meta: {
              title: '数据准确性扣分规则',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'province', 'area', 'city', 'superior', 'store']
            }
          }
        ]
      },
      // Analysis
      {
        path: '/analysis',
        name: 'Analysis',
        component: RouteView,
        meta: { title: '趋势分析' },
        redirect: '/analysis/channel',
        children: [
          {
            path: '/analysis/channel',
            name: 'AnalysisChannel',
            component: () => import('@/views/analysis/Channel'),
            meta: {
              title: '分渠道得分趋势分析',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area', 'superior', 'store']
            }
          },
          {
            path: '/analysis/factor',
            name: 'AnalysisFactor',
            component: () => import('@/views/analysis/Factor'),
            meta: {
              title: '分因子得分趋势分析',
              keepAlive: false,
              permission: ['admin', 'national', 'region', 'area', 'superior', 'store']
            }
          }
        ]
      },
      // Admin
      {
        path: '/admin',
        name: 'admin',
        component: RouteView,
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
