/* eslint-disable quote-props */
import Mock from 'mockjs2';

const scoreInfo = {
  resTimestamp: '20201105151008366',
  resCode: 200,
  resMsg: '成功',
  traceID: null,
  spanID: null,
  signature: null,
  resData: {
    scoreFactor: [
      {
        '1': '800',
        '2': '1000',
        period: '202011',
        '3': '600',
        '4': '600',
        '5': '600',
        '6': '500'
      },
      {
        '1': '700',
        '2': '900',
        period: '202010',
        '3': '800',
        '4': '200',
        '5': '300',
        '6': '900'
      }
    ],
    scoreTotal: [
      {
        pageSize: 10,
        pageNo: 0,
        recordCount: 0,
        id: '1',
        creator: '1',
        updater: '1',
        createTime: '2020-11-05T02:59:29.000+00:00',
        updateTime: '2020-11-05T02:59:29.000+00:00',
        validInd: true,
        period: '202011',
        scopeId: 1,
        name: '全国',
        score: '1000',
        rankCountry: null,
        rankRegion: null,
        scoreDiff: 5,
        rankDiff: null
      }
    ],
    scoreSeparate: [
      {
        pageSize: 10,
        pageNo: 0,
        recordCount: 0,
        id: '2',
        creator: '1',
        updater: '1',
        createTime: '2020-11-05T02:59:29.000+00:00',
        updateTime: '2020-11-05T02:59:29.000+00:00',
        validInd: true,
        period: '202011',
        scopeId: 2,
        name: '华东大区',
        score: '900',
        rankCountry: 2,
        rankRegion: null,
        scoreDiff: 2,
        rankDiff: -5
      },
      {
        pageSize: 10,
        pageNo: 0,
        recordCount: 0,
        id: '3',
        creator: '1',
        updater: '1',
        createTime: '2020-11-05T02:59:29.000+00:00',
        updateTime: '2020-11-05T02:59:29.000+00:00',
        validInd: true,
        period: '202011',
        scopeId: 2,
        name: '华南大区',
        score: '840',
        rankCountry: 3,
        rankRegion: null,
        scoreDiff: 5,
        rankDiff: -2
      },
      {
        pageSize: 10,
        pageNo: 0,
        recordCount: 0,
        id: '4',
        creator: '1',
        updater: '1',
        createTime: '2020-11-05T02:59:29.000+00:00',
        updateTime: '2020-11-05T02:59:29.000+00:00',
        validInd: true,
        period: '202011',
        scopeId: 2,
        name: '华北大区',
        score: '720',
        rankCountry: 4,
        rankRegion: null,
        scoreDiff: -2,
        rankDiff: 2
      },
      {
        pageSize: 10,
        pageNo: 0,
        recordCount: 0,
        id: '5',
        creator: '1',
        updater: '1',
        createTime: '2020-11-05T02:59:29.000+00:00',
        updateTime: '2020-11-05T02:59:29.000+00:00',
        validInd: true,
        period: '202011',
        scopeId: 2,
        name: '西北大区',
        score: '900',
        rankCountry: 6,
        rankRegion: null,
        scoreDiff: -9,
        rankDiff: 2
      }
    ],
    scoreChannel: [
      {
        '1': '800',
        '2': '1000',
        period: '202011',
        '3': '600',
        '4': '-20'
      },
      {
        '1': '700',
        '2': '900',
        period: '202010',
        '3': '800',
        '4': '-200'
      }
    ]
  },
  ext: null,
  requestUrl: null
};
Mock.mock(/\/csr-api\/scoreApi\/findScoreInfo/, 'get', scoreInfo);
