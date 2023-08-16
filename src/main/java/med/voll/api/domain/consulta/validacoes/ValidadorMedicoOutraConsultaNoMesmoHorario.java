package med.voll.api.domain.consulta.validacoes;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(),dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw new RuntimeException("O médico já possui consulta agendada nesta data");
        }

    }
}
