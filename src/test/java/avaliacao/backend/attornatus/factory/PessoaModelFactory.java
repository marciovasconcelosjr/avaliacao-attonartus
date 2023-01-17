package avaliacao.backend.attornatus.factory;

import avaliacao.backend.attornatus.repository.models.PessoaModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaModelFactory {

    public static PessoaModel criarPessoa() {
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L);
        pessoaModel.setNome("Marcio");
        pessoaModel.setDataDeNascimento(LocalDate.now());
        pessoaModel.setEnderecos(EnderecoModelFactory.criarEndereco());
        return pessoaModel;
    }

    public static List<PessoaModel> getListPessoas() {
        List<PessoaModel> pessoas = new ArrayList<>();
        pessoas.add(PessoaModelFactory.criarPessoa());
        pessoas.add(PessoaModelFactory.criarPessoa());
        return pessoas;
    }
}
