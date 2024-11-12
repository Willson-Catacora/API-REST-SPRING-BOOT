package med.voll.api.models.medico;

import med.voll.api.models.direccion.DatosDireccion;
import med.voll.api.models.direccion.Direccion;

public record DatosActualizarMedico(
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
