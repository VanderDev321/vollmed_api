package med.voll.api.endereco;

public record EnderecoDto(String logradouro,
		String bairro,
		String cep,
		String cidade,
		String uf,
		String numero,
		String complemento) {

}
