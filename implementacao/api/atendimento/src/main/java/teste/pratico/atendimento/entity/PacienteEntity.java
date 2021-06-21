package teste.pratico.atendimento.entity;

import lombok.*;
import teste.pratico.atendimento.components.Endereco;
import teste.pratico.atendimento.constantes.ConstanteSexo;
import teste.pratico.atendimento.regex.RegexCenter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Table(name = "paciente")
public class PacienteEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", insertable = true, updatable = true, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", insertable = true, updatable = true, nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(value = EnumType.STRING)
    private ConstanteSexo sexo;

    @Embedded
    private Endereco endereco;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
//        RegexCenter.verifyName(nome);
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(ConstanteSexo sexo) {
        this.sexo = sexo;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
