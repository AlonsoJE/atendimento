package teste.pratico.atendimento.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import teste.pratico.atendimento.entity.ExameEntity;
import teste.pratico.atendimento.filter.ExameFilter;
import teste.pratico.atendimento.repository.ExameRepository;
import teste.pratico.atendimento.specification.ExameEspecification;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ExameService {
    @Autowired
    private ExameRepository repository;

    public List<ExameEntity> findAll(){
        log.info(String.format("[Exame.findAll] START"));
        return repository.findAll();
    }

    public ExameEntity save(ExameEntity entity){
        log.info(String.format("[Exame.save] START"));
        ExameEntity resultado = repository.save(entity);
        log.info(String.format("[Exame.save] END"));

        return resultado;
    }

    public ExameEntity update(Long id, ExameEntity entity){
        log.info(String.format("[Exame.update] Exame id: '(%s)' START", id));
        verifyId(id);
        entity.setId(id);
        ExameEntity resultado = repository.save(entity);
        log.info(String.format("[Exame.update] Exame id: '(%s)' END", id));

        return resultado;
    }

    public Optional<ExameEntity> findOne(Long id) {
        log.info(String.format("[Exame.findOne] Exame id: '(%s)' START", id));
        verifyId(id);
        Optional<ExameEntity> resultado = repository.findById(id);
        log.info(String.format("[Exame.findOne] Exame id: '(%s)' END", id));

        return resultado;
    }

    public Page<ExameEntity> findAllPage(Pageable pageable) {
        log.info(String.format("[Exame.findAllPage] START"));
        return repository.findAll(pageable);
    }

    public List<ExameEntity> findAllList() {
        log.info(String.format("[Exame.findAllList] START"));
        return repository.findAll();
    }

    public Page<ExameEntity> findAllPage(ExameFilter filter, Pageable pageable) {
        log.info(String.format("[Exame.findAllPage] START"));
        return repository.findAll(getSpecification(filter), pageable);
    }

    public List<ExameEntity> findAllByFilterList(ExameFilter filter) {
        log.info(String.format("[Exame.findAllByFilterList] START"));
        return repository.findAll(getSpecification(filter));
    }

    public void delete(ExameEntity entity) {
        log.info(String.format("[Exame.delete] Exame id: '(%s)' START"));
        repository.delete(entity);
        log.info(String.format("[Exame.delete] Exame id: '(%s)' END"));
    }

    public void deleteById(Long id) {
        log.info(String.format("[Exame.deleteById] Exame id: '(%s)' START", id));
        verifyId(id);
        repository.deleteById(id);
        log.info(String.format("[Exame.deleteById] Exame id: '(%s)' END", id));
    }

    private void verifyId(Long id)  {
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() ->
                new RuntimeException(String.format("[Exame] Não existe registro vinculado ao 'id' ["+id+"]")));
    }

    private Specification<ExameEntity> getSpecification(ExameFilter filter) {
        if (filter != null) {
            Specification<ExameEntity> specification = Specification.where((filter.getId() == null) ? null : ExameEspecification.isNotNullId());

            specification = (filter.getId() == null) ? specification : specification.and(ExameEspecification.equalId(filter.getId()));
            specification = (StringUtils.isEmpty(filter.getDescricao())) ? specification : specification.and(ExameEspecification.likeDescricao(filter.getDescricao()));
            specification = (filter.getPreco() == null) ? specification : specification.and(ExameEspecification.likePreco(filter.getPreco()));
            specification = (filter.getTempo() == null) ? specification : specification.and(ExameEspecification.equalsTempo(filter.getTempo()));

            return specification;
        } else {
            return null;
        }
    }
}
