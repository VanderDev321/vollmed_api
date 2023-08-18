package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorAntecedenciaMinima implements ValidadorAgendamento {
      public void validar(DadosAgendamentoConsulta dados) {
        var horarioConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora,horarioConsulta).toMinutes();
          System.out.println("TEMPO DECORRIDO: " +diferencaMinutos);

        if (diferencaMinutos < 30){
            throw new ValidacaoException("Antecedência mínima deve ser de 30 minutos");
        }

    }
}
