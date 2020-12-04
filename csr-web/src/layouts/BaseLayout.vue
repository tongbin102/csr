<template>
  <div id="components-page-header">
    <a-page-header style="border: 1px solid rgb(235, 237, 240)" @back="() => $router.go(-1)">
      <template slot="title">
        <span @click="handleClickHome">
          <a-icon type="home" />
        </span>
        <span>{{ globalTitle }}</span>
      </template>
      <template slot="extra">
        <div>
          <avatar-dropdown :menu="showMenu" :current-user="currentUser" />
        </div>
      </template>
      <!-- <a-divider></a-divider> -->
      <div class="content">
        <router-view />
      </div>
      <!-- <template slot="footer">
        <global-footer />
      </template> -->
    </a-page-header>
    <!-- <page-header-wrapper>
      <router-view />
    </page-header-wrapper> -->
  </div>
</template>
<script>
import store from '@/store';
import AvatarDropdown from '@/components/GlobalHeader/AvatarDropdown';
// import GlobalFooter from '@/components/GlobalFooter';
// import RightContent from '@/components/GlobalHeader/RightContent';

export default {
  name: 'StatisfactionLayout',
  components: {
    AvatarDropdown
    // GlobalFooter
    // RightContent
  },
  data () {
    return {
      globalTitle: '上汽通用五菱满意度评估体系',
      showMenu: false,
      currentUser: {}
    };
  },
  created () {
    this.$watch('$route', (val) => {
      const roles = store.getters.roles;
      const path = val.path;
      if (roles === 'admin' && path !== '/admin/upload') {
        this.showMenu = true;
      }
      // this.tabActiveKey = getActiveKey(val.path);
    });
    setTimeout(() => {
      this.currentUser = store.getters.userInfo;
    }, 1500);
  },
  methods: {
    handleClickHome () {
      const roles = store.getters.roles;
      if (roles === 'admin') {
        this.$router.push({ path: '/satisfaction/national' });
      } else {
        this.$router.push({ path: '/' });
      }
    }
  }
};
</script>
<style>
#components-page-header .ant-page-header-heading-title {
  font-size: 20px;
  line-height: 28px;
}

#components-page-header .ant-page-header-heading-extra {
  width: auto;
  float: right;
  margin-left: 0;
  text-align: right;
}

@media (max-width: 576px) {
  #components-page-header .ant-page-header-heading-title {
    font-size: 16px;
  }
}

.content {
  min-height: 800px;
}
</style>
