import request from '@/utils/request';

const storeApi = {
  findStoreByCode: '/storeApi/findByCode',
  findStoreByParentCode: '/storeApi/findByParentCode'
};

export default storeApi;

export function getStoreByCode (code) {
  return request({
    url: storeApi.findStoreByCode,
    method: 'get',
    params: {
      code: code
    }
  });
}

export function getStoreByParentCode (parentCode) {
  return request({
    url: storeApi.findStoreByParentCode,
    method: 'get',
    params: {
      parent_code: parentCode
    }
  });
}
