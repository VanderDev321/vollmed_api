package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.EnderecoDto;

public record DadosAtualizarPaciente(@NotNull Long id,
                                     String nome,
                                     String telefone ,
                                     EnderecoDto endereco) {
}
