package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.Datasouce.MedicoRepository;
import med.voll.api.Model.medico.DadosCadastroMedico;
import med.voll.api.Model.medico.Medico;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	public void cadastra(@RequestBody DadosCadastroMedico dados) //com Resquest Body eu pego as informações quem vem no corpo da requisição post
	{
		repository.save( new Medico(dados));
		
	}

}
