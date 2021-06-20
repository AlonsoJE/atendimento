package teste.pratico.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import teste.pratico.atendimento.entity.PacienteEntity;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long>, JpaSpecificationExecutor<PacienteEntity> {
}
