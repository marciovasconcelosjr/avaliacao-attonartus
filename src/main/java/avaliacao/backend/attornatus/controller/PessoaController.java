package avaliacao.backend.attornatus.controller;

import avaliacao.backend.attornatus.models.EnderecoResponse;
import avaliacao.backend.attornatus.models.PessoasInput;
import avaliacao.backend.attornatus.models.PessoasResponse;
import avaliacao.backend.attornatus.repository.models.EnderecoModel;
import avaliacao.backend.attornatus.repository.models.PessoaModel;
import avaliacao.backend.attornatus.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PessoasResponse>> listAll() {
        List<PessoasResponse> pessoasResponse = service.listAll().stream().map(this::toPessoasModel).collect(Collectors.toList());
        return ResponseEntity.ok(pessoasResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<PessoasResponse> findById(@RequestParam Long id) {
        PessoaModel pessoa = service.findById(id);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PessoasResponse pessoasResponse = toPessoasModel(pessoa);
        return ResponseEntity.ok(pessoasResponse);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<PessoasResponse> findByNome(@RequestParam String nome) {
        PessoaModel pessoa = service.findByNome(nome).getBody();
        PessoasResponse pessoasResponse = toPessoasModel(pessoa);
        return ResponseEntity.ok(pessoasResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<PessoasInput> save(@RequestBody PessoasInput pessoasInput) {
        PessoaModel pessoaModel = pessoasInputToEntity(pessoasInput);
        PessoaModel pessoaCriada = service.save(pessoaModel);
        return new ResponseEntity<>(toPessoasInputModel(pessoaCriada), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PessoasInput> update(@RequestParam Long id, @RequestBody PessoasInput pessoasInput) {
        PessoaModel pessoaModel = pessoasInputToEntity(pessoasInput);
        PessoaModel pessoaAtualizada = service.update(id, pessoaModel);
        return ResponseEntity.ok(toPessoasInputModel(pessoaAtualizada));
    }

    @GetMapping("/listarEnderecos")
    public ResponseEntity<List<EnderecoResponse>> listarEnderecoPorId(@RequestParam Long id) {
        List<EnderecoModel> enderecoModelList = service.listarEnderecoPorId(id);
        List<EnderecoResponse> enderecoDtoList = enderecoModelList.stream().map(this::enderecoResponseToModel).collect(Collectors.toList());
        return ResponseEntity.ok(enderecoDtoList);
    }

    @PutMapping("/definirEnderecoPrincipal")
    public ResponseEntity<List<EnderecoResponse>> definirEnderecoPrincipal(@RequestParam Long id, @RequestParam String CEP) {
        List<EnderecoModel> enderecoModelList = service.definirEnderecoPrincipal(id, CEP);
        List<EnderecoResponse> enderecoDtoList = enderecoModelList.stream().map(this::enderecoResponseToModel).collect(Collectors.toList());
        return ResponseEntity.ok(enderecoDtoList);
    }

    private PessoasResponse toPessoasModel(PessoaModel pessoaModel) {
        return modelMapper.map(pessoaModel, PessoasResponse.class);
    }

    private PessoasInput toPessoasInputModel(PessoaModel pessoaModel) {
        return modelMapper.map(pessoaModel, PessoasInput.class);
    }

    private PessoaModel pessoasInputToEntity(PessoasInput pessoasInput) {
        return modelMapper.map(pessoasInput, PessoaModel.class);
    }

    private EnderecoResponse enderecoResponseToModel(EnderecoModel enderecoModel) {
        return modelMapper.map(enderecoModel, EnderecoResponse.class);
    }
}
