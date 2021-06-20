package teste.pratico.atendimento.specification;

import org.springframework.data.jpa.domain.Specification;
import teste.pratico.atendimento.entity.ExameEntity;

public class ExameEspecification {

    private static final String ID = "id";
    private static final String DESCRICAO = "descricao";
    private static final String PRECO = "preco";
    private static final String TEMPO = "tempoExameEmHoras";

    public static Specification<ExameEntity> equalId(Long id) {
        return (root, query, cb) -> cb.equal(root.get(ID), id);
    }

    public static Specification<ExameEntity> isNotNullId(){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(ID)));
    }

    public static Specification<ExameEntity> likeDescricao(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(DESCRICAO), value);
    }

    public static Specification<ExameEntity> likePreco(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(PRECO), value);
    }

    public static Specification<ExameEntity> equalsTempo(Integer value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(TEMPO), value);
    }


}
