package teste.pratico.atendimento.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import teste.pratico.atendimento.entity.PacienteEntity;
import teste.pratico.atendimento.filter.PacienteFilter;
import teste.pratico.atendimento.repository.PacienteRepository;
import teste.pratico.atendimento.specification.PacienteEspecification;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PacienteService {

    @Autowired
    PacienteRepository repository;

    public List<PacienteEntity> findAll(){
        log.info(String.format("[Paciente.findAll] START"));
        return repository.findAll();
    }

    public PacienteEntity save(PacienteEntity entity){
        log.info(String.format("[Paciente.save] START"));
        PacienteEntity resultado = repository.save(entity);
        log.info(String.format("[Paciente.save] END"));

        return resultado;
    }

    public PacienteEntity update(Long id, PacienteEntity entity){
        log.info(String.format("[Paciente.update] Paciente id: '(%s)' START", id));
        verifyId(id);
        entity.setId(id);
        PacienteEntity resultado = repository.save(entity);
        log.info(String.format("[Paciente.update] Paciente id: '(%s)' END", id));

        return resultado;
    }

    public Optional<PacienteEntity> findOne(Long id) {
        log.info(String.format("[Paciente.findOne] Paciente id: '(%s)' START", id));
        verifyId(id);
        Optional<PacienteEntity> resultado = repository.findById(id);
        log.info(String.format("[Paciente.findOne] Paciente id: '(%s)' END", id));

        return resultado;
    }

    public Page<PacienteEntity> findAllPage(Pageable pageable) {
        log.info(String.format("[Paciente.findAllPage] START"));
        return repository.findAll(pageable);
    }

    public List<PacienteEntity> findAllList() {
        log.info(String.format("[Paciente.findAllList] START"));
        return repository.findAll();
    }

    public Page<PacienteEntity> findAllPage(PacienteFilter filter, Pageable pageable) {
        log.info(String.format("[Paciente.findAllPage] START"));
        return repository.findAll(getSpecification(filter), pageable);
    }

    public List<PacienteEntity> findAllByFilterList(PacienteFilter filter) {
        log.info(String.format("[Paciente.findAllByFilterList] START"));
        return repository.findAll(getSpecification(filter));
    }

    public void delete(PacienteEntity clienteResource) {
        log.info(String.format("[Paciente.delete] Paciente id: '(%s)' START"));
        repository.delete(clienteResource);
        log.info(String.format("[Paciente.delete] Paciente id: '(%s)' END"));
    }

    public void deleteById(Long id) {
        log.info(String.format("[Paciente.deleteById] Paciente id: '(%s)' START", id));
        verifyId(id);
        repository.deleteById(id);
        log.info(String.format("[Paciente.deleteById] Paciente id: '(%s)' END", id));
    }

    private void verifyId(Long id)  {
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() ->
                new RuntimeException(String.format("[Paciente] Não existe registro vinculado ao 'id' ["+id+"]")));
    }

    private Specification<PacienteEntity> getSpecification(PacienteFilter filter) {
        if (filter != null) {
            Specification<PacienteEntity> specification = Specification.where((filter.getId() == null) ? null : PacienteEspecification.isNotNullId());

            specification = (filter.getId() == null) ? specification : specification.and(PacienteEspecification.equalId(filter.getId()));
            specification = (StringUtils.isEmpty(filter.getNome())) ? specification : specification.and(PacienteEspecification.likeNome(filter.getNome()));


            return specification;
        } else {
            return null;
        }
    }

}
