package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorAntecedenciaMinima implements ValidadorAgendamentoConsulta {
      public void validar(DadosAgendamentoConsulta dados) {
        var horarioConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(horarioConsulta,agora).toMinutes();

        if (diferencaMinutos < 30){
            throw new RuntimeException("Antecedência mínima deve ser de 30 minutos");
        }

    }
}
