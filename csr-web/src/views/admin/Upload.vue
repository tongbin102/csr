<template>
  <div>
    <a-breadcrumb>
      <a-breadcrumb-item>系統管理</a-breadcrumb-item>
      <a-breadcrumb-item><a href="">数据文件导入</a></a-breadcrumb-item>
    </a-breadcrumb>
    <a-divider></a-divider>
    <a-divider></a-divider>

    <a-form layout="inline">
      <a-form-item label="配置文件上传">
        <a-upload
          name="file"
          :multiple="false"
          accept=".xls, .xlsx"
          :customRequest="customRequestConfig"
          :beforeUpload="beforeUploadConfig"
          :showUploadList="false">

          <a-button type="primary">
            <a-icon type="upload" />上传
          </a-button>
        </a-upload>
        <!-- <a-button type="primary" icon="download" @click="handleDownLoad">下载模板</a-button> -->
      </a-form-item>

      <a-list item-layout="horizontal" :data-source="configFileData">
        <a-list-item slot="renderItem" slot-scope="item">
          <a-list-item-meta>
            <span slot="title">{{ item.title }}</span>
          </a-list-item-meta>
          <div>
            <a-icon v-if="item.status == ''" type="upload" />
            <a-icon v-if="item.status == 'loading'" type="loading" />
            <a-icon v-if="item.status == 'done'" type="check-circle" style="color: #52c41a" />
          </div>
        </a-list-item>
      </a-list>
    </a-form>
    <a-divider></a-divider>
    <a-form layout="inline">
      <a-form-item label="月份">
        <a-month-picker
          placeholder="选择月份"
          :allowClear="false"
          :format="monthFormat"
          :locale="locale"
          :defaultValue="moment(scorePeriod, monthFormat)"
          @change="onChangeScorePeriod" />
      </a-form-item>
      <!-- <a-form-item label="月份">
        <a-input v-model="scorePeriod" placeholder="请输入成绩月份" />
      </a-form-item> -->
      <a-form-item label="月度成绩文件上传">
        <a-upload
          name="file"
          :multiple="false"
          accept=".xls, .xlsx"
          :customRequest="customRequestScore"
          :beforeUpload="beforeUploadScore"
          :showUploadList="false">

          <a-button type="primary">
            <a-icon type="upload" />上传
          </a-button>
        </a-upload>
        <!-- <a-button type="primary" icon="download" @click="handleDownLoad">下载模板</a-button> -->
      </a-form-item>

      <a-list item-layout="horizontal" :data-source="scoreFileData">
        <a-list-item slot="renderItem" slot-scope="item">
          <a-list-item-meta>
            <span slot="title">{{ item.title }}</span>
          </a-list-item-meta>
          <div v-if="item.status=='error'">{{ item.errorInfo }}</div>
          <div>
            <a-icon v-if="item.status == ''" type="upload" />
            <a-icon v-if="item.status == 'loading'" type="loading" />
            <a-icon v-if="item.status == 'done'" type="check-circle" style="color: #52c41a" />
            <a-icon v-if="item.status == 'error'" type="close-circle" style="color: #FF4B4B" />
          </div>
        </a-list-item>
      </a-list>
    </a-form>
  </div>
</template>

<script>
import moment from 'moment';// 推荐在入口文件全局设置

