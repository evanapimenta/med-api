package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.MedicoAtualizacaoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoListagemDTO;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid MedicoDTO medico) {
        repository.save(new Medico(medico));
    }

    @GetMapping
    public Page<MedicoListagemDTO> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid MedicoAtualizacaoDTO medico) {
        var m = repository.getReferenceById(medico.id());
        m.atualizarInformacoes(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativarMedico(@PathVariable Long id) {
        var m = repository.getReferenceById(id);
        m.excluir();
    }

}

