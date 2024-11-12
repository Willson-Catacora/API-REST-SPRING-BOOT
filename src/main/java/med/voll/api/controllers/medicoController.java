package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.medico.*;
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
        return medicoRepositorio.findByActivoTrue(pagina).map(DatosListaMedico::new);
//        return medicoRepositorio.findAll(pagina).map(DatosListaMedico::new);
    }

    @PutMapping
    @Transactional
    public void acutliazarDatosMedico(@RequestBody DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepositorio.getReferenceById(datosActualizarMedico.id());
        medico.actulizarDatos(datosActualizarMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepositorio.getReferenceById(id);
        medico.desactivarMedico();
    }

    //DELETE EN LA BASE DE DATOS
    /*@DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepositorio.getReferenceById(id);
        medicoRepositorio.delete(medico);
    }*/
}
