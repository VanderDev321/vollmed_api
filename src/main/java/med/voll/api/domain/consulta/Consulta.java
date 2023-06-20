package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public class Consulta {
    private Long id;
    private Especialidade especialidade;
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime data;


}
