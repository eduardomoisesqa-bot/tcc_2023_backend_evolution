package io.github.tcc_2023.clientes.rest;

import io.github.tcc_2023.clientes.model.entity.Cliente;
import io.github.tcc_2023.clientes.model.repository.ClienteRepository;
import io.github.tcc_2023.clientes.model.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.Collections;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController  {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service){
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return  service.salvar(cliente);
    }

    @GetMapping("{id}")
    public Cliente acharCliente(@PathVariable Integer id){
        return service.acharCliente(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
        service.atualizar(id,clienteAtualizado);
    }

}
