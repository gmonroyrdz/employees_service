package mx.uam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mx.uam.dal.entity.Departamento;
import mx.uam.dal.entity.Empleado;
import mx.uam.dal.repository.DepartamentoRepository;
import mx.uam.dal.repository.EmpleadoRepository;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(DepartamentoRepository departamentoRepository, EmpleadoRepository empleadoRepository) {
        return args -> {
            Departamento d = new Departamento();
            d.setNombre("Sistemas");
            d.setDireccion("Calle Falsa 123");
            departamentoRepository.save(d);

            Empleado e = new Empleado();
            e.setNombre("Juan");
            e.setApellidoPaterno("Pérez");
            e.setApellidoMaterno("López");
            e.setEdad(30);
            e.setDepartamento(d);
            empleadoRepository.save(e);

            System.out.println("Sample data inserted: departamento id=" + d.getId() + ", empleado id=" + e.getId());
        };
    }
}