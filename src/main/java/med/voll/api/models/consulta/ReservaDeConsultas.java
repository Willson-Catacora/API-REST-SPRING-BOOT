package med.voll.api.models.consulta;

import med.voll.api.models.ValidacionException;
import med.voll.api.models.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.models.medico.Medico;
import med.voll.api.models.medico.MedicoRepositorio;
import med.voll.api.models.paciente.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Autowired
    private ConsultaRepositorio consultaRepositorio;
    @Autowired
    private List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos){
        if(!pacienteRepositorio.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id informado");
        }

        if(datos.idMedico() != null && !medicoRepositorio.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un médico con el id informado");
        }

        //validaciones
        validadores.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if(medico == null){
            throw new ValidacionException("No existe un médico disponible en ese horario");
        }
        var paciente = pacienteRepositorio.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepositorio.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if(datos.idMedico() != null){
            return medicoRepositorio.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepositorio.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }
}
