package med.voll.api.Model.paciente;

import med.voll.api.Model.endereco.Endereco;

public record DadosAtualizarPaciente(String nome, String telefone , Endereco endereco) {
}
