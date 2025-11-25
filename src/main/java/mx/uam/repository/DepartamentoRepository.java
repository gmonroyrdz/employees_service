package mx.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uam.model.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
