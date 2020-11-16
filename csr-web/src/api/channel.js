import request from '@/utils/request';

const channelApi = {
  getListByCtype: '/channelApi/findListByCtype'
};

export default channelApi;

/**
 * 获取所有考核项渠道
 * @param {*} parameter
 */
export function getChannelList1 () {
  return request({
    url: channelApi.getListByCtype,
    method: 'get',
    params: {
      ctype: 1
    }
  });
}

/**
 * 获取所有扣分项渠道
 * @param {*} parameter
 */
export function getChannelList2 () {
  return request({
    url: channelApi.getListByCtype,
    method: 'get',
    params: {
      ctype: 2
    }
  });
}
