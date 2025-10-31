package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;

public class MovimientoInventarioMapper {


    public static MovimientoInventarioDTO toDTO(MovimientoInventario movimientoInventario) {
        if (movimientoInventario == null) return null;
        return new MovimientoInventarioDTO(
                movimientoInventario.getId(),
                movimientoInventario.getFecha(),

        );
    }
    public static MovimientoInventario toEntity(MovimientoInventarioDTO dto) {
        if (dto == null) return null;
        return new MovimientoInventario(
                dto.getId(),
                dto.getFecha()
        );
    }
    public static void updateEntityFromDTO(MovimientoInventario entity, MovimientoInventarioDTO dto) {
        if (entity == null || dto == null) return;
        entity.setFecha(dto.getFecha());




}

