package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.Producto;

public class MovimientoInventarioMapper {

    public static MovimientoInventarioDTO toDTO(MovimientoInventario movimientoInventario) {
        if (movimientoInventario == null) return null;
        return new MovimientoInventarioDTO(
                movimientoInventario.getId(),
                movimientoInventario.getTipo() != null ? movimientoInventario.getTipo().name() : null,
                movimientoInventario.getProducto() != null ? movimientoInventario.getProducto().getSku() : null,
                movimientoInventario.getReferencia(),
                movimientoInventario.getFecha() != null ? movimientoInventario.getFecha().toLocalDate() : null,
                movimientoInventario.getCantidad()
        );
    }

    public static MovimientoInventario toEntity(MovimientoInventarioDTO dto) {
        if (dto == null) return null;
        MovimientoInventario.Tipo tipo = dto.getTipo() != null ? MovimientoInventario.Tipo.valueOf(dto.getTipo()) : null;
        Producto producto = dto.getSkuProducto() != null ? new Producto(dto.getSkuProducto(), "", 0) : null;
        return new MovimientoInventario(
                dto.getId(),
                tipo,
                producto,
                dto.getCantidad(),
                dto.getReferencia()
        );
    }
}

