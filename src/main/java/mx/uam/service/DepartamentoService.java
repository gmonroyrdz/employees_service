package mx.uam.service;

import mx.uam.model.dto.DepartamentoDTO;
import mx.uam.model.entity.Departamento;
import mx.uam.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<DepartamentoDTO> findAll() {
        return departamentoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DepartamentoDTO findById(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        return departamento.map(this::toDTO).orElse(null);
    }

    public DepartamentoDTO create(DepartamentoDTO dto) {
        Departamento departamento = new Departamento();
        departamento.setNombre(dto.getNombre());
        departamento.setDireccion(dto.getDireccion());
        Departamento saved = departamentoRepository.save(departamento);
        return toDTO(saved);
    }

    public DepartamentoDTO update(Integer id, DepartamentoDTO dto) {
        if (id == null || dto == null) {
            return null;
        }
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(id);
        if (!departamentoOpt.isPresent()) {
            return null;
        }
        Departamento departamento = departamentoOpt.get();
        departamento.setNombre(dto.getNombre());
        departamento.setDireccion(dto.getDireccion());
        Departamento updated = departamentoRepository.save(departamento);
        return toDTO(updated);
    }

    public boolean delete(Integer id) {
        if (id == null || !departamentoRepository.existsById(id)) {
            return false;
        }
        departamentoRepository.deleteById(id);
        return true;
    }

    private DepartamentoDTO toDTO(Departamento departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setNombre(departamento.getNombre());
        dto.setDireccion(departamento.getDireccion());
        return dto;
    }
}