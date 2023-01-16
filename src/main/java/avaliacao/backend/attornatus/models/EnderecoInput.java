package avaliacao.backend.attornatus.models;

import lombok.Data;

@Data
public class EnderecoInput {
    private String logadouro;
    private String CEP;
    private Integer numero;
    private String cidade;
}
