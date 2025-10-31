package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ItemEntradaDTO;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;

public class InventarioService {

    private final ProveedorService proveedorService = new ProveedorService();
    private final ProductoService productoService = new ProductoService();
    private final MovimientoInventarioService movimientoInventarioService = new MovimientoInventarioService();

    public String registrarEntrada(EntradaInventarioDTO entradaDTO) {
        if (entradaDTO == null) return "Datos de entrada inválidos";
        Proveedor proveedor = proveedorService.buscarProveedorEntity(entradaDTO.getNitProveedor());
        if (proveedor == null) return "Proveedor no encontrado";

        for (ItemEntradaDTO item : entradaDTO.getListEntradasDTO()) {
            if (item.getSku() == null || item.getSku().isEmpty()) return "SKU inválido";
            int cantidad;
            try {
                cantidad = Integer.parseInt(item.getCantidad());
            } catch (NumberFormatException ex) {
                return "Cantidad inválida para SKU " + item.getSku();
            }
            if (cantidad <= 0) return "Cantidad debe ser > 0 para SKU " + item.getSku();

            Producto producto = productoService.buscarProductoEntity(item.getSku());
            if (producto == null) return "Producto no encontrado: " + item.getSku();

            producto.aumentarStock(cantidad);
            movimientoInventarioService.registrarEntrada(producto, cantidad, entradaDTO.getId());
        }

        return "OK";
    }

    public int consultarStock(String sku) {
        Producto producto = productoService.buscarProductoEntity(sku);
        if (producto == null) throw new IllegalArgumentException("Producto no encontrado: " + sku);
        return producto.getStock();
    }
}