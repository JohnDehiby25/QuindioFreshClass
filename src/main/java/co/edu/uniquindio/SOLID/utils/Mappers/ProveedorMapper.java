package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.Proveedor;

public class ProveedorMapper {

    public static ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) return null;
        return new ProveedorDTO(
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getContacto(),
                proveedor.getEmail(),
                proveedor.getTelefono(),
                proveedor.isActivo()
        );
    }

    public static Proveedor toEntity(ProveedorDTO dto) {
        if (dto == null) return null;
        return new Proveedor(
                dto.getNit(),
                dto.getNombre(),
                dto.getContacto(),
                dto.getEmail(),
                dto.getTelefono()

        );
    }

    public static void updateEntityFromDTO(Proveedor entity, ProveedorDTO dto) {
        if (entity == null || dto == null) return;
        entity.setNit(dto.getNit());
        entity.setNombre(dto.getNombre());
        entity.setContacto(dto.getContacto());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setActivo(dto.isActivo());
    }
}
