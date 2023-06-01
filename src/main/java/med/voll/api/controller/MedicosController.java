package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Model.medico.DadosAtualizarMedico;
import med.voll.api.Model.medico.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import med.voll.api.Datasouce.MedicoRepository;
import med.voll.api.Model.medico.DadosCadastroMedico;
import med.voll.api.Model.medico.Medico;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastra(@RequestBody @Valid DadosCadastroMedico dados) //com Resquest Body eu pego as informações quem vem no corpo da requisição post
	{
		repository.save( new Medico(dados));
		
	}
	@GetMapping
	public Page<DadosListagemMedico> listarTodos(@PageableDefault(size = 10,sort={"nome"}) Pageable paginacao){
		//quando não utilizamos paginação : return repository.findAll().stream().map(DadosListagemMedico::new).toList();
		// Retorna todos os médicos, faz um stream na lista,
		// aplica um map transformando Medico em DadosListagemMedico e transforma tudo em uma lista novamente
		return repository.findAll(paginacao).map(DadosListagemMedico::new);

	}
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody DadosAtualizarMedico dados){

	}

}
