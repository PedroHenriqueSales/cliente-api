package br.com.crud.controller;

import br.com.crud.domain.Cliente;
import br.com.crud.dto.ClienteDTO;
import br.com.crud.dto.ClienteEditDTO;
import br.com.crud.dto.filters.ClienteFilter;
import br.com.crud.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @ApiOperation(value = "Buscar todos os clientes", notes = "Consulta paginada por todos os clientes cadastrados", response = ClienteDTO.class)
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getClientes(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(clienteService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar clientes por filtro", notes = "Consulta paginada de clientes por filtro de busca", response = ClienteDTO.class)
    @GetMapping(path = "/filtro")
    public ResponseEntity<Page<ClienteDTO>> getClienteByFilter(
            @ApiParam(value = "Filtro de consulta por id, nome, data de nascimento ou CPF")
            ClienteFilter filter,
            @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(clienteService.getByFilter(filter, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Salvar novo cliente", response = ClienteDTO.class)
    @PostMapping
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO cliente) {
        return new ResponseEntity<>(clienteService.create(cliente), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modificar cliente", notes = "Usado para modificar todos os dados do cliente", response = ClienteDTO.class)
    @PutMapping
    public ResponseEntity<ClienteDTO> updateCliente(
            @ApiParam(value = "DTO espelho do objeto, com todos os dados deste")
            @RequestBody ClienteDTO cliente) {
        return new ResponseEntity<>(clienteService.update(cliente), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar cliente", notes = "Atualiza dados do cliente de forma granular", response = ClienteDTO.class)
    @PatchMapping
    public ResponseEntity<ClienteDTO> updateCliente(
            @ApiParam(value = "DTO para alteração de nome e data de nascimento do cliente")
            @RequestBody ClienteEditDTO cliente) {
        return new ResponseEntity<>(clienteService.edit(cliente), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar cliente", notes = "Remove o cliente pelo id informado")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
