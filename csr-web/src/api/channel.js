import request from '@/utils/request';

const channelApi = {
  getChannelListByCtype: '/channelApi/findListByCtype',
  getAllChannelList: 'channelApi/findAll'
};

export default channelApi;

/**
 * 获取所有考核项渠道
 * @param {*} parameter
 */
export function getAllChannelList () {
  return request({
    url: channelApi.getAllChannelList,
    method: 'get'
  });
}

/**
 * 获取所有考核项渠道
 * @param {*} parameter
 */
export function getChannelList1 () {
  return request({
    url: channelApi.getChannelListByCtype,
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
    url: channelApi.getChannelListByCtype,
    method: 'get',
    params: {
      ctype: 2
    }
  });
}
