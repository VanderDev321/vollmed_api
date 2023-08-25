package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaPacienteComNoMesmoDia implements ValidadorAgendamento {

    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var PossuiConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (PossuiConsultaNoDia){
            throw new ValidacaoException("O paciente já possui consulta agendada para o dia.");
        }
    }
}
