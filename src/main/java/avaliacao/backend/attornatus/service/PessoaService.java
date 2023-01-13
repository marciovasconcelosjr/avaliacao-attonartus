package avaliacao.backend.attornatus.service;

import avaliacao.backend.attornatus.repository.models.EnderecoModel;
import avaliacao.backend.attornatus.repository.models.PessoaModel;
import avaliacao.backend.attornatus.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<PessoaModel> listAll() {
        return pessoaRepository.findAll();
    }

    public ResponseEntity<PessoaModel> findById(Long id) {
        Optional<PessoaModel> pessoa = pessoaRepository.findById(id);
        if (!verificaSePessoaExiste(pessoa.get())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa.get());
    }

    public ResponseEntity<PessoaModel> findByNome(String nome) {
        Optional<PessoaModel> pessoa = pessoaRepository.findByNome(nome);
        if (!verificaSePessoaExiste(pessoa.get())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa.get());
    }

    public PessoaModel save(PessoaModel pessoaModel) {
        for (int i = 0; i < pessoaModel.getEnderecos().size(); i++) {
            alteraEnderecoPrincipal(pessoaModel.getEnderecos().get(i), false);
        }
        return pessoaRepository.save(pessoaModel);
    }

    public PessoaModel update(Long id, PessoaModel pessoaModel) {
        PessoaModel pessoaModelById = findById(id).getBody();
        if (!verificaSePessoaExiste(pessoaModelById)) {
            throw new NotFoundException("Id não encontrado.");
        }
        pessoaModel.setId(id);
        return save(pessoaModel);
    }

    public List<EnderecoModel> listarEnderecoPorId(Long id) {
        PessoaModel pessoaModel = findById(id).getBody();
        return pessoaModel.getEnderecos();
    }

    public List<EnderecoModel> definirEnderecoPrincipal(Long id, String CEP) {
        PessoaModel pessoaModel = findById(id).getBody();
        List<EnderecoModel> enderecoModels = pessoaModel.getEnderecos();
        for (int i = 0; i < pessoaModel.getEnderecos().size(); i++) {
            if (CEP.equals(enderecoModels.get(i).getCEP())) {
                alteraEnderecoPrincipal(enderecoModels.get(i), true);
            } else {
                alteraEnderecoPrincipal(enderecoModels.get(i), false);
            }
        }
        pessoaModel.setEnderecos(enderecoModels);
        pessoaRepository.save(pessoaModel);
        return enderecoModels;
    }

    public boolean verificaSePessoaExiste(PessoaModel pessoaModel) {
        if (pessoaModel == null) {
            return false;
        }
        return true;
    }

    private void alteraEnderecoPrincipal(EnderecoModel enderecoModel, boolean principal) {
        enderecoModel.setEnderecoPrincipal(principal);
    }
}
