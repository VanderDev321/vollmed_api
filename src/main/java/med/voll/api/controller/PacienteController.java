package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Datasouce.PacienteRepository;
import med.voll.api.Model.paciente.DadosAtualizarPaciente;
import med.voll.api.Model.paciente.DadosListagemPaciente;
import med.voll.api.Model.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import med.voll.api.Model.paciente.DadosCadastroPaciente;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteRepository repository;
	@PostMapping
	@Transactional
	public void cadastra(@RequestBody @Valid DadosCadastroPaciente dados) {

		repository.save(new Paciente(dados));
	}
	@GetMapping
	public Page<DadosListagemPaciente> listarTodos( @PageableDefault( size = 2 , sort = {"nome"}) Pageable paginacao){
			return repository.findAll(paginacao).map(DadosListagemPaciente::new);
	}
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody DadosAtualizarPaciente dados){

	}

}
