package br.com.crud.service;

import br.com.crud.domain.Cliente;
import br.com.crud.dto.ClienteDTO;
import br.com.crud.dto.ClienteEditDTO;
import br.com.crud.dto.filters.ClienteFilter;
import br.com.crud.dto.mapper.ClienteEditMapper;
import br.com.crud.dto.mapper.ClienteMapper;
import br.com.crud.exception.ClienteParamsException;
import br.com.crud.exception.PersistenceException;
import br.com.crud.exception.ResourceNotFoundException;
import br.com.crud.repository.ClienteRepository;
import br.com.crud.util.MessageCode;
import br.com.crud.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private static final String CLIENTE = "Cliente";
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteEditMapper clienteEditMapper;

    public Page<ClienteDTO>getAll(Pageable pageable) {

            Page<Cliente> clientes = clienteRepository.findAll(pageable);

            if(clientes.isEmpty()){
                throw new ResourceNotFoundException(MessageCode.NOT_FOUND);
            }

            return clienteMapper.toPageDTO(clientes);
    }

    public Page<ClienteDTO> getByFilter(ClienteFilter filter, Pageable pageable) {

        if (filter.isBlank()) {
            throw new ClienteParamsException(MessageCode.BAD_REQUEST_SEARCH);
        }

        Page<Cliente> clientes = clienteRepository.findByFilter(filter, pageable);

        if (clientes.isEmpty()) {
            throw new ResourceNotFoundException(MessageCode.NOT_FOUND);
        }

        return clienteMapper.toPageDTO(clientes);
    }

    private Cliente getById(Long id) {
        if (Utils.isObjectEmpty(id)) {
            throw new ClienteParamsException(MessageCode.BAD_REQUEST_SEARCH);
        }
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException(MessageCode.NOT_FOUND);

        } else {
            return cliente.get();
        }
    }

    public ClienteDTO create(ClienteDTO cliente) {
        if (Objects.isNull(cliente)) {
            throw new ClienteParamsException(MessageCode.BAD_REQUEST_CREATE, CLIENTE);
        }
        return Optional.of(cliente).map(clienteMapper::toEntity).map(clienteRepository::save).map(clienteMapper::toDTO)
                .orElseThrow(() -> new PersistenceException(MessageCode.PERSIST_FAIL));
    }

    public ClienteDTO update(ClienteDTO cliente) {
        if (Objects.isNull(cliente)) {
            throw new ClienteParamsException(MessageCode.BAD_REQUEST_CREATE, CLIENTE);
        }

        return Optional.of(cliente).map(clienteMapper::toEntity).map(clienteRepository::save).map(clienteMapper::toDTO)
                .orElseThrow(() -> new PersistenceException(MessageCode.UPDATE_FAIL));
    }

    public ClienteDTO edit(ClienteEditDTO cliente) {
        if (Objects.isNull(cliente)) {
            throw new ClienteParamsException(MessageCode.BAD_REQUEST_CREATE, CLIENTE);
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId())
                .map(c -> clienteEditMapper.toCompleteEntity(c, cliente));

        return clienteOptional.map(clienteRepository::save).map(clienteMapper::toDTO)
                .orElseThrow(() -> new PersistenceException(MessageCode.PERSIST_FAIL));
    }

    public void delete(Long id) {
        clienteRepository.delete((getById(id)));
    }


}
