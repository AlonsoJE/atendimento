package teste.pratico.atendimento.entity;

import lombok.*;
import teste.pratico.atendimento.components.Endereco;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Table(name = "posto_coleta")
public class PostoColetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "descricao", insertable = true, updatable = true, nullable = false)
    private String descricao;

    @Embedded
    private Endereco endereco;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
