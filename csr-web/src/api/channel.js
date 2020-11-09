import request from '@/utils/request';

const channelApi = {
  getAll: '/channelApi/findAll'
};

export default channelApi;

export function getAllChannel (parameter) {
  return request({
    url: channelApi.getAll,
    method: 'get',
    params: parameter
  });
}
