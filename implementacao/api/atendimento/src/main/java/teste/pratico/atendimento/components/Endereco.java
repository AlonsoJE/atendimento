package teste.pratico.atendimento.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    @Column(name = "logradouro", insertable = true, updatable = true, nullable = false)
    private String logradouro;

    @Column(name = "numero", insertable = true, updatable = true, nullable = false)
    private String numero;

}
