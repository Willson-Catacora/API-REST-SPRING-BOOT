package med.voll.api.models.medico;

import med.voll.api.models.direccion.DatosDireccion;

public record DatosMedicos(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {
}
