package avaliacao.backend.attornatus.interfaceadapter.repository;

import avaliacao.backend.attornatus.entities.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

    Optional<PessoaModel> findByNome(String nome);
}
