<template>
  <div class="main">
    <a-form id="formSubmit" class="user-layout-submit" ref="formSubmit" :form="form" @submit="handleSubmit">
      <a-alert v-if="isSubmitError" type="error" showIcon style="margin-bottom: 24px;" message="密码输入错误" />
      <a-form-item>
        <span>请设置您的专属密码</span>
      </a-form-item>
      <a-form-item>
        <a-input-password
          size="large"
          placeholder="密码"
          v-decorator="[
            'password',
            {
              rules: [
                {
                  required: true, message: '请输入密码'
                },
                {
                  min: 6, message: '密码长度必须大于6'
                },
                {
                  validator: validateToNextPassword
                }
              ],
              validateTrigger: ['blur']
            }
          ]">
          <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }" />
        </a-input-password>
      </a-form-item>
      <a-form-item>
        <a-input-password
          size="large"
          placeholder="确认密码"
          v-decorator="[
            'confirmPassword',
            {
              rules: [
                {
                  required: true, message: '请输入确认密码'
                },
                {
                  min: 6, message: '密码长度必须大于6'
                },
                {
                  validator: compareToFirstPassword
                }
              ],
              validateTrigger: ['blur']}
          ]"
          @blur="handleConfirmBlur">
          <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }" />
        </a-input-password>
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
import store from '@/store';
import { changeUserPassword } from '@/api/login';

export default {
  data () {
    return {
      confirmDirty: false,
      submitBtn: false,
      isSubmitError: false,
      form: this.$form.createForm(this, { name: 'changePassword' }),
      state: {
        submitBtn: false
      }
    };
  },
  methods: {
    compareToFirstPassword (rule, value, callback) {
      const form = this.form;
      if (value && value !== form.getFieldValue('password')) {
        // eslint-disable-next-line standard/no-callback-literal
        callback('密码必须与确认密码一致');
      } else {
        callback();
      }
    },
    validateToNextPassword (rule, value, callback) {
      const form = this.form;
      if (value && this.confirmDirty) {
        form.validateFields(['confirmPassword'], { force: true });
      }
      callback();
    },
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

      state.loginBtn = true;

      const validateFieldsKey = ['password', 'confirmPassword'];
      validateFields(validateFieldsKey, { force: true }, (err, values) => {
        if (!err) {
          const params = { ...values };
          params.username = store.getters.userInfo.username;
          console.log(params.username);
          params.password = values.password;
          params.confirmPassword = values.confirmPassword;
          changeUserPassword(params)
            .then(res => {
              if (res.resCode === 200) {
                this.changePasswordSuccess(res);
              } else {
                this.requestFailed(res);
              }
            })
            .catch(err => this.requestFailed(err))
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
    changePasswordSuccess (res) {
      // 延迟 1 秒显示欢迎信息
      setTimeout(() => {
        this.$notification.success({
          message: '成功',
          description: '密码修改成功！'
        });
        this.$router.push({ path: '/' });
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

<style lang="less" scoped>
.form-button .ant-btn {
  margin: 0 20px;
}
</style>
