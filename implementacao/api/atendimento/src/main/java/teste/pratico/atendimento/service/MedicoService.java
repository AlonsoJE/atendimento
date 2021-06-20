package teste.pratico.atendimento.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import teste.pratico.atendimento.entity.MedicoEntity;
import teste.pratico.atendimento.filter.MedicoFilter;
import teste.pratico.atendimento.repository.MedicoRepository;
import teste.pratico.atendimento.specification.MedicoEspecification;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MedicoService {

    @Autowired
    MedicoRepository repository;

    public List<MedicoEntity> findAll(){
        log.info(String.format("[Medico.findAll] START"));
        return repository.findAll();
    }

    public MedicoEntity save(MedicoEntity entity){
        log.info(String.format("[Medico.save] START"));
        MedicoEntity resultado = repository.save(entity);
        log.info(String.format("[Medico.save] END"));

        return resultado;
    }

    public MedicoEntity update(Long id, MedicoEntity entity){
        log.info(String.format("[Medico.update] Medico id: '(%s)' START", id));
        verifyId(id);
        entity.setId(id);
        MedicoEntity resultado = repository.save(entity);
        log.info(String.format("[Medico.update] Medico id: '(%s)' END", id));

        return resultado;
    }

    public Optional<MedicoEntity> findOne(Long id) {
        log.info(String.format("[Medico.findOne] Medico id: '(%s)' START", id));
        verifyId(id);
        Optional<MedicoEntity> resultado = repository.findById(id);
        log.info(String.format("[Medico.findOne] Medico id: '(%s)' END", id));

        return resultado;
    }

    public Page<MedicoEntity> findAllPage(Pageable pageable) {
        log.info(String.format("[Medico.findAllPage] START"));
        return repository.findAll(pageable);
    }

    public List<MedicoEntity> findAllList() {
        log.info(String.format("[Medico.findAllList] START"));
        return repository.findAll();
    }

    public Page<MedicoEntity> findAllPage(MedicoFilter filter, Pageable pageable) {
        log.info(String.format("[Medico.findAllPage] START"));
        return repository.findAll(getSpecification(filter), pageable);
    }

    public List<MedicoEntity> findAllByFilterList(MedicoFilter filter) {
        log.info(String.format("[Medico.findAllByFilterList] START"));
        return repository.findAll(getSpecification(filter));
    }

    public void delete(MedicoEntity clienteResource) {
        log.info(String.format("[Medico.delete] Medico id: '(%s)' START"));
        repository.delete(clienteResource);
        log.info(String.format("[Medico.delete] Medico id: '(%s)' END"));
    }

    public void deleteById(Long id) {
        log.info(String.format("[Medico.deleteById] Medico id: '(%s)' START", id));
        verifyId(id);
        repository.deleteById(id);
        log.info(String.format("[Medico.deleteById] Medico id: '(%s)' END", id));
    }

    private void verifyId(Long id)  {
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() ->
                new RuntimeException(String.format("[Medico] Não existe registro vinculado ao 'id' ["+id+"]")));
    }

    private Specification<MedicoEntity> getSpecification(MedicoFilter filter) {
        if (filter != null) {
            Specification<MedicoEntity> specification = Specification.where((filter.getId() == null) ? null : MedicoEspecification.isNotNullId());

            specification = (filter.getId() == null) ? specification : specification.and(MedicoEspecification.equalId(filter.getId()));
            specification = (StringUtils.isEmpty(filter.getNome())) ? specification : specification.and(MedicoEspecification.likeNome(filter.getNome()));
            specification = (StringUtils.isEmpty(filter.getCrm())) ? specification : specification.and(MedicoEspecification.equalsCrm(filter.getCrm()));
            specification = (StringUtils.isEmpty(filter.getEspecialidade())) ? specification : specification.and(MedicoEspecification.likeEspecialidade(filter.getEspecialidade()));


            return specification;
        } else {
            return null;
        }
    }

}
