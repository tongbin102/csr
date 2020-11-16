import request from '@/utils/request';

const storeApi = {
  findStoreById: '/storeApi/findById',
  findStoreByParentId: '/storeApi/findByParentId'
};

export default storeApi;

export function getStoreById (id) {
  return request({
    url: storeApi.findStoreById + '/' + id,
    method: 'get'
  });
}

export function getStoreByParentId (parentId) {
  return request({
    url: storeApi.findStoreByParentId + '/' + parentId,
    method: 'get'
  });
}
