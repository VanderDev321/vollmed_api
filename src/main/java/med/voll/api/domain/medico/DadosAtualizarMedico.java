package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.EnderecoDto;

public record DadosAtualizarMedico(
       @NotNull
       Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
