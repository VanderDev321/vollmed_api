package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesAberturaDaClinica = dataConsulta.getHour() < 7;

        var depoisFechamentoCLinica = dataConsulta.getHour() > 18;


        if (domingo || antesAberturaDaClinica || depoisFechamentoCLinica){
            throw new ValidacaoException("Consulta fora do horário de atendimento da clínica ");
        }

    }
}
