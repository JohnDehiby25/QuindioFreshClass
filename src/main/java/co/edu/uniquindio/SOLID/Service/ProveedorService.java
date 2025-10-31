package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.utils.Mappers.ProveedorMapper;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    private final Minimercado minimercado = Minimercado.getInstancia();

    public List<ProveedorDTO> listarProveedores() {
        List<ProveedorDTO> lista = new ArrayList<>();
        for (Proveedor p : minimercado.getProveedores()) {
            lista.add(ProveedorMapper.toDTO(p));
        }
        return lista;
    }

    public String crearProveedor(ProveedorDTO dto) {
        if (dto == null || dto.getNit() == null || dto.getNit().isEmpty()) return "NIT obligatorio";
        if (!validarEmail(dto.getEmail())) return "Email inválido";
        if (buscarProveedorEntity(dto.getNit()) != null) return "Proveedor ya existe";
        Proveedor nuevo = ProveedorMapper.toEntity(dto);
        minimercado.agregarProveedor(nuevo);
        return "OK";
    }

    public String actualizarProveedor(ProveedorDTO dto) {
        if (dto == null || dto.getNit() == null) return "Datos inválidos";
        Proveedor existente = buscarProveedorEntity(dto.getNit());
        if (existente == null) return "Proveedor no encontrado";
        if (!validarEmail(dto.getEmail())) return "Email inválido";
        ProveedorMapper.updateEntityFromDTO(existente, dto);
        return "OK";
    }

    public String eliminarProveedor(String nit) {
        Proveedor existente = buscarProveedorEntity(nit);
        if (existente == null) return "Proveedor no encontrado";
        minimercado.getProveedores().remove(existente);
        return "OK";
    }

    public String activarProveedor(String nit) {
        Proveedor existente = buscarProveedorEntity(nit);
        if (existente == null) return "Proveedor no encontrado";
        existente.activar();
        return "OK";
    }

    public String inactivarProveedor(String nit) {
        Proveedor existente = buscarProveedorEntity(nit);
        if (existente == null) return "Proveedor no encontrado";
        existente.inactivar();
        return "OK";
    }

    public Proveedor buscarProveedorEntity(String nit) {
        for (Proveedor p : minimercado.getProveedores()) {
            if (p.getNit().equals(nit)) return p;
        }
        return null;
    }

    private boolean validarEmail(String email) {
        return email != null && email.contains("@");
    }
}
