<template>
  <div class="main">
    <a-form id="formSubmit" class="user-layout-submit" ref="formSubmit" :form="form" @submit="handleSubmit">
      <a-alert v-if="isSubmitError" type="error" showIcon style="margin-bottom: 24px;" message="邮箱输入错误" />
      <!-- <a-form-item>
        <span></span>
      </a-form-item> -->
      <a-form-item label="请填写需要找回密码的账户对应的邮箱">
        <a-input
          size="large"
          placeholder="请输入邮箱"
          v-decorator="[
            'email',
            {
              rules: [
                {
                  required: true,
                  type: 'email',
                  message: '请输入邮箱地址'
                }
              ],
              validateTrigger: [
                'change',
                'blur'
              ]
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item class="form-button" style="margin-top:24px; text-align: center;">
        <a-button
          size="large"
          type="primary"
          htmlType="submit"
          class="submit-button"
          :loading="state.submitBtn"
          :disabled="state.submitBtn">确认</a-button>
        <a-button size="large" type="default" @click="$router.go(-1)">返回</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { sendValidationEmail } from '@/api/login';

export default {
  data () {
    return {
      submitBtn: false,
      isSubmitError: false,
      form: this.$form.createForm(this, { name: 'recover' }),
      state: {
        submitBtn: false
      }
    };
  },
  methods: {
    handleConfirmBlur (e) {
      const value = e.target.value;
      this.confirmDirty = this.confirmDirty || !!value;
    },
    handleSubmit (e) {
      e.preventDefault();
      const {
        form: { validateFields },
        state
      } = this;

      state.recoverBtn = true;

      const validateFieldsKey = ['email'];
      validateFields(validateFieldsKey, { force: true }, (err, values) => {
        if (!err) {
          const params = { ...values };
          params.email = values.email;
          // 发送忘记密码邮件
          sendValidationEmail(params).then(res => {
            // console.log(res);
            this.sendMailSuccess(res);
          }).catch(err => this.requestFailed(err))
            .finally(() => {
              state.submitBtn = false;
            });
        } else {
          setTimeout(() => {
            state.submitBtn = false;
          }, 600);
        }
      });
    },
    sendMailSuccess (res) {
      // 延迟 1 秒显示欢迎信息
      setTimeout(() => {
        this.$notification.success({
          message: '成功',
          description: '已成功发送邮件！'
        });
      }, 1000);
      this.isLoginError = false;
    },
    requestFailed (err) {
      this.isSubmitError = true;
      this.$notification.error({
        message: '错误',
        description: ((err.response || {}).data || {}).message || '请求出现错误，请稍后再试',
        duration: 4
      });
    }
  }
};
</script>

<style>
.form-button .ant-btn {
  margin: 0 20px;
}
</style>
