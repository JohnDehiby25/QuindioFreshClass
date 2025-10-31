package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    public Proveedor crearProveedor(String nit, String nombre, String contacto, String email, String telefono) {
        List<Proveedor> proveedores = new ArrayList<>();
        if (buscarProveedor(nit) != null) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese NIT");
        }
        Proveedor proveedor = new Proveedor(nit, nombre, contacto != null ? contacto : "", email != null ? email : "", telefono != null ? telefono : "");
        proveedores.add(proveedor);
        return proveedor;
    }
    public Proveedor buscarProveedor(String nit) {
        List<Proveedor> proveedores = new ArrayList<>();
        for (Proveedor p : proveedores) {
            if (p.getNit().equals(nit)) return p;
        }
        return null;
    }

    public Proveedor actualizarProveedor(String nit, String nombre, String contacto, String email, String telefono, Boolean activo) {
        Proveedor p = buscarProveedor(nit);
        if (p == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        if (nombre != null) p.setNombre(nombre);
        if (contacto != null) p.setContacto(contacto);
        if (email != null) p.setEmail(email);
        if (telefono != null) p.setTelefono(telefono);
        if (activo != null) { if (activo) p.activar(); else p.inactivar(); }
        return p;
    }

    public void eliminarProveedor(String nit) {
        List<Proveedor> proveedores = new ArrayList<>();
        Proveedor p = buscarProveedor(nit);
        if (p == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedores.remove(p);
    }
}
