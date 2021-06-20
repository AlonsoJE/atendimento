package teste.pratico.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import teste.pratico.atendimento.entity.ExameEntity;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<ExameEntity, Long>, JpaSpecificationExecutor<ExameEntity> {
    List<ExameEntity> findByIdIn(List<Long> ids);
}
