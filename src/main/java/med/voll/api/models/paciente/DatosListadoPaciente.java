package med.voll.api.models.paciente;

public record DatosListadoPaciente(
        Long id,
        String nombre,
        String email,
        String documento
) {
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
