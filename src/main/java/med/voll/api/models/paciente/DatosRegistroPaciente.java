package med.voll.api.models.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.models.direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        String documento,
        @NotNull @Valid DatosDireccion direccion
) {
}
