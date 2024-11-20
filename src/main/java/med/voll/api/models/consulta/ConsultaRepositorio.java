package med.voll.api.models.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
