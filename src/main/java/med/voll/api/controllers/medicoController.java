package med.voll.api.controllers;

import med.voll.api.models.medico.DatosMedicos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class medicoController {
    @PostMapping
    public void registrarMedico(@RequestBody DatosMedicos datosRegistroMedico) {
        System.out.println("el request llego correctamente");
        System.out.println(datosRegistroMedico);
    }
}
