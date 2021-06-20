package teste.pratico.atendimento.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Table(name = "medico")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", insertable = true, updatable = true, nullable = false)
    private String nome;

    @Column(name = "especialidade", insertable = true, updatable = true, nullable = false)
    private String especialidade;

    @Column(name = "crm", insertable = true, updatable = true, nullable = false)
    private String crm;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

}
