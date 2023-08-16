package med.voll.api.domain.consulta;

import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.Datasouce.MedicoRepository;
import med.voll.api.Datasouce.PacienteRepository;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsulta;
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
    private List<ValidadorAgendamentoConsulta> validadores;

    public void agendar(DadosAgendamentoConsulta dados) {
        if(!this.pacienteRepository.existsById(dados.idPaciente())){
            throw new RuntimeException("o id: +"+dados.idPaciente()+"não existe!");
        }
        if(dados.idMedico() != null &&!this.medicoRepository.existsById(dados.idMedico())){
            throw new RuntimeException("o id: +"+dados.idMedico()+"não existe!");
        }
        validadores.forEach(v -> v.validar(dados));
        
        var medico = escolherMedico(dados);
        var paciente = this.pacienteRepository.getPacienteByIdAndAtivoTrue(dados.idPaciente());
        var consulta = new Consulta(null,medico,paciente,dados.data(),null,true);

        this.consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
            if(dados.idMedico() != null) {
                return medicoRepository.getReferenceById(dados.idMedico());
            }
            if(!(dados.especialidade() != null)){
                throw  new RuntimeException("é necessário informar a especialidade em caso de médico não informado ");
            }

            return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new RuntimeException("a consulta informada não existe");
        }
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.motivo());

    }
}
