import request from '@/utils/request';

const provinceApi = {
  findProvinceByCode: '/provinceApi/findByCode',

  ResetPassword: '/validateApi/resetPassword'
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

export function resetPassword (parameter) {
  return request({
    url: provinceApi.ResetPassword,
    method: 'post',
    params: parameter
  });
}
