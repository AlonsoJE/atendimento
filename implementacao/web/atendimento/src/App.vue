<template>
<div>
<v-app>
    <Navigation/>
    <Header/>
    <Content/> 
    
</v-app>
</div>
</template>

<script>

import Content from "./templates/Content.vue"
import Header from "./templates/Header.vue"

import Navigation from "./templates/Navigation.vue"

export default {
  name: 'App',
  components:{Content, Header,Navigation},

  data: () => ({
    //
  }),
};
</script>
