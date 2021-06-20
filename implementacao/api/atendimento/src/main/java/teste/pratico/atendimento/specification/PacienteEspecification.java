package teste.pratico.atendimento.specification;

import org.springframework.data.jpa.domain.Specification;
import teste.pratico.atendimento.entity.PacienteEntity;

public class PacienteEspecification {

    private static final String ID = "id";
    private static final String NOME = "nome";

    public static Specification<PacienteEntity> equalId(Long id) {
        return (root, query, cb) -> cb.equal(root.get(ID), id);
    }

    public static Specification<PacienteEntity> isNotNullId(){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(ID)));
    }

    public static Specification<PacienteEntity> likeNome(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(NOME), value);
    }

}
