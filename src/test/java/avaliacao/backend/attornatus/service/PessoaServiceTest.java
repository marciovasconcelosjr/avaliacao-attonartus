package avaliacao.backend.attornatus.service;

import avaliacao.backend.attornatus.TestBase;
import avaliacao.backend.attornatus.factory.PessoaModelFactory;
import avaliacao.backend.attornatus.repository.PessoaRepository;
import avaliacao.backend.attornatus.repository.models.EnderecoModel;
import avaliacao.backend.attornatus.repository.models.PessoaModel;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class PessoaServiceTest extends TestBase {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    PessoaService pessoaService;


    @BeforeEach
    void setMockOutput() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(PessoaModelFactory.criarPessoa()));
        Mockito.when(pessoaRepository.findAll()).thenReturn(PessoaModelFactory.getListPessoas());
        Mockito.when(pessoaRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
    }

    @DisplayName("Test Salvar" +
            " Pessoas")
    @Test
    void salvarPessoa() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();
        PessoaModel resultado = pessoaService.save(pessoa);
        log.info(resultado);

        Mockito.verify(pessoaRepository).save(pessoa);
        Assertions.assertEquals(resultado.getId(), pessoa.getId());
    }

    @DisplayName("Test Listar Pessoas")
    @Test
    void listAll_WhenSuccessful() {
        List<PessoaModel> pessoasDB = pessoaService.listAll();
        Mockito.verify(pessoaRepository).findAll();
        Assertions.assertEquals(pessoasDB.size(), 2);
    }

    @DisplayName("Test PessoaService + PessoaRepository")
    @Test
    void findById_WhenSuccessful() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();
        PessoaModel response = pessoaService.findById(pessoa.getId());
        Assertions.assertEquals(PessoaModel.class, response.getClass());
        Assertions.assertEquals(pessoa.getNome(), response.getNome());
    }

    @DisplayName("Test Atualizar Pessoas")
    @Test
    void updatePerson_WhenSuccessful() {
        PessoaModel pessoaAtualizada = PessoaModelFactory.criarPessoa();
        pessoaAtualizada.setNome("Xuxa");
        PessoaModel resultado = pessoaService.update(pessoaAtualizada.getId(), pessoaAtualizada);
        Assertions.assertEquals(resultado.getNome(), pessoaAtualizada.getNome());
    }

    @DisplayName("Test Listar Enderecos por ID")
    @Test
    void listAddresById_WhenSuccessful() {
        List<EnderecoModel> resultado = pessoaService.listarEnderecoPorId(1L);
        Assertions.assertEquals(resultado.size(), 1);
        EnderecoModel enderecoModel = resultado.get(0);
        Assertions.assertTrue(enderecoModel.getLogadouro().equals("Rua ABC"));
    }

    @DisplayName("Test Alterar Endereço Principal")
    @Test
    void changePrincipalAddress_WhenSuccessful() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();
        List<EnderecoModel> enderecoResponse = pessoaService.definirEnderecoPrincipal(pessoa.getId(), pessoa.getEnderecos().get(0).getCEP());
        Assertions.assertEquals(enderecoResponse.get(0).getEnderecoPrincipal(), true);
    }
}