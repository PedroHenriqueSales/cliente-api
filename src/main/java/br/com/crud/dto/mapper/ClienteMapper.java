package br.com.crud.dto.mapper;

import br.com.crud.domain.Cliente;
import br.com.crud.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ClienteMapper implements AbstractMapper<Cliente, ClienteDTO> {

    @Override
    public Cliente toEntity(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(Optional.ofNullable(clienteDTO).map(ClienteDTO::getId).orElse(null))
                .cpf(Optional.ofNullable(clienteDTO).map(ClienteDTO::getCpf).orElse(null))
                .dataNascimento(Optional.ofNullable(clienteDTO).map(ClienteDTO::getDataNascimento).orElse(null))
                .nome(Optional.ofNullable(clienteDTO).map(ClienteDTO::getNome).orElse(null))
                .rg(Optional.ofNullable(clienteDTO).map(ClienteDTO::getRg).orElse(null))
                .build();
    }

    @Override
    public ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(Optional.ofNullable(cliente).map(Cliente::getId).orElse(null))
                .cpf(Optional.ofNullable(cliente).map(Cliente::getCpf).orElse(null))
                .dataNascimento(Optional.ofNullable(cliente).map(Cliente::getDataNascimento).orElse(null))
                .nome(Optional.ofNullable(cliente).map(Cliente::getNome).orElse(null))
                .idade(Optional.ofNullable(cliente).map(Cliente::getDataNascimento)
                        .map(this::getIdade).orElse(BigInteger.ZERO.intValue()))
                .rg(Optional.ofNullable(cliente).map(Cliente::getRg).orElse(null))
                .build();
    }

    private int getIdade(LocalDate dataNascimento) {
        return Optional.ofNullable(dataNascimento)
                .map(dtNasc -> Period.between(dtNasc, LocalDate.now()))
                .map(Period::getYears).orElse(BigInteger.ZERO.intValue());
    }

}
