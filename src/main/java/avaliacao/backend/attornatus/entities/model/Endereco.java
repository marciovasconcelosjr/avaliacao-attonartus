package avaliacao.backend.attornatus.entities.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
    private String logadouro;
    private String CEP;
    private Integer numero;
    private String cidade;
}
