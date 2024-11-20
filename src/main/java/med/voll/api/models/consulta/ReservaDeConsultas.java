package med.voll.api.models.consulta;

import med.voll.api.ValidacionException;
import med.voll.api.models.medico.Medico;
import med.voll.api.models.medico.MedicoRepositorio;
import med.voll.api.models.paciente.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {

    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    public void reservar(DatosReservaConsulta datos) {

        if (!pacienteRepositorio.existsById(datos.idPaciente())) {
            throw new ValidacionException("No existe un paciente con el id informado");
        }

        if (datos.idMedico() != null && !medicoRepositorio.existsById(datos.idMedico())) {
            throw new ValidacionException("No existe un médico con el id informado");
        }

        var medico = elegirMedico(datos);
        var paciente = pacienteRepositorio.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepositorio.save(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepositorio.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepositorio.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }
}
