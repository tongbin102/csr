import request from '@/utils/request';

const factorApi = {
  getAll: '/factorApi/findAll'
};

export default factorApi;

export function getAllFactor (parameter) {
  return request({
    url: factorApi.getAll,
    method: 'get',
    params: parameter
  });
}
