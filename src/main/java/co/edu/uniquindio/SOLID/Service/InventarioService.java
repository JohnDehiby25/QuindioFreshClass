package co.edu.uniquindio.SOLID.Service;


import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ItemEntradaDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;


import java.util.List;


public class InventarioService {


    private ProveedorService proveedorService;


    private ProductoService productoService;



    public EntradaInventario registrarEntradaInventario(Proveedor proveedor, Producto producto, int cantidad) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Se requiere un proveedor");
        }
        if (producto == null) {
            throw new IllegalArgumentException("Se requiere un producto");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        EntradaInventario entrada = new EntradaInventario("ENT-" + System.currentTimeMillis(), proveedor);
        entrada.agregarItem(producto, cantidad);
        entrada.confirmar();
        return entrada;
    }


    public void procesarEntradaInventario(EntradaInventarioDTO entradaDTO) {

        // 1. Validar que el proveedor existe
        Proveedor proveedor = proveedorService.buscarProveedor(entradaDTO.getNitProveedor());
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + entradaDTO.getNitProveedor());
        }

        // 2. Procesar cada item de la entrada
        for (ItemEntradaDTO item : entradaDTO.getListEntradasDTO()) {

            // 2.1 Validar que el producto existe
            Producto producto = productoService.buscarProductoEntity(item.getSku());
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado: " + item.getSku());
            }

            if (<= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0 para el producto: " + item.getSku());
            }

            // 2.3 Actualizar stock del producto
            productoService.aumentarStock(item.getSku(), item.getCantidad());

            // 2.4 Crear movimiento de inventario tipo ENTRADA
            movimientoInventarioService.crearMovimiento(
                    item.getSku(),
                    item.getCantidad(),
                    TipoMovimiento.ENTRADA,
                    entradaDTO.getFecha(),
                    "Entrada de inventario - Proveedor: " + entradaDTO.getNitProveedor()
            );
        }
    }


    public int consultarStock(String sku) {
        Producto producto = productoService.buscarProducto(sku);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado: " + sku);
        }
        return producto.getStock();
    }

    public List<MovimientoInventario> listarMovimientos() {
        return movimientoInventarioService.listarTodos();
    }


    public List<MovimientoInventario> listarMovimientosPorProducto(String sku) {
        return movimientoInventarioService.listarPorProducto(sku);
    }
}