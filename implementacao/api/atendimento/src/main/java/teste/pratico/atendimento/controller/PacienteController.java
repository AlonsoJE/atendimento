package teste.pratico.atendimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.pratico.atendimento.entity.PacienteEntity;
import teste.pratico.atendimento.filter.PacienteFilter;
import teste.pratico.atendimento.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping({"","/"})
    public ResponseEntity<?> save(@RequestBody PacienteEntity entity){
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping({"{id}","/{id}"})
    public ResponseEntity<?> update(@PathVariable (name = "id") Long id, @RequestBody PacienteEntity entity){
        return ResponseEntity.ok(service.update(id,entity));
    }

    @GetMapping({"","/"})
    public ResponseEntity<Page<?>> findWithPage(Pageable pageable){
        return  ResponseEntity.ok(service.findAllPage(pageable));
    }

    @PostMapping({"filter","/filter"})
    public ResponseEntity<Page<?>> findWithPageFilter(@RequestBody PacienteFilter filter, Pageable pageable){
        return  ResponseEntity.ok(service.findAllPage(filter,pageable));
    }

    @GetMapping({"{id}","/{id]"})
    public ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(service.findOne(id));
    }

    @DeleteMapping({"","/"})
    public ResponseEntity<?> delete(@RequestBody PacienteEntity entity){
        service.delete(entity);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping({"{id}","/{id}"})
    public ResponseEntity<?> deleteById(@PathVariable (name = "id") Long id){
        service.deleteById(id);
        return  ResponseEntity.ok().build();
    }

}
