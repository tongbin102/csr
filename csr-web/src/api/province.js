import request from '@/utils/request';

const provinceApi = {
  findProvinceByCode: '/provinceApi/findByCode'
};

export default provinceApi;

export function getProvinceByCode (code) {
  return request({
    url: provinceApi.findProvinceByCode,
    method: 'get',
    params: {
      code: code
    }
  });
}
