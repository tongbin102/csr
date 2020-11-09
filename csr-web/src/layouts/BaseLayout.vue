<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
    >
      <template slot="extra">
        <!-- <right-content></right-content> -->
        <!-- <right-content :top-menu="settings.layout === 'topmenu'" :is-mobile="isMobile" :theme="settings.theme" /> -->
      </template>
      <template slot="footer">
        <global-footer />
      </template>
      <div class="content">
        <router-view />
      </div>
    </a-page-header>
  </div>
</template>
<script>
import GlobalFooter from '@/components/GlobalFooter';
import RightContent from '@/components/GlobalHeader/RightContent';

export default {
  name: 'StatisfactionLayout',
  components: {
    GlobalFooter,
    RightContent
  }
};
</script>
<style scoped>
tr:last-child td {
  padding-bottom: 0;
}
#components-page-header-demo-responsive .content {
  display: flex;
}
#components-page-header-demo-responsive .ant-statistic-content {
  font-size: 20px;
  line-height: 28px;
}
@media (max-width: 576px) {
  #components-page-header-demo-responsive .content {
    display: block;
  }

  #components-page-header-demo-responsive .main {
    width: 100%;
    margin-bottom: 12px;
  }

  #components-page-header-demo-responsive .extra {
    width: 100%;
    margin-left: 0;
    text-align: left;
  }
}

.content{
  min-height:800px;
}
</style>
