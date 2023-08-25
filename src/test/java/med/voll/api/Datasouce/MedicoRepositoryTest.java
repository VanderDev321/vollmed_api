package med.voll.api.Datasouce;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.endereco.EnderecoDto;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")

class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {

        //given
        var segundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico =criarMedico("ciclano","ciclano.medico@vollmed","123456",Especialidade.CARDIOLOGIA);
        var paciente = criarPaciente("beltrano","beltrano.paciente@email","12345678900");
      criarConsulta(medico,paciente,segundaAs10);

        //when
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,segundaAs10);
        //then
        assertThat(medicoLivre).isNull();

    }
    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data ")
    void escolherMedicoAleatorioLivreNaDataCenario2() {

        //given
        var segundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico =criarMedico("ciclano","ciclano.medico@vollmed","123456",Especialidade.CARDIOLOGIA);

        //when
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,segundaAs10);
        //then
        assertThat(medicoLivre).isEqualTo(medico);

    }

    private Medico criarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dados(nome,email,crm,Especialidade.CARDIOLOGIA));
        em.persist(medico);
        return medico;
    }
    private Paciente criarPaciente(String nome, String email, String cpf){
        var paciente = new Paciente(dadosP(nome, email,cpf));
        em.persist(paciente);
        return paciente;
    }
    private void criarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
        em.persist(new Consulta(medico,paciente,data));
    }



    private DadosCadastroMedico dados(String nome, String email, String crm, Especialidade especialidade ){
        var dados = new DadosCadastroMedico(nome,email,"0000000000",crm,especialidade,endereco());
        return dados;
    }
    private DadosCadastroPaciente dadosP(String nome, String email, String cpf){
        var dados = new DadosCadastroPaciente(nome,"email@email.com","1111111111",cpf,endereco());
        return dados;
    }

    private EnderecoDto endereco(){
        return new EnderecoDto("Rua teste","bairro teste","11111111","cidade teste","df","xx","");
    }

}