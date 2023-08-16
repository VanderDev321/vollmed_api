package med.voll.api.domain.consulta.validacoes;

import med.voll.api.Datasouce.MedicoRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaMedicoAtivo implements ValidadorAgendamentoConsulta {

        @Autowired
        private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }
        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoAtivo){
            throw new RuntimeException("o medico informado não está ativo no sistema");
        }
    }
}
