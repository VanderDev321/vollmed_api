package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Datasouce.PacienteRepository;
import med.voll.api.Model.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.Model.paciente.DadosCadastroPaciente;

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

}
