import request from '@/utils/request';

const cityApi = {
  findCityById: '/cityApi/findById'
};

export default cityApi;

export function getCityById (id) {
  return request({
    url: cityApi.findCityById + '/' + id,
    method: 'get'
  });
}
