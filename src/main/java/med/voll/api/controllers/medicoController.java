package med.voll.api.controllers;

import med.voll.api.models.medico.DatosMedicos;
import med.voll.api.models.medico.Medico;
import med.voll.api.models.medico.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class medicoController {
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @PostMapping
    public void registrarMedico(@RequestBody DatosMedicos datosRegistroMedico) {
        System.out.println(datosRegistroMedico);
        medicoRepositorio.save(new Medico(datosRegistroMedico));
    }
}
