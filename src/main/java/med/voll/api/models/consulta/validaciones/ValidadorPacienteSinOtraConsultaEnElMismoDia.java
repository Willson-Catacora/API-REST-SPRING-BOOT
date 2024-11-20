package med.voll.api.models.consulta.validaciones;

import med.voll.api.models.ValidacionException;
import med.voll.api.models.consulta.ConsultaRepositorio;
import med.voll.api.models.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    public void validar(DatosReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = consultaRepositorio.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(pacienteTieneOtraConsultaEnElDia) {
            throw new ValidacionException("Paciente ya tiene una consulta reservada para ese dia");
        }
    }
}
