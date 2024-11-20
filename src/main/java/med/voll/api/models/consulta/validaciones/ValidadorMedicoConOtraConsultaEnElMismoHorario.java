package med.voll.api.models.consulta.validaciones;

import med.voll.api.models.ValidacionException;
import med.voll.api.models.consulta.ConsultaRepositorio;
import med.voll.api.models.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    public void validar(DatosReservaConsulta datos){
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepositorio.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidacionException("Medico ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}
