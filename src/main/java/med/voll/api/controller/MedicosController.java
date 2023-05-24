package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.medico.DadosCadastroMedico;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
	
	@PostMapping
	public void cadastra(@RequestBody DadosCadastroMedico dados) //com Resquest Body eu pego as informações quem vem no corpo da requisição post
	{
		System.out.println(dados);
		
	}

}
