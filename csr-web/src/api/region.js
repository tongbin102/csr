import request from '@/utils/request';

const regionApi = {
  findRegionById: '/regionApi/findById'
};

export default regionApi;

export function getRegionById (id) {
  return request({
    url: regionApi.findRegionById + '/' + id,
    method: 'get'
  });
}
