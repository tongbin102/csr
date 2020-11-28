import request from '@/utils/request';

const cityApi = {
  findCityByCode: '/cityApi/findByCode'
};

export default cityApi;

export function getCityByCode (code) {
  return request({
    url: cityApi.findCityByCode,
    method: 'get',
    params: {
      code: code
    }
  });
}
