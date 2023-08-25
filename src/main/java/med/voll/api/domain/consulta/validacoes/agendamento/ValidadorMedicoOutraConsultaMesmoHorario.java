package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoOutraConsultaMesmoHorario implements ValidadorAgendamento {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(),dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("O médico já possui consulta agendada nesta data");
        }

    }
}
