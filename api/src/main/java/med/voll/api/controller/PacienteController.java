package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.MedicoListagemDTO;
import med.voll.api.dto.PacienteAtualizacaoDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.dto.PacienteListagemDTO;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")

public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void cadastroPaciente(@RequestBody PacienteDTO paciente) {
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<PacienteListagemDTO> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteListagemDTO::new);
    }

    @PutMapping
    @Transactional

    public void atualizarPaciente(@RequestBody @Valid PacienteAtualizacaoDTO paciente) {
        var p = repository.getReferenceById(paciente.id());
        p.atualizarInformacoes(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativarPaciente(@PathVariable Long id) {
        var p = repository.getReferenceById(id);
        p.excluir();
    }
}
