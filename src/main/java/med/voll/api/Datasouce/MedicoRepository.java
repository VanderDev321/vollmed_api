package med.voll.api.Datasouce;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.medico.Medico;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    Medico getReferenceByIdAndAtivoTrue(Long id);
    List<Medico> findAllByEspecialidade(Especialidade especialidade);
}
