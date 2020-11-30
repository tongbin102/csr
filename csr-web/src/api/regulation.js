import request from '@/utils/request';

const regulationApi = {
  getRegulationById: '/regulationApi/findById',
  // getRegulationVoById: '/regulationApi/findVoById',
  getRegulationScoreInfo: '/regulationApi/findInfo'
};

export default regulationApi;

export function getRegulationById (id) {
  return request({
    url: regulationApi.getRegulationById + '/' + id,
    method: 'get'
  });
}

// export function getRegulationVoById (id) {
//   return request({
//     url: regulationApi.getRegulationVoById + '/' + id,
//     method: 'get'
//   });
// }

export function getRegulationScoreInfo (parameter) {
  return request({
    url: regulationApi.getRegulationScoreInfo,
    method: 'get',
    params: parameter
  });
}
