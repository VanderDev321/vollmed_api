package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;
    @MockBean
    private AgendaDeConsulta agenda;

    @Test
    @DisplayName("Deveria devolver http 400 quando informações estão invalidas")
    @WithMockUser
    void agendarCenario1() throws Exception {

        var response =mvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver http 200 quando informações estão validas")
    @WithMockUser
    void agendarCenario2() throws Exception {

        var dataFutura = LocalDateTime.now().plusHours(11l);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosAgendamento = new DadosAgendamentoConsulta(1l,2l,dataFutura,especialidade);
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null,1l,2l,dataFutura);

        Mockito.when(agenda.agendar(any())).thenReturn(dadosDetalhamento);


        var response =mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoConsultaJson.write(dadosAgendamento).getJson())
        ).andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}