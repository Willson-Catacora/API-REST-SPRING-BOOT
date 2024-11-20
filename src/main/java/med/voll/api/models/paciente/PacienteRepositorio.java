package med.voll.api.models.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByActivoTrue(Pageable paginacion);

    @Query("""
            select p.activo
            from Paciente p
            where
            p.id = :idPaciente
            """)
    boolean findActivoById(Long idPaciente);
}
