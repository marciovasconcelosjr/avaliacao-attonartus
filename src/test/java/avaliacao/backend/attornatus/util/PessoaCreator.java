package avaliacao.backend.attornatus.util;

import avaliacao.backend.attornatus.repository.models.EnderecoModel;
import avaliacao.backend.attornatus.repository.models.PessoaModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaCreator {
    public static PessoaModel criarPessoaParaSalvar() {
        return PessoaModel.builder()
                .nome("Marcio")
                .dataDeNascimento(LocalDate.now())
                .enderecos(criarEndereco())
                .build();
    }

    public static PessoaModel criarPessoaComId() {
        return PessoaModel.builder()
                .id(1L)
                .nome("Marcio")
                .dataDeNascimento(LocalDate.now())
                .enderecos(criarEndereco())
                .build();
    }

    public static PessoaModel criarPessoaParaAtualizar() {
        return PessoaModel.builder()
                .id(1L)
                .nome("Naruto")
                .dataDeNascimento(LocalDate.now())
                .enderecos(criarEndereco())
                .build();
    }

    public static List<EnderecoModel> criarEndereco() {
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogadouro("Rua ABC");
        enderecoModel.setNumero(000);
        enderecoModel.setCEP("11111-111");
        enderecoModel.setEnderecoPrincipal(false);
        List<EnderecoModel> enderecos = new ArrayList<>();
        enderecos.add(enderecoModel);
        return enderecos;
    }
}
