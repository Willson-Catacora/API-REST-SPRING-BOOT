package med.voll.api.models.consulta.validaciones;

import med.voll.api.models.ValidacionException;
import med.voll.api.models.consulta.DatosReservaConsulta;
import med.voll.api.models.paciente.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    public void validar(DatosReservaConsulta datos){
        var pacienteEstaActivo = pacienteRepositorio.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con paciente excluido");
        }
    }
}
