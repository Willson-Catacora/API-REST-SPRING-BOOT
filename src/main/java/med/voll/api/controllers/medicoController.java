package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.models.medico.DatosListaMedico;
import med.voll.api.models.medico.DatosMedicos;
import med.voll.api.models.medico.Medico;
import med.voll.api.models.medico.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class medicoController {
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosMedicos datosRegistroMedico) {
        System.out.println(datosRegistroMedico);
        medicoRepositorio.save(new Medico(datosRegistroMedico));
    }

    @GetMapping
    public Page<DatosListaMedico> listarMedicos(@PageableDefault(size = 2) Pageable pagina) {
        return medicoRepositorio.findAll(pagina).map(DatosListaMedico::new);
    }
}
