package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;

public class EntradaInventarioMapper {

    public static EntradaInventarioDTO toDTO(EntradaInventario entradaInventario) {
        if (entradaInventario == null) return null;
        return new EntradaInventarioDTO(
                entradaInventario.getId(),
                entradaInventario.getFecha(),
                entradaInventario.getProveedor().getNit()).getId();

    }
    public static EntradaInventario toEntity(EntradaInventarioDTO dto) {
        if (dto == null) return null;
        return new EntradaInventario(
                dto.getId(),
                dto.getFecha()
        );
    }
    public static void updateEntityFromDTO(EntradaInventario entity, EntradaInventarioDTO dto) {
        if (entity == null || dto == null) return;
        entity.setFecha(dto.getFecha());
    }
}