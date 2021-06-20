package teste.pratico.atendimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.pratico.atendimento.entity.OrdemServicoEntity;
import teste.pratico.atendimento.filter.OrdemServicoFilter;
import teste.pratico.atendimento.service.OrdemServicoService;

import java.util.Base64;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService service;

    @PostMapping({"","/"})
    public ResponseEntity<?> save(@RequestBody OrdemServicoEntity entity){
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping({"{id}","/{id}"})
    public ResponseEntity<?> update(@PathVariable (name = "id") Long id, @RequestBody OrdemServicoEntity entity){
        return ResponseEntity.ok(service.update(id,entity));
    }

    @GetMapping({"","/"})
    public ResponseEntity<Page<?>> findWithPage(Pageable pageable){
        return  ResponseEntity.ok(service.findAllPage(pageable));
    }

    @PostMapping({"filter","/filter"})
    public ResponseEntity<Page<?>> findWithPageFilter(@RequestBody OrdemServicoFilter filter, Pageable pageable){
        return  ResponseEntity.ok(service.findAllPage(filter,pageable));
    }

    @GetMapping({"{id}","/{id]"})
    public ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(service.findOne(id));
    }

    @DeleteMapping({"","/"})
    public ResponseEntity<?> delete(@RequestBody OrdemServicoEntity entity){
        service.delete(entity);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping({"{id}","/{id}"})
    public ResponseEntity<?> deleteById(@PathVariable (name = "id") Long id){
        service.deleteById(id);
        return  ResponseEntity.ok().build();

    }

    @PostMapping({"download","/download"})
    public Object printReport(@RequestBody OrdemServicoFilter filter) {
        byte[] file = service.printReport(filter);
        String encodedString = Base64.getEncoder().encodeToString(file);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/pdf").body(encodedString);
    }

}
