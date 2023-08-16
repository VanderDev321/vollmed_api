package med.voll.api.domain.consulta.validacoes;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaPacienteComConsultaNoMesmoDia implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var PossuiConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (PossuiConsultaNoDia){
            throw new RuntimeException("O paciente já possui consulta agendada para o dia.");
        }
    }
}
