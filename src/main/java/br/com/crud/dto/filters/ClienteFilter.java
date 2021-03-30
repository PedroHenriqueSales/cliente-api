package br.com.crud.dto.filters;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFilter {

    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String cpf;
    private Long rg;


    public boolean isBlank(){

        return StringUtils.isAllBlank(this.nome, this.cpf) &&
                this.id == null &&
                this.dataNascimento == null;
    }
}
