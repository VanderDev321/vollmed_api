package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var horarioConsulta = dados.data();
        var domingo = horarioConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaDaClinica = horarioConsulta.getHour() < 7;
        var depoisFechamentoCLinica = horarioConsulta.getHour() > 18;

        if (domingo || antesAberturaDaClinica || depoisFechamentoCLinica){
            throw new RuntimeException("Horario de funcionamento da clínica não é válido. ");
        }

    }
}
