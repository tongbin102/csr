// import request from '@/utils/request';

const fileApi = {
  uploadConfigFile: '/fileApi/uploadConfig',
  uploadScoreFile: '/fileApi/uploadScore'
};

export default fileApi;

// export function uploadConfigFile (parameter) {
//   console.log(parameter);

//   return request({
//     url: fileApi.uploadConfigFile,
//     method: 'post',
//     headers: {
//       'Content-Type': 'multipart/form-data'
//     },
//     params: parameter
//   });
// }
