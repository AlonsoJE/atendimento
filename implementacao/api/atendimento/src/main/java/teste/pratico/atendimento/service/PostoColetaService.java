package teste.pratico.atendimento.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import teste.pratico.atendimento.entity.PostoColetaEntity;
import teste.pratico.atendimento.filter.PostoColetaFilter;
import teste.pratico.atendimento.repository.PostoColetaRepository;
import teste.pratico.atendimento.specification.PostoColetaEspecification;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PostoColetaService {

    @Autowired
    PostoColetaRepository repository;

    public List<PostoColetaEntity> findAll(){
        log.info(String.format("[PostoColeta.findAll] START"));
        return repository.findAll();
    }

    public PostoColetaEntity save(PostoColetaEntity entity){
        log.info(String.format("[PostoColeta.save] START"));
        PostoColetaEntity resultado = repository.save(entity);
        log.info(String.format("[PostoColeta.save] END"));

        return resultado;
    }

    public PostoColetaEntity update(Long id, PostoColetaEntity entity){
        log.info(String.format("[PostoColeta.update] PostoColeta id: '(%s)' START", id));
        verifyId(id);
        entity.setId(id);
        PostoColetaEntity resultado = repository.save(entity);
        log.info(String.format("[PostoColeta.update] PostoColeta id: '(%s)' END", id));

        return resultado;
    }

    public Optional<PostoColetaEntity> findOne(Long id) {
        log.info(String.format("[PostoColeta.findOne] PostoColeta id: '(%s)' START", id));
        verifyId(id);
        Optional<PostoColetaEntity> resultado = repository.findById(id);
        log.info(String.format("[PostoColeta.findOne] PostoColeta id: '(%s)' END", id));

        return resultado;
    }

    public Page<PostoColetaEntity> findAllPage(Pageable pageable) {
        log.info(String.format("[PostoColeta.findAllPage] START"));
        return repository.findAll(pageable);
    }

    public List<PostoColetaEntity> findAllList() {
        log.info(String.format("[PostoColeta.findAllList] START"));
        return repository.findAll();
    }

    public Page<PostoColetaEntity> findAllPage(PostoColetaFilter filter, Pageable pageable) {
        log.info(String.format("[PostoColeta.findAllPage] START"));
        return repository.findAll(getSpecification(filter), pageable);
    }

    public List<PostoColetaEntity> findAllByFilterList(PostoColetaFilter filter) {
        log.info(String.format("[PostoColeta.findAllByFilterList] START"));
        return repository.findAll(getSpecification(filter));
    }

    public void delete(PostoColetaEntity clienteResource) {
        log.info(String.format("[PostoColeta.delete] PostoColeta id: '(%s)' START"));
        repository.delete(clienteResource);
        log.info(String.format("[PostoColeta.delete] PostoColeta id: '(%s)' END"));
    }

    public void deleteById(Long id) {
        log.info(String.format("[PostoColeta.deleteById] PostoColeta id: '(%s)' START", id));
        verifyId(id);
        repository.deleteById(id);
        log.info(String.format("[PostoColeta.deleteById] PostoColeta id: '(%s)' END", id));
    }

    private void verifyId(Long id)  {
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() ->
                new RuntimeException(String.format("[PostoColeta] Não existe registro vinculado ao 'id' ["+id+"]")));
    }

    private Specification<PostoColetaEntity> getSpecification(PostoColetaFilter filter) {
        if (filter != null) {
            Specification<PostoColetaEntity> specification = Specification.where((filter.getId() == null) ? null : PostoColetaEspecification.isNotNullId());

            specification = (filter.getId() == null) ? specification : specification.and(PostoColetaEspecification.equalId(filter.getId()));
            specification = (StringUtils.isEmpty(filter.getDescricao())) ? specification : specification.and(PostoColetaEspecification.likeDescricao(filter.getDescricao()));

            return specification;
        } else {
            return null;
        }
    }



}
