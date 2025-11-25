package mx.uam.service;

import mx.uam.model.dto.EmpleadoDTO;
import mx.uam.model.entity.Empleado;
import mx.uam.repository.EmpleadoRepository;
import mx.uam.repository.DepartamentoRepository;
import mx.uam.model.entity.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<EmpleadoDTO> findAll() {
        return empleadoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO findById(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(this::toDTO).orElse(null);
    }

    public EmpleadoDTO create(EmpleadoDTO dto) {
        if (dto == null || dto.getDepartamentoId() == null) {
            return null;
        }
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(dto.getDepartamentoId());
        if (!departamentoOpt.isPresent()) {
            return null;
        }
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setApellidoPaterno(dto.getApellidoPaterno());
        empleado.setApellidoMaterno(dto.getApellidoMaterno());
        empleado.setEdad(dto.getEdad());
        empleado.setPuesto(dto.getPuesto());
        empleado.setDepartamento(departamentoOpt.get());
        Empleado saved = empleadoRepository.save(empleado);
        return toDTO(saved);
    }

    public EmpleadoDTO update(Integer id, EmpleadoDTO dto) {
        if (id == null || dto == null || dto.getDepartamentoId() == null) {
            return null;
        }
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(dto.getDepartamentoId());
        if (!empleadoOpt.isPresent() || !departamentoOpt.isPresent()) {
            return null;
        }
        Empleado empleado = empleadoOpt.get();
        empleado.setNombre(dto.getNombre());
        empleado.setApellidoPaterno(dto.getApellidoPaterno());
        empleado.setApellidoMaterno(dto.getApellidoMaterno());
        empleado.setEdad(dto.getEdad());
        empleado.setPuesto(dto.getPuesto());
        empleado.setDepartamento(departamentoOpt.get());
        Empleado updated = empleadoRepository.save(empleado);
        return toDTO(updated);
    }

    public boolean delete(Integer id) {
        if (id == null || !empleadoRepository.existsById(id)) {
            return false;
        }
        empleadoRepository.deleteById(id);
        return true;
    }

    private EmpleadoDTO toDTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setApellidoPaterno(empleado.getApellidoPaterno());
        dto.setApellidoMaterno(empleado.getApellidoMaterno());
        dto.setEdad(empleado.getEdad());
        dto.setPuesto(empleado.getPuesto());
        dto.setDepartamentoId(empleado.getDepartamento() != null ? empleado.getDepartamento().getId() : null);
        return dto;
    }
}