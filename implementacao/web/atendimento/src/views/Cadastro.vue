<template>
<div>
  <v-alert dense text type="success" dismissible v-model="exibirAlerta">Registro inserido</v-alert>
  <v-alert dense text type="error" dismissible v-model="exibirAlertaErro">Ocorreu um erro</v-alert>
    <v-btn tile color="secondary" class="ml-7" @click="exibirTabelaOs = false, exibirFormCadastroOs = false, resetarTudo()">
      <v-icon left>mdi-eraser</v-icon>Limpar 
    </v-btn>
     <v-btn tile color="yellow" class="ml-2" @click="exibirFormCadastroOs = true">
      <v-icon left>mdi-face-agent</v-icon>
      Cadastrar
    </v-btn>


      <!-- formulario cadastro os -->
  <v-card>
    <v-form v-show="exibirFormCadastroOs" class="mt-8">
    <v-container>
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="ordemServico.convenio"
              :counter="30"
              label="Convênio"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="6">
            <v-text-field
              v-model="ordemServico.observacao"
              :counter="100"
              label="Observação"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
            <v-col cols="12" md="6">
              <v-text-field
              disabled
              v-model="ordemServico.paciente.nome"
              label="Paciente"
              required
            ></v-text-field>
            </v-col>
            <v-col>
              <v-btn tile color="primary" class="ml-2" @click="exibirDialogoPaciente = true">
                <v-icon left>mdi-magnify</v-icon>Buscar Paciente</v-btn>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12" md="6">
              <v-text-field
              disabled
              v-model="ordemServico.medico.nome"
              label="Médico"
              required
            ></v-text-field>
            </v-col>
            <v-col>
              <v-btn tile color="primary" class="ml-2" @click="exibirDialogoMedico = true">
                <v-icon left>mdi-magnify</v-icon>Buscar Medico</v-btn>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12" md="6">
              <v-text-field
              disabled
              v-model="ordemServico.postoColeta.descricao"
              label="Posto de Coleta"
              required
            ></v-text-field>
            </v-col>
            <v-col>
              <v-btn tile color="primary" class="ml-2" @click="exibirDialogoPosto = true">
                <v-icon left>mdi-magnify</v-icon>Buscar Posto de Coleta</v-btn>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12" md="6">
              <v-row align="center">
                 <v-col cols="12">   
                  <v-data-table v-show="exibirTabelaExamesSelecionados" :headers="camposTabelaExamesSelecionados" :items="examesSelecionados" hide-default-footer hide-default-header class="elevation-1" ></v-data-table>
                 </v-col>
              </v-row>
            </v-col>
            <v-col>
              <v-btn tile color="primary" class="ml-2" @click="exibirDialogoExames = true, exibirTabelaExamesSelecionados=true">
                <v-icon left>mdi-magnify</v-icon>Buscar Exames</v-btn>
            </v-col>
            <v-col>
              <v-btn tile color="error" class="ml-2" @click="removerExamesNoObjetoDeEnvio()">
                <v-icon left>mdi-close-box</v-icon>Remover Exame</v-btn>
            </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="6"></v-col>
          <v-col cols="12" md="4">
            <v-btn tile color="success" class="ml-2" @click="lancarOs()" :disabled="!this.exibirBotaoCadastrar">
              <v-icon left>mdi-check</v-icon>Cadastrar</v-btn>
          </v-col>
          
        </v-row>
      </v-container>
    </v-form>
  </v-card>

  <!-- dialogo para carregar pacientes -->
  <div class="text-center">
    <v-dialog v-model="exibirDialogoPaciente">
      <v-card>
        <v-container>
          <v-container>
            <v-row>
                <v-col col sm="12" md="3">
                      <v-btn tile color="secondary" class="ml-7" outlined @click="limparFiltrosPaciente()">
                        <v-icon left>mdi-filter-off</v-icon>Limpar Filtros</v-btn>
                </v-col>
                <v-col sm="12" md="3">
                    <v-btn tile color="primary" class="ml-2" @click="exibirTabelaPaciente = true, buscarPacientes()">
                      <v-icon>mdi-magnify</v-icon>Pesquisar</v-btn>
                </v-col>               
            </v-row>
            <v-row>
                <v-col col sm="12" md="2">
                  <v-text-field label="Identificador" v-model="pacienteFiltro.id" dense></v-text-field>
                </v-col>
                <v-col col sm="12" md="7">
                  <v-text-field label="Nome" v-model="pacienteFiltro.nome" dense></v-text-field>
                </v-col>
            </v-row>
        </v-container>
          <v-row>
            <v-col cols="12" sm="12" md="12">
              <v-data-table :headers="camposTabelaPaciente" :items="listaPacientes" v-show="exibirTabelaPaciente" class="elevation-1" >
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="exibirDialogoPaciente=!exibirDialogoPaciente, adicionarPacienteNoObjetoDeEnvio(item)">mdi-hand</v-icon>
                  </template>
              </v-data-table>
            </v-col>  
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

  <!-- dialogo para carregar medicos -->
  <div class="text-center">
    <v-dialog v-model="exibirDialogoMedico">
      <v-card>
        <v-container>
          <v-container>
            <v-row>
                <v-col col sm="12" md="3">
                      <v-btn tile color="secondary" class="ml-7" outlined @click="limparFiltrosMedico()">
                        <v-icon left>mdi-filter-off</v-icon>Limpar Filtros</v-btn>
                </v-col>
                <v-col sm="12" md="3">
                    <v-btn tile color="primary" class="ml-2" @click="exibirTabelaMedicos = true, buscarMedicos()">
                      <v-icon>mdi-magnify</v-icon>Pesquisar</v-btn>
                </v-col>               
            </v-row>
            <v-row>
                <v-col col sm="12" md="2">
                  <v-text-field label="Identificador" v-model="medicoFiltro.id" dense></v-text-field>
                </v-col>
                <v-col col sm="12" md="7">
                  <v-text-field label="Nome" v-model="pacienteFiltro.nome" dense></v-text-field>
                </v-col>
            </v-row>
            <v-row>
               <v-col col sm="12" md="4">
                  <v-text-field label="CRM" v-model="medicoFiltro.crm" dense></v-text-field>
                </v-col>
            </v-row>
        </v-container>
          <v-row>
            <v-col cols="12" sm="12" md="12">
              <v-data-table :headers="camposTabelaPaciente" :items="listaMedicos" v-show="exibirTabelaMedicos" class="elevation-1" >
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="exibirDialogoMedico=!exibirDialogoMedico, adicionarMedicoNoObjetoDeEnvio(item)">mdi-hand</v-icon>
                  </template>
              </v-data-table>
            </v-col>  
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>


   <!-- dialogo para carregar postos de coleta -->
  <div class="text-center">
    <v-dialog v-model="exibirDialogoPosto">
      <v-card>
        <v-container>
          <v-container>
            <v-row>
                <v-col col sm="12" md="3">
                      <v-btn tile color="secondary" class="ml-7" outlined @click="limparFiltrosPosto()">
                        <v-icon left>mdi-filter-off</v-icon>Limpar Filtros</v-btn>
                </v-col>
                <v-col sm="12" md="3">
                    <v-btn tile color="primary" class="ml-2" @click="exibirTabelaPosto = true, buscarPostos()">
                      <v-icon>mdi-magnify</v-icon>Pesquisar</v-btn>
                </v-col>               
            </v-row>
            <v-row>
                <v-col col sm="12" md="2">
                  <v-text-field label="Identificador" v-model="postoFiltro.id" dense></v-text-field>
                </v-col>
                <v-col col sm="12" md="7">
                  <v-text-field label="Nome" v-model="postoFiltro.nome" dense></v-text-field>
                </v-col>
            </v-row>
        </v-container>
          <v-row>
            <v-col cols="12" sm="12" md="12">
              <v-data-table :headers="camposTabelaPosto" :items="listaPostos" v-show="exibirTabelaPosto" class="elevation-1" >
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="exibirDialogoPosto=!exibirDialogoPosto, adicionarPostoNoObjetoDeEnvio(item)">mdi-hand</v-icon>
                  </template>
              </v-data-table>
            </v-col>  
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

  <!-- dialogo para carregar exames -->
  <div class="text-center">
    <v-dialog v-model="exibirDialogoExames">
      <v-card>
        <v-container>
          <v-container>
            <v-row>
                <v-col col sm="12" md="3">
                      <v-btn tile color="secondary" class="ml-7" outlined @click="limparFiltrosExame()">
                        <v-icon left>mdi-filter-off</v-icon>Limpar Filtros</v-btn>
                </v-col>
                <v-col sm="12" md="3">
                    <v-btn tile color="primary" class="ml-2" @click="exibirTabelaExames = true, buscarExames()">
                      <v-icon>mdi-magnify</v-icon>Pesquisar</v-btn>
                </v-col>               
            </v-row>
            <v-row>
                <v-col col sm="12" md="2">
                  <v-text-field label="Identificador" v-model="exameFiltro.id" dense></v-text-field>
                </v-col>
                <v-col col sm="12" md="7">
                  <v-text-field label="Descricao" v-model="exameFiltro.nome" dense></v-text-field>
                </v-col>
            </v-row>
            <v-row>
               <v-col col sm="12" md="2">
                  <v-text-field label="Preço" v-model="exameFiltro.preco" dense></v-text-field>
                </v-col>
                <v-col col sm="12" md="7">
                  <v-text-field label="Tempo" v-model="exameFiltro.tempo" dense></v-text-field>
                </v-col>
            </v-row>
        </v-container>
          <v-row>
            <v-col cols="12" sm="12" md="12">
              <v-data-table :headers="camposTabelaExame" :items="listaExames" v-show="exibirTabelaExames" class="elevation-1" >
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="exibirDialogoExames=!exibirDialogoExames, adicionarExamesNoObjetoDeEnvio(item), habilitarBotaoCadastrar()">mdi-hand</v-icon>
                  </template>
              </v-data-table>
            </v-col>  
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

