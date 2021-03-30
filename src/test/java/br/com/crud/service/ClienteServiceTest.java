package br.com.crud.service;

import br.com.crud.domain.Cliente;
import br.com.crud.dto.ClienteDTO;
import br.com.crud.dto.ClienteEditDTO;
import br.com.crud.dto.filters.ClienteFilter;
import br.com.crud.dto.mapper.ClienteEditMapper;
import br.com.crud.dto.mapper.ClienteMapper;
import br.com.crud.repository.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private ClienteEditMapper clienteEditMapper;

    private static final Pageable DEFAULT_PAGE_REQUEST = PageRequest
            .of(BigInteger.ZERO.intValue(), BigInteger.TEN.intValue());

    @Test
    public void getAll() {

        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(mock(Cliente.class))));
        when(clienteMapper.toPageDTO(any())).thenReturn(new PageImpl<>(Collections.singletonList(mock(ClienteDTO.class))));
        Page<ClienteDTO> allClientes = clienteService.getAll(DEFAULT_PAGE_REQUEST);
        assertTrue(Objects.nonNull(allClientes) && !allClientes.isEmpty());

    }

    @Test
    public void getByFilter() {

        ClienteFilter filter = new ClienteFilter(1L, "Pedro", LocalDate.now(), "1234567899", 12456L);
        when(clienteRepository.findByFilter(filter, DEFAULT_PAGE_REQUEST)).thenReturn(new PageImpl<>(Collections.singletonList(mock(Cliente.class))));
        when(clienteMapper.toPageDTO(any())).thenReturn(new PageImpl<>(Collections.singletonList(mock(ClienteDTO.class))));
        Page<ClienteDTO> allClientes = clienteService.getByFilter(filter, DEFAULT_PAGE_REQUEST);
        assertTrue(Objects.nonNull(allClientes) && !allClientes.isEmpty());

    }

    @Test
    public void create() {

        when(clienteRepository.save(any(Cliente.class))).thenReturn(mock(Cliente.class));
        when(clienteMapper.toEntity(any(ClienteDTO.class))).thenReturn(mock(Cliente.class));
        when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(mock(ClienteDTO.class));

        ClienteDTO clienteDTO = clienteService.create(mock(ClienteDTO.class));
        assertTrue(Objects.nonNull(clienteDTO));
    }

    @Test
    public void update() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(mock(Cliente.class));
        when(clienteMapper.toEntity(any(ClienteDTO.class))).thenReturn(mock(Cliente.class));
        when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(mock(ClienteDTO.class));

        ClienteDTO clienteDTO = clienteService.update(mock(ClienteDTO.class));
        assertTrue(Objects.nonNull(clienteDTO));
    }

    @Test
    public void edit() {

        when(clienteRepository.save(any(Cliente.class))).thenReturn(mock(Cliente.class));
        when(clienteEditMapper.toCompleteEntity(any(Cliente.class), any(ClienteEditDTO.class))).thenReturn(mock(Cliente.class));
        when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(mock(ClienteDTO.class));
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(mock(Cliente.class)));

        ClienteDTO clienteDTO = clienteService.edit(mock(ClienteEditDTO.class));
        assertTrue(Objects.nonNull(clienteDTO));

    }

    @Test
    public void delete() {

        doNothing().when(clienteRepository).delete(any(Cliente.class));
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(mock(Cliente.class)));
        assertDoesNotThrow(() -> clienteService.delete(BigInteger.ONE.longValue()));
    }

}