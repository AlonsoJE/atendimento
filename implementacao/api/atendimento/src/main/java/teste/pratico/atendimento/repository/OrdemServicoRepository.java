package teste.pratico.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import teste.pratico.atendimento.entity.OrdemServicoEntity;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long>, JpaSpecificationExecutor<OrdemServicoEntity> {

}
