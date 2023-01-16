package avaliacao.backend.attornatus.repository;

import avaliacao.backend.attornatus.repository.models.PessoaModel;
import avaliacao.backend.attornatus.util.PessoaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("tests for repository")
class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void save_PersistPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaCreator.criarPessoaParaSalvar();

        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        Assertions.assertThat(pessoaSalva).isNotNull();
        Assertions.assertThat(pessoaSalva.getId()).isNotNull();
        Assertions.assertThat(pessoaSalva.getNome()).isEqualTo(pessoa.getNome());
    }

    @Test
    void save_UpdatesPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaCreator.criarPessoaParaSalvar();;
        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        pessoaSalva.setNome("Naruto");
        PessoaModel pessoaAtualizada = this.pessoaRepository.save(pessoaSalva);

        Assertions.assertThat(pessoaAtualizada).isNotNull();
        Assertions.assertThat(pessoaAtualizada.getId()).isNotNull();
        Assertions.assertThat(pessoaAtualizada.getNome()).isEqualTo(pessoaSalva.getNome());
    }

    @Test
    void findById_ReturnsPerson_WhenSuccessful() {
        PessoaModel pessoa = PessoaCreator.criarPessoaParaSalvar();;
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
        PessoaModel pessoa = PessoaCreator.criarPessoaParaSalvar();;
        PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);

        String nome = pessoaSalva.getNome();

        Optional<PessoaModel> pessoaPorNome = this.pessoaRepository.findByNome(nome);

        Assertions.assertThat(pessoaPorNome).isNotEmpty().contains(pessoaSalva);
    }

}