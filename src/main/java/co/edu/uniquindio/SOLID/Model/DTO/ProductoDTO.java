package co.edu.uniquindio.SOLID.Model.DTO;

import javafx.beans.property.*;

public class ProductoDTO {
    private StringProperty sku;
    private StringProperty nombre;
    private DoubleProperty precio;
    private IntegerProperty stock;

    public ProductoDTO() {
        this.sku = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.precio = new SimpleDoubleProperty();
        this.stock = new SimpleIntegerProperty();
    }

    public ProductoDTO(String sku, String nombre, double precio) {
        this();
        setSku(sku);
        setNombre(nombre);
        setPrecio(precio);
    }

    public ProductoDTO(String sku, String nombre, double precio, int stock) {
        this(sku, nombre, precio);
        setStock(stock);
    }

    public String getSku() { return sku.get(); }
    public String getNombre() { return nombre.get(); }
    public double getPrecio() { return precio.get(); }
    public int getStock() { return stock.get(); }

    public void setSku(String sku) { this.sku.set(sku); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setPrecio(double precio) { this.precio.set(precio); }
    public void setStock(int stock) { this.stock.set(stock); }

    public StringProperty skuProperty() { return sku; }
    public StringProperty nombreProperty() { return nombre; }
    public DoubleProperty precioProperty() { return precio; }
    public IntegerProperty stockProperty() { return stock; }

    @Override
    public String toString() {
        return getNombre() + " - $" + String.format("%.0f", getPrecio()) + " (Stock: " + getStock() + ")";
    }
}
