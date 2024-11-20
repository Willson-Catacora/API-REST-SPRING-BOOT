package med.voll.api.models.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {

    // Obtiene una página de médicos activos
    Page<Medico> findByActivoTrue(Pageable pagina);

    // Selecciona un médico aleatorio disponible en una fecha específica, que sea de la especialidad dada.
    @Query("""
            select m from Medico m
            where
            m.activo = true
            and
            m.especialidad = :especialidad
            and m.id not in(
                select c.medico.id from Consulta c
                where
                c.fecha = :fecha
            )
            order by rand()
            """)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(
            Especialidad especialidad,
            @NotNull @Future LocalDateTime fecha);

    // Método para verificar si un médico está activo
    @Query("""
            select m.activo from Medico m
            where m.id = :idMedico
            """)
    Boolean findActivoById(Long idMedico);
}
