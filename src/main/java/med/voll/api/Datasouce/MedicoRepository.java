package med.voll.api.Datasouce;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    Medico getReferenceByIdAndAtivoTrue(Long id);
    List<Medico> findAllByEspecialidade(Especialidade especialidade);

    @Query("""
            select m from Medico m
            where
            m.ativo = 1
            and
            m.especialidade= :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo 
            from Medico m
            where
            m.id = :medicoId
            """)
    boolean findAtivoById(Long medicoId);
}
