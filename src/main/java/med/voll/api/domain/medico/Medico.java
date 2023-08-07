package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;

@Table(name="medicos")
@Entity(name= "Medico")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm; 
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	@Embedded
	private Endereco endereco;
	private boolean ativo;

	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Consulta> consultasAgendadas = new ArrayList<>();
	public Medico(DadosCadastroMedico dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.especialidade = dados.especialidade();
		this.crm = dados.crm();
		this.endereco = new Endereco(dados.endereco());
	}

    public void atualizarRegistro(DadosAtualizarMedico dados) {
		if (dados.nome()!= null){
			this.nome = dados.nome();
		}
		if(dados.telefone() != null){
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
