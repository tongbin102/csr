import request from '@/utils/request';

const elementApi = {
  getElementById: '/elementApi/findById'
};

export default elementApi;

export function getElementById (id) {
  return request({
    url: elementApi.getElementById + '/' + id,
    method: 'get'
  });
}
