package med.voll.api.Model.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Model.endereco.Endereco;
import med.voll.api.Model.endereco.EnderecoDto;

public record DadosAtualizarPaciente(@NotNull Long id,
                                     String nome,
                                     String telefone ,
                                     EnderecoDto endereco) {
}
