package co.edu.uniquindio.SOLID.Model.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class EntradaInventarioDTO {

    private String id;
    private LocalDate fecha;
    private String nitProveedor;
    private ArrayList<ItemEntradaDTO> listEntradasDTO= new ArrayList<>();

    public EntradaInventarioDTO(String id, LocalDate fecha, String nitProveedor) {
        this.id = id;
        this.fecha = fecha;
        this.nitProveedor = nitProveedor;
        this.listEntradasDTO = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public ArrayList<ItemEntradaDTO> getListEntradasDTO() {
        return listEntradasDTO;
    }

    public void setListEntradasDTO(ArrayList<ItemEntradaDTO> listEntradasDTO) {
        this.listEntradasDTO = listEntradasDTO;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
