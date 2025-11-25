package mx.uam.controller;

import mx.uam.model.dto.DepartamentoDTO;
import mx.uam.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@Tag(name = "Departamento", description = "Operaciones CRUD para departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    @Operation(summary = "Obtener todos los departamentos", description = "Retorna una lista de todos los departamentos")
    public List<DepartamentoDTO> getAll() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener departamento por ID", description = "Retorna un departamento por su ID")
    public ResponseEntity<DepartamentoDTO> getById(@Parameter(description = "ID del departamento") @PathVariable Integer id) {
        DepartamentoDTO dto = departamentoService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear departamento", description = "Crea un nuevo departamento")
    public ResponseEntity<DepartamentoDTO> create(@RequestBody DepartamentoDTO dto) {
        DepartamentoDTO created = departamentoService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar departamento", description = "Actualiza un departamento existente")
    public ResponseEntity<DepartamentoDTO> update(@Parameter(description = "ID del departamento") @PathVariable Integer id, @RequestBody DepartamentoDTO dto) {
        DepartamentoDTO updated = departamentoService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar departamento", description = "Elimina un departamento por su ID")
    public ResponseEntity<Void> delete(@Parameter(description = "ID del departamento") @PathVariable Integer id) {
        boolean deleted = departamentoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
