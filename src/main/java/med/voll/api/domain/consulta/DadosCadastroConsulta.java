package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;

public record DadosCadastroConsulta(String paciente , String medico , Especialidade especialidade , String data) {
}
