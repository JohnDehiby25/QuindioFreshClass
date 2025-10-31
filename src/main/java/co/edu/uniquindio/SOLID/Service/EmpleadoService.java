package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.utils.Mappers.EmpleadoMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private final Minimercado minimercado = Minimercado.getInstancia();

    public List<EmpleadoDTO> listarEmpleados() {
        List<EmpleadoDTO> lista = new ArrayList<>();
        for (Empleado e : minimercado.getEmpleados()) {
            lista.add(EmpleadoMapper.toDTO(e));
        }
        return lista;
    }

    public String crearEmpleado(EmpleadoDTO dto) {
        if (dto == null || dto.getId() == null || dto.getId().isEmpty()) {
            return "ID de empleado obligatorio";
        }
        if (buscarEmpleadoEntity(dto.getId()) != null) {
            return "Ya existe un empleado con ese ID";
        }
        Empleado nuevo = EmpleadoMapper.toEntity(dto);
        minimercado.agregarEmpleado(nuevo);
        return "OK";
    }

    public String actualizarEmpleado(EmpleadoDTO dto) {
        if (dto == null || dto.getId() == null) return "Datos inválidos";
        Empleado existente = buscarEmpleadoEntity(dto.getId());
        if (existente == null) return "Empleado no encontrado";
        EmpleadoMapper.updateEntityFromDTO(existente, dto);
        return "OK";
    }

    public String eliminarEmpleado(String id) {
        Empleado existente = buscarEmpleadoEntity(id);
        if (existente == null) return "Empleado no encontrado";
        minimercado.getEmpleados().remove(existente);
        return "OK";
    }

    public String activarEmpleado(String id) {
        Empleado existente = buscarEmpleadoEntity(id);
        if (existente == null) return "Empleado no encontrado";
        existente.activar();
        return "OK";
    }

    public String inactivarEmpleado(String id) {
        Empleado existente = buscarEmpleadoEntity(id);
        if (existente == null) return "Empleado no encontrado";
        existente.inactivar();
        return "OK";
    }

    public Empleado buscarEmpleadoEntity(String id) {
        for (Empleado e : minimercado.getEmpleados()) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }
}
