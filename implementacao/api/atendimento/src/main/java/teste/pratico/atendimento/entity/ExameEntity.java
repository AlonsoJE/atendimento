package teste.pratico.atendimento.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Table(name = "exame")
public class ExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "descricao", insertable = true, updatable = false, nullable = false)
    private String descricao;

    @Column(name = "tempo_exame", insertable = true, updatable = true, nullable = false)
    private Integer tempoExameEmHoras;

    @Column(name = "preco", insertable = true, updatable = false, nullable = false)
    private BigDecimal preco = BigDecimal.ZERO;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTempoExameEmHoras(Integer tempoExameEmHoras) {
        this.tempoExameEmHoras = tempoExameEmHoras;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
