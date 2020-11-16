import request from '@/utils/request';

const scoreApi = {
  findScoreInfo: '/scoreApi/findInfo',
  findScoreChannelInfo: '/scoreChannelApi/findInfo',
  findScoreChannelInfoByPeriods: '/scoreChannelApi/findInfoByPeriods',
  findScoreFactorInfo: '/scoreFactorApi/findInfo',
  findScoreFactorInfoByPeriods: '/scoreFactorApi/findInfoByPeriods',
  findScoreFactorInfoForStore: '/scoreFactorApi/findInfoForStore',
  findScoreFactorInfoForFactor: '/scoreFactorApi/findInfoForFactor',
  findScoreQuestionByStoreAndQuestionId: '/scoreQuestionApi/findByStoreAndQuestionId'
};

export default scoreApi;

export function getScoreInfo (parameter) {
  return request({
    url: scoreApi.findScoreInfo,
    method: 'get',
    params: parameter
  });
}

export function getScoreChannelInfo (parameter) {
  return request({
    url: scoreApi.findScoreChannelInfo,
    method: 'get',
    params: parameter
  });
}

export function getScoreChannelInfoByPeriods (parameter) {
  return request({
    url: scoreApi.findScoreChannelInfoByPeriods,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfo (parameter) {
  return request({
    url: scoreApi.findScoreFactorInfo,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfoByPeriods (parameter) {
  return request({
    url: scoreApi.findScoreFactorInfoByPeriods,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfoForStore (parameter) {
  return request({
    url: scoreApi.findScoreFactorInfoForStore,
    method: 'get',
    params: parameter
  });
}

export function getScoreFactorInfoForFactor (parameter) {
  return request({
    url: scoreApi.findScoreFactorInfoForFactor,
    method: 'get',
    params: parameter
  });
}

export function getScoreQuestionByStoreAndQuestionId (parameter) {
  return request({
    url: scoreApi.findScoreQuestionByStoreAndQuestionId,
    method: 'get',
    params: parameter
  });
}
