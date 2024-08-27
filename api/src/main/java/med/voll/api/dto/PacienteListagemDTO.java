package med.voll.api.dto;

import med.voll.api.model.Paciente;

public record PacienteListagemDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone
) {
    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone());
    }
}
