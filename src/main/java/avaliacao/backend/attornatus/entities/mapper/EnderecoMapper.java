package avaliacao.backend.attornatus.entities.mapper;

import avaliacao.backend.attornatus.entities.EnderecoModel;
import avaliacao.backend.attornatus.entities.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public static Endereco modelToEntity(EnderecoModel model) {
        if (model == null) {
            return null;
        }
        return new Endereco(
                model.getLogadouro(),
                model.getCEP(),
                model.getNumero(),
                model.getCidade());
    }

}
