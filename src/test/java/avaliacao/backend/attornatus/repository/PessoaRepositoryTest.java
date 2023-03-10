package avaliacao.backend.attornatus.repository;

import avaliacao.backend.attornatus.factory.PessoaModelFactory;
import avaliacao.backend.attornatus.repository.models.PessoaModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("tests for repository")
class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void findAll_ReturnsListOfPerson_WhenSuccessful() {
        PessoaModel pessoaModel = PessoaModelFactory.criarPessoa();
        pessoaRepository.save(pessoaModel);
        PessoaModel pessoaModel2 = PessoaModelFactory.criarPessoa();
        pessoaModel2.setId(2L);
        pessoaModel2.setNome("Xuxa Menegel");
        pessoaRepository.save(pessoaModel2);

        List<PessoaModel> expectedList = pessoaRepository.findAll();

        Assertions.assertThat(expectedList).isNotNull().isNotEmpty();
        Assertions.assertThat(expectedList.get(0).getNome()).contains(pessoaModel.getNome());
        Assertions.assertThat(expectedList.get(1).getNome()).contains(pessoaModel2.getNome());
    }

    @Test
    void save_PersistPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();

        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        Assertions.assertThat(pessoaSalva).isNotNull();
        Assertions.assertThat(pessoaSalva.getId()).isNotNull();
        Assertions.assertThat(pessoaSalva.getNome()).isEqualTo(pessoa.getNome());
    }

    @Test
    void save_UpdatesPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();
        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        pessoaSalva.setNome("Naruto");
        PessoaModel pessoaAtualizada = this.pessoaRepository.save(pessoaSalva);

        Assertions.assertThat(pessoaAtualizada).isNotNull();
        Assertions.assertThat(pessoaAtualizada.getId()).isNotNull();
        Assertions.assertThat(pessoaAtualizada.getNome()).isEqualTo(pessoaSalva.getNome());
    }

    @Test
    void findById_ReturnsPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();
        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        Long id = pessoaSalva.getId();

        Optional<PessoaModel> pessoaPorId = this.pessoaRepository.findById(id);

        Assertions.assertThat(pessoaPorId).isNotEmpty().contains(pessoaSalva);
    }

    @Test
    void findById_ReturnsEmptyListOfPersons_WhenPersonIsNotFound() {
        Optional<PessoaModel> pessoaPorId = this.pessoaRepository.findById(100L);

        Assertions.assertThat(pessoaPorId).isEmpty();
    }

    @Test
    void findByName_ReturnsPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaModelFactory.criarPessoa();
        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        String nome = pessoaSalva.getNome();

        Optional<PessoaModel> pessoaPorNome = this.pessoaRepository.findByNome(nome);

        Assertions.assertThat(pessoaPorNome).isNotEmpty().contains(pessoaSalva);
    }

}