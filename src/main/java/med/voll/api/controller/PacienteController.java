package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Datasouce.PacienteRepository;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteRepository repository;
	@PostMapping
	@Transactional
	public ResponseEntity cadastra(@RequestBody @Valid DadosCadastroPaciente dados , UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}

	@GetMapping("{id}")
	public ResponseEntity listaUm(@PathVariable Long id){
		var paciente = repository.getPacienteByIdAndAtivoTrue(id);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listarTodos( @PageableDefault( size = 20 , sort = {"nome"}) Pageable paginacao){
			var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);

			return ResponseEntity.ok(page);
	}
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody DadosAtualizarPaciente dados){
		var paciente = repository.getPacienteByIdAndAtivoTrue(dados.id());
		paciente.atualizarPaciente(dados);

		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));

	}
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity Apagar(@PathVariable Long id){
		var paciente = repository.getReferenceById(id);
		paciente.excluir();

		return ResponseEntity.noContent().build();
	}

}
