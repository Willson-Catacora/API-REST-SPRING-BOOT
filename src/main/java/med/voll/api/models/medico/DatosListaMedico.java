package med.voll.api.models.medico;

public record DatosListaMedico(
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public DatosListaMedico(Medico medico){
        this(medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
