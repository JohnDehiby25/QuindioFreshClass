package co.edu.uniquindio.SOLID.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaInventario {
    private String id;
    private Proveedor proveedor;
    private LocalDateTime fecha;
    private List<ItemEntrada> items;
    private boolean confirmada;
    private List<MovimientoInventario> movimientosGenerados;

    public EntradaInventario(String id, Proveedor proveedor) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.confirmada = false;
        this.movimientosGenerados = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setItems(List<ItemEntrada> items) {
        this.items = items;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public void setMovimientosGenerados(List<MovimientoInventario> movimientosGenerados) {
        this.movimientosGenerados = movimientosGenerados;
    }

    public String getId() { return id; }
    public Proveedor getProveedor() { return proveedor; }
    public LocalDateTime getFecha() { return fecha; }
    public List<ItemEntrada> getItems() { return items; }
    public boolean isConfirmada() { return confirmada; }

    public void agregarItem(Producto producto, int cantidad) {
        if (cantidad <= 0) return;
        items.add(new ItemEntrada(producto, cantidad));
    }

    public void confirmar() {
        if (confirmada) return;
        Minimercado minimercado = Minimercado.getInstancia();
        for (ItemEntrada item : items) {
            Producto producto = item.getProducto();
            producto.aumentarStock(item.getCantidad());
            MovimientoInventario mov = new MovimientoInventario(
                    "MOV-" + System.currentTimeMillis(),
                    MovimientoInventario.Tipo.ENTRADA,
                    producto,
                    item.getCantidad(),
                    id
            );
            movimientosGenerados.add(mov);
            minimercado.registrarMovimiento(mov);
        }
        this.confirmada = true;
    }

    public List<MovimientoInventario> getMovimientosGenerados() { return movimientosGenerados; }
}


