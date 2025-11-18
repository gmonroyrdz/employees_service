package mx.uam.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.uam.dal.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
