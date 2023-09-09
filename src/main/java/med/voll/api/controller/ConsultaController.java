package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Datasouce.ConsultaRepository;
import med.voll.api.domain.consulta.AgendaDeConsulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping( "/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendaDeConsulta agenda;
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var consulta =agenda.agendar(dados);

        return ResponseEntity.ok(consulta);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
            agenda.cancelar(dados);

            return ResponseEntity.noContent().build();
    }
}
