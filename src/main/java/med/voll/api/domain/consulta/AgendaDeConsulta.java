package med.voll.api.domain.consulta;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.Datasouce.MedicoRepository;
import med.voll.api.Datasouce.PacienteRepository;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamento;

import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamento;
import med.voll.api.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamento> validadores;
    @Autowired
    private List<ValidadorCancelamento>validadoresDeCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if(!this.pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("o paciente com o id informado não existe!");
        }

        if(dados.idMedico() != null &&!this.medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("o médico com o id informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));
        
        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("NÃO HÁ MEDICOS DISPONÍVEIS PARA ESTA DATA/HORÁRIO");
        }

        var paciente = this.pacienteRepository.getPacienteByIdAndAtivoTrue(dados.idPaciente());
        var consulta = new Consulta(medico,paciente,dados.data());

        this.consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
            if(dados.idMedico() != null) {
                return medicoRepository.getReferenceById(dados.idMedico());
            }
            if(!(dados.especialidade() != null)){
                throw  new ValidacaoException("é necessário informar a especialidade em caso de médico não informado ");
            }

            return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("a consulta informada não existe");
        }
        validadoresDeCancelamento.forEach(v-> v.validar(dados.idConsulta()));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.motivo());

    }
}
