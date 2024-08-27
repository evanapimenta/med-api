package med.voll.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.dto.EnderecoDTO;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

// DTO
public record PacienteDTO(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,
        @CPF
        String cpf,
        EnderecoDTO endereco
) {
}
