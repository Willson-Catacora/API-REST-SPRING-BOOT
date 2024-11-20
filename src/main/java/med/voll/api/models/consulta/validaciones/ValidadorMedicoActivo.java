package med.voll.api.models.consulta.validaciones;

import med.voll.api.models.ValidacionException;
import med.voll.api.models.consulta.DatosReservaConsulta;
import med.voll.api.models.medico.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    public void validar(DatosReservaConsulta datos) {
        //eleccion del medico opcional
        if(datos.idMedico() == null) {
            return;
        }
        var medicoEstaActivo = medicoRepositorio.findActivoById(datos.idMedico());
        if(!medicoEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con medico excluido");
        }
    }
}
