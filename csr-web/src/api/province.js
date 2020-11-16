import request from '@/utils/request';

const provinceApi = {
  findProvinceById: '/provinceApi/findById'
};

export default provinceApi;

export function getProvinceById (id) {
  return request({
    url: provinceApi.findProvinceById + '/' + id,
    method: 'get'
  });
}
