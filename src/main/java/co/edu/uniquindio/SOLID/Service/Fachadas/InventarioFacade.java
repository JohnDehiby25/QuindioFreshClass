package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Service.InventarioService;
import co.edu.uniquindio.SOLID.Service.ProductoService;
import co.edu.uniquindio.SOLID.Service.ProveedorService;

import java.util.List;

public class InventarioFacade {
    private final ProveedorService proveedorService;
    private final ProductoService productoService;
    private final InventarioService inventarioService;
    private final MovimientoInventarioService movimientoInventarioService;

    public InventarioFacade() {
        this.proveedorService = new ProveedorService();
        this.productoService = new ProductoService();
        this.inventarioService = new InventarioService();
        this.movimientoInventarioService = new MovimientoInventarioService();
    }

    // ========== DELEGACIÓN SIMPLE A SERVICIOS ==========

    // Proveedores
    public List<ProveedorDTO> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    public String crearProveedor(ProveedorDTO proveedorDTO) {
        return proveedorService.crearProveedor(proveedorDTO);
    }

    public String actualizarProveedor(ProveedorDTO proveedorDTO) {
        return proveedorService.actualizarProveedor(proveedorDTO);
    }

    public String eliminarProveedor(String nit) {
        return proveedorService.eliminarProveedor(nit);
    }

    public String activarProveedor(String nit) {
        return proveedorService.activarProveedor(nit);
    }

    public String inactivarProveedor(String nit) {
        return proveedorService.inactivarProveedor(nit);
    }

    // Productos
    public List<ProductoDTO> listarProductos() {
        return productoService.listarProductos();
    }

    // Inventario
    public String registrarEntrada(EntradaInventarioDTO entradaDTO) {
        return inventarioService.registrarEntrada(entradaDTO);
    }

    public List<MovimientoInventarioDTO> listarMovimientos() {
        return movimientoInventarioService.listarMovimientos();
    }

}
