package med.voll.api.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.MedicoAtualizacaoDTO;
import med.voll.api.dto.MedicoDTO;

// JPA
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
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

    public Medico(MedicoDTO medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.especialidade = medico.especialidade();
        this.crm = medico.crm();
        this.endereco = new Endereco(medico.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(MedicoAtualizacaoDTO medico) {
        if (medico.nome() != null) {
            this.nome = medico.nome();
        }

        if (medico.telefone() != null) {
            this.telefone = medico.telefone();
        }

        if (medico.endereco() != null) {
            this.endereco.atualizarInformacoes(medico.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
