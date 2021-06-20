package teste.pratico.atendimento.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoFilter {

    private Long id;
    private String nome;
    private String especialidade;
    private String crm;

}
