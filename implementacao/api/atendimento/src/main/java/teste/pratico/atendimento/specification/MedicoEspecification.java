package teste.pratico.atendimento.specification;

import org.springframework.data.jpa.domain.Specification;
import teste.pratico.atendimento.entity.MedicoEntity;

public class MedicoEspecification {

    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String ESPECIALIDADE = "especialidade";
    private static final String CRM = "crm";


    public static Specification<MedicoEntity> equalId(Long id) {
        return (root, query, cb) -> cb.equal(root.get(ID), id);
    }

    public static Specification<MedicoEntity> isNotNullId(){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(ID)));
    }

    public static Specification<MedicoEntity> likeNome(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(NOME), value);
    }

    public static Specification<MedicoEntity> likeEspecialidade(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(ESPECIALIDADE), value);
    }

    public static Specification<MedicoEntity> equalsCrm(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(CRM), value);
    }

}
