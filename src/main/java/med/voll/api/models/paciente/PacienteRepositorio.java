package med.voll.api.models.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByActivoTrue(Pageable paginacion);
}
