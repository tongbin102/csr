import request from '@/utils/request';

const regionApi = {
  findRegionByCode: '/regionApi/findByCode'
};

export default regionApi;

export function getRegionByCode (code) {
  return request({
    url: regionApi.findRegionByCode,
    method: 'get',
    params: {
      code: code
    }
  });
}
