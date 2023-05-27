package med.voll.api.Datasouce;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.Model.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
