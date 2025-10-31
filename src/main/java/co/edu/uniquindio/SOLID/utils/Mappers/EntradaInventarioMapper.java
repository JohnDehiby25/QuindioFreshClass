package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Proveedor;

public class EntradaInventarioMapper {

    public static EntradaInventarioDTO toDTO(EntradaInventario entradaInventario) {
        if (entradaInventario == null) return null;
        return new EntradaInventarioDTO(
                entradaInventario.getId(),
                entradaInventario.getFecha(),
                entradaInventario.getProveedor().getNit()
        );
    }
    public static EntradaInventario toEntity(EntradaInventarioDTO dto) {
        if (dto == null) return null;
        Proveedor proveedor = new Proveedor();
        proveedor.setNit(dto.getNitProveedor());

        return new EntradaInventario(
                dto.getId(),
                proveedor
        );
    }

    public static void updateEntityFromDTO(EntradaInventario entity, EntradaInventarioDTO dto) {
        if (entity == null || dto == null) return;
        entity.setId(dto.getId());
        entity.setFecha(dto.getFecha());

        Proveedor proveedor = new Proveedor();
        proveedor.setNit(dto.getNitProveedor());
        entity.setProveedor(proveedor);
    }
}