package teste.pratico.atendimento.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Table(name = "ordem_servico")
public class OrdemServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "convenio", insertable = true, updatable = true, nullable = true)
    private String convenio;

    @Column(name = "observacao", insertable = true, updatable = true, nullable = true)
    private String observacao;

    @Column(name = "protocolo", insertable = true, updatable = false, nullable = false, unique = true)
    private String protocoloRetirada ;

    @Column(name = "data_retirada", insertable = true, updatable = true, nullable = false)
    private LocalDateTime retiradaExame ;

    @Column(name = "valor", insertable = true, updatable = true, nullable = false)
    private BigDecimal valor = BigDecimal.ZERO;

    @ManyToOne(targetEntity = PacienteEntity.class)
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    @ManyToOne(targetEntity = PostoColetaEntity.class)
    @JoinColumn(name = "id_posto")
    private PostoColetaEntity postoColeta;

    @ManyToOne(targetEntity = MedicoEntity.class)
    @JoinColumn(name = "id_medico")
    private MedicoEntity medico;

    // duvidavel
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ordem_servico_exame",
            joinColumns = @JoinColumn(name = "os_id"),
            inverseJoinColumns = @JoinColumn(name = "exame_id"))
    private List<ExameEntity> exames;

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setProtocoloRetirada(String protocoloRetirada) {
        this.protocoloRetirada = protocoloRetirada;
    }

    public void setRetiradaExame(LocalDateTime retiradaExame) {
        this.retiradaExame = retiradaExame;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public void setPostoColeta(PostoColetaEntity postoColeta) {
        this.postoColeta = postoColeta;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public void setExames(List<ExameEntity> exames) {
        this.exames = exames;
    }

}
