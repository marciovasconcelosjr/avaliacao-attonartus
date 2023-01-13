package avaliacao.backend.attornatus.repository;

import avaliacao.backend.attornatus.repository.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

    Optional<PessoaModel> findByNome(String nome);
}
