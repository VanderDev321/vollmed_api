package med.voll.api.Model.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Model.endereco.Endereco;
import med.voll.api.Model.endereco.EnderecoDto;

public record DadosAtualizarMedico(
       @NotNull
       Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
