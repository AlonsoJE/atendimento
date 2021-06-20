<template>
    <v-navigation-drawer app
      mini-variant
      permanent
      color="blue darken-4"
    >
      <v-list-item class="px-2 indigo darken-4" >
        <v-btn icon   color="white" to="/Home">
        <v-icon>mdi-home</v-icon>
      </v-btn>  
      </v-list-item>
      <v-divider></v-divider>
         <v-list-item class="px-2 indigo darken-4" >
        <v-btn icon  color="white" to="/Cadastro">
        <v-icon>mdi-folder-plus</v-icon>
      </v-btn>  
      </v-list-item>
      <v-divider></v-divider>
         <v-list-item class="px-2 indigo darken-4" >
        <v-btn icon  color="white" to="/Consulta">
        <v-icon>mdi-file-find</v-icon>
      </v-btn>  
      </v-list-item>
      <v-divider></v-divider>
    </v-navigation-drawer>
  
  
</template>

<script>
export default {
    name:"Navigation",
     data () {
      return {
        drawer: true,
        mini: true,
      }
    },

}
</script>

<style>
.texto {
    color: #f4f4f4 !important;
  }
</style>