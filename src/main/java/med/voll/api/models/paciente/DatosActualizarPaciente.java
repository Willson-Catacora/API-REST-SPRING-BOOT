package med.voll.api.models.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.direccion.DatosDireccion;

public record DatosActualizarPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