</div>
</template>

<script>
export default {
    name:"Cadastro",
    data: function () {
        return{
        totalElementoPagina: "",
        exibirDialogoExames: false,
        exibirDialogoPosto: false,
        exibirDialogoMedico: false,
        exibirDialogoPaciente: false,
        exibirFormCadastroOs: false,

        listaPacientes:[],
        listaMedicos:[],
        listaPostos:[],
        listaExames:[],
        examesSelecionados:[],

        pacienteFiltro:{},
        medicoFiltro:{},
        postoFiltro:{},
        exameFiltro:{},
        id:{},

        exibirBotaoCadastrar: false,
        exibirAlerta: false,
        exibirAlertaErro: false,
        exibirTabelaPaciente: false,
        exibirTabelaMedicos: false,
        exibirTabelaPosto: false,
        exibirTabelaExames:false,
        exibirTabelaExamesSelecionados: false,
        ordemServico:{
          convenio:"",
          observacao:"",
          paciente:{id:""},
          postoColeta:{id:""},
          medico:{id:""},
          exames:[{}]
        },
        camposTabelaPaciente:[
            {value:"id", text: "Codigo"},
            {value:"nome", text: "Nome"},
            {value:"actions", text: "Ações"}
        ],
            camposTabelaPosto:[
            {value:"id", text: "Codigo"},
            {value:"descricao", text: "Descricao"},
            {value:"actions", text: "Ações"}
        ],
            camposTabelaExame:[
            {value:"id", text: "Codigo"},
            {value:"descricao", text: "Descricao"},
            {value:"preco", text: "Preço"},
            {value:"tempoExameEmHoras", text: "Resultado em Horas"},
            {value:"actions", text: "Ações"}
        ],
         camposTabelaExamesSelecionados:[
            {value:"descricao", text: "Descricao"},
            {value:"preco", text: "Preço"},
            {value:"actions", text: "Ações"}
        ]
        }
    },
    methods:{
        habilitarBotaoCadastrar(){
          this.exibirBotaoCadastrar = true              
        } ,    
        resetarTudo(){
            this.exibirBotaoCadastrar = false;
            this.exibirFormCadastroOs = false;
            this.exibirTabelaExamesSelecionados = false;
            this.exibirTabelaPaciente = false;
            this.exibirTabelaMedicos = false;
            this.exibirTabelaPosto = false;
            this.exibirTabelaExames = false;
            this.ordemServico = {
            convenio:"",
            observacao:"",
            paciente:{id:""},
            postoColeta:{id:""},
            medico:{id:""},
            exames:[{}],
        },
            this.examesSelecionados = [],
            this.listaPacientes =[],
            this.listaMedicos=[],
            this.listaPostos=[],
            this.listaExames=[],
            this.limparFiltrosPaciente();
            this.limparFiltrosMedico();
            this.limparFiltrosPosto();
            this.limparFiltrosExame();
        },

        limparFiltrosPaciente(){
          this.pacienteFiltro = {}
        },
        limparFiltrosMedico(){
          this.medicoFiltro = {}
        },
        limparFiltrosPosto(){
          this.postoFiltro = {}
        },
        limparFiltrosExame(){
          this.exameFiltro = {}
        },

        alertaSucesso(){
          this.exibirAlerta = true;
          setInterval(()=>{
            this.exibirAlerta = false
          }, 4000);
        },
        alertaErro(){
          this.exibirAlertaErro = true;
          setInterval(()=>{
            this.exibirAlerta = false
          }, 4000);
        },
        adicionarPacienteNoObjetoDeEnvio(item){
            this.ordemServico.paciente.id = item.id;
            this.ordemServico.paciente.nome = item.nome;
        },
        adicionarMedicoNoObjetoDeEnvio(item){
            this.ordemServico.medico.id = item.id;
            this.ordemServico.medico.nome = item.nome;
        },
        adicionarPostoNoObjetoDeEnvio(item){
            this.ordemServico.postoColeta.id = item.id;
            this.ordemServico.postoColeta.descricao = item.descricao; 
        },
        adicionarExamesNoObjetoDeEnvio(item){
            this.ordemServico.exames.push(item);
            this.examesSelecionados.push(item);
        },
        removerExamesNoObjetoDeEnvio(){
          this.ordemServico.exames.pop();
          this.examesSelecionados.pop();
        },
      buscarPacientes(){

          let id = this.pacienteFiltro.id;
          let nome = this.pacienteFiltro.nome;

            this.$http
                .post('pacientes/filter', {
                    id, nome
                })
                .then( resposta =>{
                    this.listaPacientes = resposta.data.content
                    this.totalElementoPagina = resposta.data.totalElementoPagina
                })
                .catch(erro=>{
                    console.log(erro)
                    this.alertaErro();
                })
      },
        buscarMedicos(){

          let id = this.medicoFiltro.id;
          let nome = this.medicoFiltro.nome;
          let crm = this.medicoFiltro.crm;

            this.$http
                .post('medicos/filter', {
                    id, nome, crm
                })
                .then( resposta =>{
                    this.listaMedicos = resposta.data.content
                    this.totalElementoPagina = resposta.data.totalElementoPagina
                })
                .catch(erro=>{
                    console.log(erro)
                    this.alertaErro();
                })
      },
      buscarPostos(){
           let id = this.postoFiltro.id;
          let nome = this.postoFiltro.descricao;

            this.$http
                .post('postos-coleta/filter', {
                    id, nome
                })
                .then( resposta =>{
                    this.listaPostos = resposta.data.content
                    this.totalElementoPagina = resposta.data.totalElementoPagina
                })
                .catch(erro=>{
                    console.log(erro)
                    this.alertaErro();
                })
      },
            buscarExames(){
           let id = this.exameFiltro.id;
           let descricao = this.exameFiltro.descricao;
           let preco = this.exameFiltro.preco;
           let tempo = this.exameFiltro.tempo;

            this.$http
                .post('exames/filter', {
                    id, descricao, preco, tempo
                })
                .then( resposta =>{
                    this.listaExames = resposta.data.content
                    this.totalElementoPagina = resposta.data.totalElementoPagina
                })
                .catch(erro=>{
                    console.log(erro)
                    this.alertaErro();
                })
      },
          lancarOs(){

            this.ordemServico.exames.shift();

            this.$http
              .post('ordens-servico', this.ordemServico,{
              })
              .then( resposta =>{                 
                  
                  let protocolo = resposta.data.protocoloRetirada;

                    // emitir report
                    this.$http
                      .post('ordens-servico/download',{
                        protocolo
                      }).then(resposta =>{
                        

                       var arrrayBuffer = base64ToArrayBuffer(resposta.data);
                      function base64ToArrayBuffer(base64) {
                      var binaryString = window.atob(base64);
                      var binaryLen = binaryString.length;
                      var bytes = new Uint8Array(binaryLen);
                          for (var i = 0; i < binaryLen; i++) {
                                var ascii = binaryString.charCodeAt(i);
                              bytes[i] = ascii;
                          }
                      return bytes;
                      }
                      var blob = new Blob([arrrayBuffer], {
                        type: "application/pdf"
                      });
                      var link = window.URL.createObjectURL(blob);
                      window.open(link);

                      }).catch(erro=>{
                        console.log(erro)
                        this.alertaErro();
                    })
          
                 this.alertaSucesso();
                 this.resetarTudo();
              }).catch(erro =>{
                console.log(erro)
                this.alertaErro();
              })
          },
       
            
    }
}
</script>

<style>

</style>