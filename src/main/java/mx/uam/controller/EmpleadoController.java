package mx.uam.controller;

import mx.uam.model.dto.EmpleadoDTO;
import mx.uam.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Empleado", description = "Operaciones CRUD para empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    @Operation(summary = "Obtener todos los empleados", description = "Retorna una lista de todos los empleados")
    public List<EmpleadoDTO> getAll() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID", description = "Retorna un empleado por su ID")
    public ResponseEntity<EmpleadoDTO> getById(@Parameter(description = "ID del empleado") @PathVariable Integer id) {
        EmpleadoDTO dto = empleadoService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear empleado", description = "Crea un nuevo empleado")
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO dto) {
        EmpleadoDTO created = empleadoService.create(dto);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado", description = "Actualiza un empleado existente")
    public ResponseEntity<EmpleadoDTO> update(@Parameter(description = "ID del empleado") @PathVariable Integer id, @RequestBody EmpleadoDTO dto) {
        EmpleadoDTO updated = empleadoService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado", description = "Elimina un empleado por su ID")
    public ResponseEntity<Void> delete(@Parameter(description = "ID del empleado") @PathVariable Integer id) {
        boolean deleted = empleadoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
