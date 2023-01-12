package avaliacao.backend.attornatus.interfaceadapter.controller;

import avaliacao.backend.attornatus.entities.EnderecoModel;
import avaliacao.backend.attornatus.entities.PessoaModel;
import avaliacao.backend.attornatus.interfaceadapter.PessoaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaGateway service;

    @GetMapping
    public ResponseEntity<List<PessoaModel>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/search")
    public ResponseEntity<PessoaModel> findById(@RequestParam Long id) {
        return service.findById(id);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<PessoaModel> findByNome(@RequestParam String nome) {
        return service.findByNome(nome);
    }

    @PostMapping("/save")
    public ResponseEntity<PessoaModel> save(@RequestBody PessoaModel pessoaModel) {
        return new ResponseEntity<>(service.save(pessoaModel), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PessoaModel> update(@RequestParam Long id, @RequestBody PessoaModel pessoaModel) {
        return ResponseEntity.ok(service.update(id, pessoaModel));
    }

    @GetMapping("/listarEnderecos")
    public ResponseEntity<List<EnderecoModel>> listarEnderecoPorId(@RequestParam Long id) {
        return ResponseEntity.ok(service.listarEnderecoPorId(id));
    }

    @PutMapping("/definirEnderecoPrincipal")
    public ResponseEntity<List<EnderecoModel>> definirEnderecoPrincipal(@RequestParam Long id, @RequestParam String CEP) {
        return ResponseEntity.ok(service.definirEnderecoPrincipal(id, CEP));
    }
}
