package med.voll.api.domain.consulta.validacoes;

import med.voll.api.Datasouce.PacienteRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;
    public void validar(DadosAgendamentoConsulta dados){

        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
         if (!pacienteAtivo){
             throw new RuntimeException("O paciente informado está inativo no sistema");
         }
    }
}