  <template>
    <div>
      <v-row>
        <v-col sm="12" md="1" xl="2">
          <v-btn tile color="yellow" class="ml-2" to="/Cadastro">
          <v-icon>mdi-folder-plus</v-icon>Novo</v-btn>
        </v-col>
      <v-col sm="12" md="1" xl="2">
        <v-btn tile color="primary" class="ml-2" to="/Consulta">
        <v-icon>mdi-file-find</v-icon>Consultar</v-btn>
      </v-col>  
      </v-row>
    </div>
    
</template>

<script>
  export default {
    name:"Home",

   
  }
</script>

<style>

</style>