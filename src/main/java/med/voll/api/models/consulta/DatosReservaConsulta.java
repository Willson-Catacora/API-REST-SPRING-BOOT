package med.voll.api.models.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad
) {
}
