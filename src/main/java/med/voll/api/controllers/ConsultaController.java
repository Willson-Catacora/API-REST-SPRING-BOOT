package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.consulta.DatosReservaConsulta;
import med.voll.api.models.consulta.ReservaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        var detalleConsulta = reserva.reservar(datos);
        return ResponseEntity.ok(detalleConsulta);
    }
}
