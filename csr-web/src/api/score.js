import request from '@/utils/request';

const scoreApi = {
  findInfo: '/scoreApi/findInfo'
};

const scoreChannelApi = {
  findInfo: '/scoreChannelApi/findInfo'
};

const scoreFactorApi = {
  findInfo: '/scoreFactorApi/findInfo',
  findInfoForStore: '/scoreFactorApi/findInfoForStore',
  findInfoForFactor: '/scoreFactorApi/findInfoForFactor'
};

export default scoreApi;

export function getScoreInfo (parameter) {
  return request({
    url: scoreApi.findInfo,
    method: 'get',
    params: parameter
  });
}

export function getScoreChannelInfo (parameter) {
  return request({
    url: scoreChannelApi.findInfo,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfo (parameter) {
  return request({
    url: scoreFactorApi.findInfo,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfoForStore (parameter) {
  return request({
    url: scoreFactorApi.findInfoForStore,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfoForFactor (parameter) {
  return request({
    url: scoreFactorApi.findInfoForFactor,
    method: 'get',
    params: parameter
  });
}
