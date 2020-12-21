import request from '@/utils/request';

const questionApi = {
  getQuestionSurveyList: '/questionSurveyApi/findList',
  getQuestionMonitorList: '/questionMonitorApi/findList',
  getQuestionAssistanceList: '/questionAssistanceApi/findList',
  getQuestionComplainList: '/questionComplainApi/findList',
  getQuestionRescueList: '/questionRescueApi/findList',
  getQuestionDataList: '/questionDataApi/findList'
};

export default questionApi;

export function getQuestionSurveyList (parameter) {
  return request({
    url: questionApi.getQuestionSurveyList,
    method: 'get',
    params: parameter
  });
}

export function getQuestionMonitorList (parameter) {
  return request({
    url: questionApi.getQuestionMonitorList,
    method: 'get',
    params: parameter
  });
}

export function getQuestionAssistanceList (parameter) {
  return request({
    url: questionApi.getQuestionAssistanceList,
    method: 'get',
    params: parameter
  });
}

export function getQuestionComplainList (parameter) {
  return request({
    url: questionApi.getQuestionComplainList,
    method: 'get',
    params: parameter
  });
}

export function getQuestionRescueList (parameter) {
  return request({
    url: questionApi.getQuestionRescueList,
    method: 'get',
    params: parameter
  });
}

export function getQuestionDataList (parameter) {
  return request({
    url: questionApi.getQuestionDataList,
    method: 'get',
    params: parameter
  });
}
