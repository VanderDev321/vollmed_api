package med.voll.api.Model.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Model.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
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
