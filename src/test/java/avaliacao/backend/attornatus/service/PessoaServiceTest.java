package avaliacao.backend.attornatus.service;

import avaliacao.backend.attornatus.TestBase;
import avaliacao.backend.attornatus.repository.PessoaRepository;
import avaliacao.backend.attornatus.repository.models.EnderecoModel;
import avaliacao.backend.attornatus.repository.models.PessoaModel;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class PessoaServiceTest extends TestBase {

    @InjectMocks
    PessoaService pessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    @Test
    void salvarPessoa() {
        PessoaModel pessoa = criarPessoa();
        PessoaModel pessoa2 = criarPessoa();
        Mockito.when(pessoaService.save(pessoa)).thenReturn(pessoa2);
        PessoaModel resultado = pessoaService.save(pessoa);
        log.info(resultado);

        Mockito.verify(pessoaRepository).save(pessoa);
        Assertions.assertEquals(resultado.getId(), pessoa2.getId());
    }

    @Test
    void listarPessoas() {
        PessoaModel p1 = criarPessoa();
        PessoaModel p2 = criarPessoa();
        pessoaService.save(p1);
        pessoaService.save(p2);
        List<PessoaModel> pessoas = new ArrayList<>();
        pessoas.add(p1);
        pessoas.add(p2);

        Mockito.when(pessoaService.listAll()).thenReturn(pessoas);

        List<PessoaModel> pessoasDB = pessoaService.listAll();

        Mockito.verify(pessoaRepository).findAll();
        Assertions.assertEquals(pessoasDB, pessoas);
    }

    @Test
    void buscarPessoaPorId() {
        final Long id = 1L;
        PessoaModel p1 = criarPessoa();
        log.info(p1);
        Mockito.when(pessoaService.findById(id)).thenReturn(p1);
        PessoaModel response = pessoaService.findById(p1.getId());
        log.info(response);

        Assertions.assertEquals(PessoaModel.class, response.getClass());
    }

    @Test
    void atualizarPessoa() {
        PessoaModel pessoaModel = criarPessoa();
        PessoaModel pessoaAtualizada = pessoaModel;
        pessoaAtualizada.setId(2L);
        Mockito.when(pessoaService.update(pessoaModel.getId(), pessoaAtualizada)).thenReturn(pessoaAtualizada);
        PessoaModel resultado = pessoaService.update(pessoaModel.getId(), pessoaAtualizada);
        Assertions.assertNotEquals(resultado.getId(), pessoaAtualizada.getId());
    }

    @Test
    void listerEnderecoPorId() {
        PessoaModel p1 = criarPessoa();
        Mockito.when(pessoaService.listarEnderecoPorId(p1.getId())).thenReturn(p1.getEnderecos());
        List<EnderecoModel> resultado = pessoaService.listarEnderecoPorId(p1.getId());
        Assertions.assertEquals(resultado, p1.getEnderecos());
    }

    public List<EnderecoModel> criarEndereco() {
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(1L);
        enderecoModel.setLogadouro("Rua ABC");
        enderecoModel.setNumero(000);
        enderecoModel.setCEP("11111-111");
        enderecoModel.setEnderecoPrincipal(true);
        List<EnderecoModel> enderecos = new ArrayList<>();
        enderecos.add(enderecoModel);
        return enderecos;
    }

    public PessoaModel criarPessoa() {
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L);
        pessoaModel.setNome("Marcio");
        pessoaModel.setDataDeNascimento(LocalDate.now());
        pessoaModel.setEnderecos(criarEndereco());
        return pessoaModel;
    }
}
