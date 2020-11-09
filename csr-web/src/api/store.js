import request from '@/utils/request';

const storeApi = {
  findById: '/storeApi/findById',
  findByParentId: '/storeApi/findByParentId'
};

export default storeApi;

export function getStoreById (id) {
  return request({
    url: storeApi.findById + '/' + id,
    method: 'get'
  });
}

export function getStoreByParentId (parentId) {
  return request({
    url: storeApi.findByParentId + '/' + parentId,
    method: 'get'
  });
}
