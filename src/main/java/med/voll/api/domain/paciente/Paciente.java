package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	@Embedded
	private Endereco endereco;
	private boolean ativo;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Consulta> consultasAgendadas = new ArrayList<>();


	public Paciente(DadosCadastroPaciente dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
		this.telefone = dados.telefone();
		this.endereco = new Endereco(dados.endereco());
	}
    public void atualizarPaciente(DadosAtualizarPaciente dados) {
		if (dados.nome()!= null){
			this.nome = dados.nome();
		}
		if (dados.telefone()!= null){
			this.telefone = dados.telefone();
		}
		if (dados.endereco() != null){
			this.endereco.atualizarEndereco(dados.endereco());
		}
    }

    public void excluir() {
		this.ativo = false;
    }
}