export default {
  data () {
    const configFileData = [
      {
        title: '1. TS-1.  H5用户名',
        status: ''
      },
      {
        title: '2. TS-2.  店区域配置表',
        status: ''
      },
      {
        title: '3. TSS-1. 因子要素评价映射表',
        status: ''
      },
      {
        title: '4. TSS-2. 调研问卷评分规则',
        status: ''
      },
      {
        title: '5. TSS-3. 过程监控评分规则',
        status: ''
      },
      {
        title: '6. TSS-4. 服务助手评分规则',
        status: ''
      },
      {
        title: '7. TSS-7. 道路救援扣分规则',
        status: ''
      },
      {
        title: '8. TSS-8. 数据准确性扣分规则',
        status: ''
      }
    ];
    const scoreFileData = [
      {
        title: '1. TG1. 全国及各店分因子成绩',
        status: ''
      },
      {
        title: '2. TG2. 全国及各店分渠道成绩',
        status: ''
      },
      {
        title: '3. TG3. 经销商分项成绩',
        status: ''
      },
      {
        title: '4. TG4. 经销商投诉单',
        status: ''
      },
      {
        title: '5. TG5. 经销商因子要素成绩单',
        status: ''
      }
    ];
    return {
      configFileData,
      scoreFileData,
      locale: 'zh-CN',
      monthFormat: 'yyyyMM',
      scorePeriod: moment()
    };
  },
  methods: {
    moment,
    beforeUploadConfig (file) {
      console.log('before upload');
    },
    beforeUploadScore (file) {
      if (!this.scorePeriod) {
        this.$notification.error({
          message: '提示信息',
          description: '请选择成绩所属月份'
        });
        return false;
      }
    },
    // handleChange (info) {
    //   console.log(info.file.status);
    //   if (info.file.status === 'uploading') {
    //     let fileList = [...info.fileList];
    //     // 限制上传数量
    //     fileList = fileList.slice(-1);

    //     this.fileList = fileList;
    //   } else if (info.file.status === 'done') {
    //     // this.$message.success(`${info.file.name} file uploaded successfully`);
    //     this.$notification.success({
    //       message: '上传成功',
    //       description: '配置文件上传成功'
    //     });
    //   } else if (info.file.status === 'error') {
    //     // this.$message.error(`${info.file.name} file upload failed.`);
    //     this.$notification.error({
    //       message: '上传失败',
    //       description: '配置文件上传失败'
    //     });
    //   }
    // },
    customRequestConfig (data) {
      const form = new FormData();
      form.append('file', data.file);
      // 导入用户及店信息
      this.configFileData[0].status = 'loading';
      this.configFileData[1].status = 'loading';
      this.$http.post('/fileApi/importUsersAndStores', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.configFileData[0].status = 'done';
          this.configFileData[1].status = 'done';
        }
      });
      // 导入因子要素及对应题目详情
      this.configFileData[2].status = 'loading';
      this.configFileData[3].status = 'loading';
      this.configFileData[4].status = 'loading';
      this.configFileData[5].status = 'loading';
      this.configFileData[6].status = 'loading';
      this.configFileData[7].status = 'loading';
      this.$http.post('/fileApi/importRegulationsAndQuestions', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.configFileData[2].status = 'done';
          this.configFileData[3].status = 'done';
          this.configFileData[4].status = 'done';
          this.configFileData[5].status = 'done';
          this.configFileData[6].status = 'done';
          this.configFileData[7].status = 'done';
        }
      });
    },
    customRequestScore (data) {
      const form = new FormData();
      form.append('file', data.file);
      form.append('period', moment(this.scorePeriod).format(this.monthFormat));

      // 导入全国及各店分因子成绩
      this.scoreFileData[0].status = 'loading';
      this.$http.post('/fileApi/importScoresFactor', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.scoreFileData[0].status = 'done';
        }
      });
      // 导入全国及各店分渠道成绩
      this.scoreFileData[1].status = 'loading';
      this.$http.post('/fileApi/importScoresChannel', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.scoreFileData[1].status = 'done';
        }
      });
      // 导入经销商分项成绩
      this.scoreFileData[2].status = 'loading';
      this.$http.post('/fileApi/importScoresQuestion', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.scoreFileData[2].status = 'done';
        }
      });
      // 导入经销商投诉单
      this.scoreFileData[3].status = 'loading';
      this.$http.post('/fileApi/importQuestionComplain', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.scoreFileData[3].status = 'done';
        }
      });
      // 导入经销商因子要素成绩单
      this.scoreFileData[4].status = 'loading';
      this.$http.post('/fileApi/importRegulationScore', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.resCode === 200) {
          this.scoreFileData[4].status = 'done';
        }
      }).catch(error => {
        console.log(error);
        this.scoreFileData[4].errorInfo = '导入时出错';
        this.scoreFileData[4].status = 'error';
      });
    },
    handleDownLoad () {
      console.log('download');
    },
    onChangeScorePeriod (value) {
      console.log(value);
      this.scorePeriod = value;
    }
  }

};
</script>

<style>
</style>
