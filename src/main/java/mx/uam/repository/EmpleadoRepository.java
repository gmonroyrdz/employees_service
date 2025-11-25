package mx.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uam.model.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
