package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.Datasouce.MedicoRepository;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicosController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastra(@RequestBody @Valid DadosCadastroMedico dados , UriComponentsBuilder uriBuilder)
	{
		var medico =  new Medico(dados);
		repository.save(medico);
		var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>>listarTodos(@PageableDefault(size = 20,sort={"id"}) Pageable paginacao){

		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

		return ResponseEntity.ok(page);

	}
	@GetMapping("{id}")

	public ResponseEntity listarUm(@PathVariable Long id){
		var medico = repository.getReferenceByIdAndAtivoTrue(id);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody  DadosAtualizarMedico dados){
		var medico = repository.getReferenceByIdAndAtivoTrue(dados.id());
		medico.atualizarRegistro(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

	}
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity Apagar(@PathVariable Long id){
		var medico = repository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}

}
