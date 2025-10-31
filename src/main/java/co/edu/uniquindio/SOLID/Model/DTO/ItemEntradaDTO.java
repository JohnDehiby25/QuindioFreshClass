package co.edu.uniquindio.SOLID.Model.DTO;

public class ItemEntradaDTO {
    private String sku;
    private String cantidad;

    public ItemEntradaDTO(String sku, String cantidad) {
        this.sku = sku;
        this.cantidad = cantidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}


