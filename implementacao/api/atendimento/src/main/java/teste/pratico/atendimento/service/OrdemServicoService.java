package teste.pratico.atendimento.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import teste.pratico.atendimento.entity.ExameEntity;
import teste.pratico.atendimento.entity.OrdemServicoEntity;
import teste.pratico.atendimento.filter.OrdemServicoFilter;
import teste.pratico.atendimento.repository.ExameRepository;
import teste.pratico.atendimento.repository.OrdemServicoRepository;
import teste.pratico.atendimento.specification.OrdemServicoSpecification;
import teste.pratico.atendimento.util.GeradorProtocolo;
import teste.pratico.atendimento.util.ReportUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

@Service
@Log4j2
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private ExameRepository exameRepository;

    public List<OrdemServicoEntity> findAll() {
        log.info(String.format("[OrdemServico.findAll] START"));
        return repository.findAll();
    }

    public OrdemServicoEntity save(OrdemServicoEntity entity) {
        log.info(String.format("[OrdemServico.save] START"));

        entity.setValor(somarExames(buscarExamesInId(entity)));
        entity.setRetiradaExame(LocalDateTime.now().plusHours(verificarExameMaisLong(entity)));
        entity.setProtocoloRetirada(GeradorProtocolo.proximoProtocolo());

        System.out.println(entity);

        OrdemServicoEntity resultado = repository.save(entity);

        log.info(String.format("[OrdemServico.save] END"));

        return resultado;
    }

    public OrdemServicoEntity update(Long id, OrdemServicoEntity entity) {
        log.info(String.format("[OrdemServico.update] OrdemServico id: '(%s)' START", id));
        verifyId(id);
        entity.setId(id);
        OrdemServicoEntity resultado = repository.save(entity);
        log.info(String.format("[OrdemServico.update] OrdemServico id: '(%s)' END", id));

        return resultado;
    }

    public Optional<OrdemServicoEntity> findOne(Long id) {
        log.info(String.format("[OrdemServico.findOne] OrdemServico id: '(%s)' START", id));
        verifyId(id);
        Optional<OrdemServicoEntity> resultado = repository.findById(id);
        log.info(String.format("[OrdemServico.findOne] OrdemServico id: '(%s)' END", id));

        return resultado;
    }

    public Page<OrdemServicoEntity> findAllPage(Pageable pageable) {
        log.info(String.format("[OrdemServico.findAllPage] START"));
        return repository.findAll(pageable);
    }

    public List<OrdemServicoEntity> findAllList() {
        log.info(String.format("[OrdemServico.findAllList] START"));
        return repository.findAll();
    }

    public Page<OrdemServicoEntity> findAllPage(OrdemServicoFilter filter, Pageable pageable) {
        log.info(String.format("[OrdemServico.findAllPage] START"));
        return repository.findAll(getSpecification(filter), pageable);
    }

    public List<OrdemServicoEntity> findAllByFilterList(OrdemServicoFilter filter) {
        log.info(String.format("[OrdemServico.findAllByFilterList] START"));
        return repository.findAll(getSpecification(filter));
    }

    public void delete(OrdemServicoEntity clienteResource) {
        log.info(String.format("[OrdemServico.delete] OrdemServico id: '(%s)' START"));
        repository.delete(clienteResource);
        log.info(String.format("[OrdemServico.delete] OrdemServico id: '(%s)' END"));
    }

    public void deleteById(Long id) {
        log.info(String.format("[OrdemServico.deleteById] OrdemServico id: '(%s)' START", id));
        verifyId(id);
        repository.deleteById(id);
        log.info(String.format("[OrdemServico.deleteById] OrdemServico id: '(%s)' END", id));
    }

    private void verifyId(Long id) {
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() ->
                new RuntimeException(String.format("[OrdemServico] Não existe registro vinculado ao 'id' [" + id + "]")));
    }

    // -> business Rules

    private BigDecimal somarExames(List<ExameEntity> entity) {
        BinaryOperator<BigDecimal> soma = (v1, v2) -> {
            return v1.add(v2);
        };

        return entity.stream().map(ExameEntity::getPreco).reduce(soma).get();
    }

    private Integer verificarExameMaisLong(OrdemServicoEntity entity) {

        List<ExameEntity> listExames = buscarExamesInId(entity);

        int resultado = listExames.stream().map(ExameEntity::getTempoExameEmHoras).max(Integer::compare).get();

        return resultado;
    }

    private List<ExameEntity> buscarExamesInId(OrdemServicoEntity entity) {
        List<ExameEntity> listaExames;

        List<Long> ids = new ArrayList<>();

        entity.getExames().stream().map(ExameEntity::getId).forEach((e) -> {
            if(null != e){
                ids.add(e);
            }
        });

        listaExames = exameRepository.findByIdIn(ids);
        return listaExames;
    }

    public byte[] printReport(OrdemServicoFilter protocolo) {
        try {
            List<OrdemServicoEntity> ordemServicoEntity =  findAllByFilterList(protocolo);
            System.out.println(ordemServicoEntity);
            return ReportUtils.generateReportJasper("protocolo", ordemServicoEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Specification<OrdemServicoEntity> getSpecification(OrdemServicoFilter filter) {
        if (filter != null) {
            Specification<OrdemServicoEntity> specification = Specification.where((filter.getId() == null) ? null : OrdemServicoSpecification.isNotNullId());

            specification = (filter.getId() == null) ? specification : specification.and(OrdemServicoSpecification.equalId(filter.getId()));
            specification = (StringUtils.isEmpty(filter.getProtocolo())) ? specification : specification.and(OrdemServicoSpecification.likeProtocolo(filter.getProtocolo()));

            return specification;
        } else {
            return null;
        }
    }
}
