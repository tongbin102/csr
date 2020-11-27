<template>
  <div id="components-page-header">
    <a-page-header :title="globalTitle" style="border: 1px solid rgb(235, 237, 240)" @back="() => $router.go(-1)">
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
      showMenu: true,
      currentUser: {}
    };
  },
  mounted () {
    setTimeout(() => {
      this.currentUser = store.getters.userInfo;
    }, 1500);
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
