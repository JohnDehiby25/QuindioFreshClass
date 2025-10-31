package co.edu.uniquindio.SOLID.Model.DTO;

import java.time.LocalDate;

public class MovimientoInventarioDTO {

    private String id,tipo,skuProducto,referencia;
    private LocalDate fecha;
    private int cantidad;

    public MovimientoInventarioDTO(String id, String tipo, String skuProducto, String referencia, LocalDate fecha, int cantidad) {
        this.id = id;
        this.tipo = tipo;
        this.skuProducto = skuProducto;
        this.referencia = referencia;
        this.fecha = fecha;
        this.cantidad = cantidad;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSkuProducto() {
        return skuProducto;
    }

    public void setSkuProducto(String skuProducto) {
        this.skuProducto = skuProducto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}


