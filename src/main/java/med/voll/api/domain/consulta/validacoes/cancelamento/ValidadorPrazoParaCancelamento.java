package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorPrazoParaCancelamento implements ValidadorCancelamento {
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(Long idConsulta) {
        var consulta = repository.getReferenceById(idConsulta);
        var hoje = LocalDateTime.now();
        var diferencaEntreDatas = Duration.between(hoje, consulta.getData()).toHours();

        if (diferencaEntreDatas < 24){
            throw new ValidacaoException("""
                    Erro: Prazo mínimo invalido.
                    O prazo mínimo para o cancelamento de consultas
                    é de 24 Horas de antecedência.
                    """);
        }


    }
}
