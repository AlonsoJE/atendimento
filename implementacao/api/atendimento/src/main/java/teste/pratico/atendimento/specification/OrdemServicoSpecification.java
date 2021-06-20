package teste.pratico.atendimento.specification;

import org.springframework.data.jpa.domain.Specification;
import teste.pratico.atendimento.entity.OrdemServicoEntity;

public class OrdemServicoSpecification {

    private static final String ID = "id";
    private static final String PROTOCOLO = "protocoloRetirada";

    public static Specification<OrdemServicoEntity> equalId(Long id) {
        return (root, query, cb) -> cb.equal(root.get(ID), id);
    }

    public static Specification<OrdemServicoEntity> isNotNullId(){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(ID)));
    }

    public static Specification<OrdemServicoEntity> likeProtocolo(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(PROTOCOLO), value);
    }

}
