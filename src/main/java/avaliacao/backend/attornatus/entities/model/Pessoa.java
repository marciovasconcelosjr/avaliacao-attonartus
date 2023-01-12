package avaliacao.backend.attornatus.entities.model;

import avaliacao.backend.attornatus.entities.EnderecoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    private String nome;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private List<EnderecoModel> enderecoModels = new ArrayList<>();
}
