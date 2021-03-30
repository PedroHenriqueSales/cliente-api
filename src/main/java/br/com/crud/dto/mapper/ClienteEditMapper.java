package br.com.crud.dto.mapper;

import br.com.crud.domain.Cliente;
import br.com.crud.dto.ClienteEditDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class ClienteEditMapper implements AbstractMapper<Cliente, ClienteEditDTO>{

    @Override
    public Cliente toEntity(ClienteEditDTO dto) {
        return Cliente.builder()
                .id(Optional.ofNullable(dto).map(ClienteEditDTO::getId).orElse(null))
                .nome(Optional.ofNullable(dto).map(ClienteEditDTO::getNome).orElse(null))
                .dataNascimento(Optional.ofNullable(dto).map(ClienteEditDTO::getDataNascimento).orElse(null))
                .build();
    }

    @Override
    public ClienteEditDTO toDTO(Cliente entity) {
        return ClienteEditDTO.builder()
                .id(Optional.ofNullable(entity).map(Cliente::getId).orElse(null))
                .nome(Optional.ofNullable(entity).map(Cliente::getNome).orElse(null))
                .dataNascimento(Optional.ofNullable(entity).map(Cliente::getDataNascimento).orElse(null))
                .build();
    }

    public Cliente toCompleteEntity(Cliente cliente, ClienteEditDTO clienteUpdate) {
        return Cliente.builder()
                .id(cliente.getId())
                .cpf(cliente.getCpf())
                .rg(cliente.getRg())
                .nome(Optional.ofNullable(clienteUpdate.getNome()).orElse(cliente.getNome()))
                .dataNascimento(Optional.ofNullable(clienteUpdate.getDataNascimento()).orElse(cliente.getDataNascimento()))
                .build();
    }

}
