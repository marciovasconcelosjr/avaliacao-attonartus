package avaliacao.backend.attornatus.entities.mapper;

import avaliacao.backend.attornatus.entities.PessoaModel;
import avaliacao.backend.attornatus.entities.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public static Pessoa modelToEntity(PessoaModel model) {
        if(model == null) {
            return null;
        }
        return new Pessoa(model.getNome(),
                model.getDataDeNascimento(),
                model.getEnderecos());
    }
}
