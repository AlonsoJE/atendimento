<template>
  <div>
    <v-row class="mt-4">
     <v-btn tile color="secondary" class="ml-7" @click="exibirTabelaOs = false, exibirFormCadastroOs = false,exibirFormFiltro = false">
      <v-icon left>mdi-eraser</v-icon>Limpar 
     </v-btn>

    <v-btn tile color="primary" class="ml-2" @click="exibirTabelaOs = true, buscarOs()">
     <v-icon>mdi-magnify</v-icon>
      Pesquisar
    </v-btn>

    <v-btn tile color="secondary" class="ml-9" outlined @click="exibirFormFiltro = !exibirFormFiltro">
      <v-icon left>mdi-pencil</v-icon>
      Filtar por
    </v-btn>    
  </v-row>

    <!-- formulario de filtros -->
    <v-form class="mt-7" v-show="exibirFormFiltro">
      <hr/>
    <v-container>
      <v-row>
          <v-col col sm="12" md="4">
                <v-btn tile color="secondary" class="ml-7" outlined @click="resetarFiltros()">
                  <v-icon left>mdi-filter-off</v-icon>Limpar Filtros
                </v-btn>
          </v-col>
          <v-col col sm="12" md="4">
            <v-text-field label="Protocolo" v-model="protocoloFilter" dense></v-text-field>
          </v-col>
      </v-row>
    </v-container>
    <hr/>
  </v-form> 


    <!-- dialogo os -->
 <v-row justify="center">
    <v-dialog v-model="exibirDialogoAlterarOs">
      <v-card>
        <v-card-title>
          <span class="text-h5">Ordem de Serviço - {{ordemSelecionada.protocoloRetirada}} - Valor R$ {{ordemSelecionada.valor}}</span>
        </v-card-title>

        <!-- Card Paciente -->
        <v-card>
          <v-card-title>
            <span class="text-h9">Paciente</span>
          </v-card-title>
          <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4"              >
                <v-text-field label="Nome" readonly v-model="ordemSelecionada.paciente.nome"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="2"              >
                <v-text-field label="Nascimento" readonly v-model="ordemSelecionada.paciente.dataNascimento"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3"              >
                <v-text-field label="Convenio" readonly v-model="ordemSelecionada.convenio"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4"              >
                <v-text-field label="Rua" readonly v-model="ordemSelecionada.paciente.endereco.logradouro"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="1"              >
                <v-text-field label="Numero" readonly v-model="ordemSelecionada.paciente.endereco.numero"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        </v-card>

        <!-- Card posto -->
         <v-card>
          <v-card-title>
            <span class="text-h9">Local de coleta</span>
          </v-card-title>
          <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4"              >
                <v-text-field label="Nome" readonly v-model="ordemSelecionada.postoColeta.descricao"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4"              >
                <v-text-field label="Rua" readonly v-model="ordemSelecionada.postoColeta.endereco.logradouro"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="1"              >
                <v-text-field label="Numero" readonly v-model="ordemSelecionada.postoColeta.endereco.numero"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        </v-card>

         <!-- Card medico -->
         <v-card>
          <v-card-title>
            <span class="text-h9">Médico</span>
          </v-card-title>
          <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4"              >
                <v-text-field label="Nome" readonly v-model="ordemSelecionada.medico.nome"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        </v-card>

         <!-- Card exame -->
         <v-card>
          <v-card-title>
            <span class="text-h9">Exames</span>
          </v-card-title>
          <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="6">
                  <v-data-table :headers="camposTabelaExame" :items="seletorExames" class="elevation-1" ></v-data-table>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        </v-card>
        <v-card-actions>
          <small>* Indica que o campo é obrigatório</small>
          <v-spacer></v-spacer>
          <v-btn color="red darken-1" text @click="exibirDialogoAlterarOs = false">Close</v-btn>
        </v-card-actions>
      </v-card> 
    </v-dialog>
  </v-row>

  <!-- tabela os -->
  <v-data-table
    :headers="camposTabela"
    :items="ordemServico"
    class="elevation-1 mt-9"
    v-show="exibirTabelaOs"
  >
    <template>
      <v-toolbar flat>
        <v-toolbar-title>Ordens de Serviço</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
    </template>
      <template v-slot:[`item.exames`]="{ item }">
          <span v-for="s in item.exames" :key="s.exames">
            {{ `(${s.descricao})`}}
          </span>
      </template>
    <template v-slot:[`item.actions`]="{ item }">
        <v-icon small class="mr-2" @click="exibirDialogoAlterarOs=!exibirDialogoAlterarOs, pegarLinha(item)">mdi-eye</v-icon>
    </template>
  </v-data-table>
</div>
</template>

<script>
export default {
    name:"Consulta",
    data: function () {
        return{
            ordemSelecionada:{
              valor:"",
              paciente:{
                nome:"",
                dataNascimento:"",
                sexo:"",
                endereco:{
                  logradouro:"",
                  numero:""
                }
              },
              postoColeta:{
                descricao:"",
                endereco:{
                  logradouro:"",
                  numero:""
                }
              },
              medico:{
                nome:"",
                especialidade:"",
                crm:""
              },
              exames:[{
                
              }]
            },
            seletorExames:[],
            totalElementoPagina:"",
            expanded:[],
            page:1,
            itemsPerPage: 10,
            exibirFormFiltro: false,
            exibirTabelaOs: false,
            exibirDialogoAlterarOs: false,
            protocoloFilter: "",
            ordemServico: [],
            camposTabela:[
                {value:"id", text: "Codigo"},
                {value:"protocoloRetirada", text: "Protocolo"},
                {value:"paciente.nome",text:"Paciente"},
                {value:"convenio", text: "Convenio"},
                {value:"observacao", text: "Observação"},
                {value:"postoColeta.descricao", text: "Posto"},
                {value:"medico.nome", text: "Médico"},
                {value:"exames", text: "Exames"},
                {value:"actions", text: "Ações"}
            ],
            camposTabelaExame:[
              {value:"descricao", text:"Descrição"},
              {value:"preco", text: "Valor R$"}
            ]

        }
    },
    mounted(){
      
    },
    methods:{
        resetarFiltros(){
          this.protocoloFilter = "";
        } ,     
        pegarLinha(item){
            this.ordemSelecionada = item;
            this.seletorExames = item.exames.map( exames =>{
              return{
                ...exames
              }
            })
            console.log(this.seletorExames)

        },
        buscarOs(){         
        
            let protocolo = (this.protocoloFilter) ? this.protocoloFilter : ""
            this.$http
                .post('ordens-servico/filter',{
                    protocolo
                })
                .then( resposta =>{
                    this.ordemServico = resposta.data.content
                    this.totalElementoPagina = resposta.data.totalElementoPagina
                    console.log(this.ordemServico = resposta.data.content)
                })
                .catch(erro=>{
                    console.log(erro)
                })
        }
    }
}
</script>

<style>

</style>