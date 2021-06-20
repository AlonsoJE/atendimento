package teste.pratico.atendimento.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameFilter {

    private Long id;
    private String descricao;
    private String preco;
    private Integer tempo;

}
