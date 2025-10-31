package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {


    public Empleado crearEmpleado(String id, String nombre, String rolStr) {
        List<Empleado> empleados = new ArrayList<>();
        if (buscarEmpleado(id) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID");
        }
        Empleado empleado = new Empleado(id, nombre, Empleado.Rol.valueOf(rolStr));
        empleados.add(empleado);
        return empleado;
    }

    public Empleado buscarEmpleado(String id) {
         List<Empleado> empleados = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public Empleado actualizarEmpleado(String id, String nombre, String rolStr, Boolean activo) {
        Empleado e = buscarEmpleado(id);
        if (e == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        if (nombre != null) e.setNombre(nombre);
        if (rolStr != null) e.setRol(Empleado.Rol.valueOf(rolStr));
        if (activo != null) { if (activo) e.activar(); else e.inactivar(); }
        return e;
    }

    public void eliminarEmpleado(String id) {
        List<Empleado> empleados = new ArrayList<>();
        Empleado e = buscarEmpleado(id);
        if (e == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        empleados.remove(e);
    }
}
