package mx.uam.controller;

import mx.uam.model.dto.DepartamentoDTO;
import mx.uam.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoDTO> getAll() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> getById(@PathVariable Integer id) {
        DepartamentoDTO dto = departamentoService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> create(@RequestBody DepartamentoDTO dto) {
        DepartamentoDTO created = departamentoService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable Integer id, @RequestBody DepartamentoDTO dto) {
        DepartamentoDTO updated = departamentoService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = departamentoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
