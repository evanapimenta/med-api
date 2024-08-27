package med.voll.api.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.PacienteAtualizacaoDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.model.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(PacienteDTO paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.cpf = paciente.cpf();
        this.endereco = new Endereco(paciente.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(PacienteAtualizacaoDTO paciente) {
        if (paciente.nome() != null) {
            this.nome = paciente.nome();
        }
        if (paciente.telefone() != null) {
            this.telefone = paciente.telefone();
        }
        if(paciente.endereco() != null) {
            this.endereco.atualizarInformacoes(paciente.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
