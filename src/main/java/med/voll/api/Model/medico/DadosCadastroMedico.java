package med.voll.api.Model.medico;

import med.voll.api.endereco.EnderecoDto;

public record DadosCadastroMedico(String nome,
		String email,
		String telefone,
		String crm,
		Especialidade especialidade,
		EnderecoDto endereco)  {

}
