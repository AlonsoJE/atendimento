package teste.pratico.atendimento.specification;

import org.springframework.data.jpa.domain.Specification;
import teste.pratico.atendimento.entity.PostoColetaEntity;

public class PostoColetaEspecification {

    private static final String ID = "id";
    private static final String DESCRICAO = "descricao";

    public static Specification<PostoColetaEntity> equalId(Long id) {
        return (root, query, cb) -> cb.equal(root.get(ID), id);
    }

    public static Specification<PostoColetaEntity> isNotNullId(){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(ID)));
    }

    public static Specification<PostoColetaEntity> likeDescricao(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(DESCRICAO), value);
    }

}
