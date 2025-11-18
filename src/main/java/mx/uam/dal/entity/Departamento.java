package mx.uam.dal.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String direccion;

    @OneToMany(mappedBy="departamento",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Empleado> empleados = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    // helper methods to keep bidirectional consistency
    public void addEmpleado(Empleado e) {
        if (e == null) return;
        if (!this.empleados.contains(e)) {
            this.empleados.add(e);
            e.setDepartamento(this);
        }
    }

    public void removeEmpleado(Empleado e) {
        if (e == null) return;
        if (this.empleados.remove(e)) {
            e.setDepartamento(null);
        }
    }

    


}
