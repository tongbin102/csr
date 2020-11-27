<template>
  <a-result status="404" title="404" sub-title="Sorry, the page you visited does not exist.">
    <template #extra>
      <a-button type="primary" @click="toHome">
        Back Home
      </a-button>
    </template>
  </a-result>
</template>

<script>
export default {
  name: 'Exception404',
  methods: {
    toHome () {
      // console.log(this);
      // this.$router.push('/aaa');

      // this.$router.push({ path: '/satisfaction/national' });
      this.$router.push({ path: '/' });
      console.log(333);
    }
  }
};
</script>
