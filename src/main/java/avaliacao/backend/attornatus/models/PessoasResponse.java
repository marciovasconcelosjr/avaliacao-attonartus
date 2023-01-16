package avaliacao.backend.attornatus.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PessoasResponse {
    private Long id;
    private String nome;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private List<EnderecoResponse> enderecos = new ArrayList<>();
}
