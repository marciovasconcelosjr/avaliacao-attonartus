package avaliacao.backend.attornatus.factory;

import avaliacao.backend.attornatus.repository.models.EnderecoModel;

import java.util.ArrayList;
import java.util.List;

public class EnderecoModelFactory {

    public static List<EnderecoModel> criarEndereco() {
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(1L);
        enderecoModel.setLogadouro("Rua ABC");
        enderecoModel.setNumero(000);
        enderecoModel.setCEP("11111-111");
        enderecoModel.setEnderecoPrincipal(false);
        List<EnderecoModel> enderecos = new ArrayList<>();
        enderecos.add(enderecoModel);
        return enderecos;
    }

}
