package io.github.tcc_2023.clientes.model.service;


import io.github.tcc_2023.clientes.model.entity.Cliente;
import io.github.tcc_2023.clientes.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }


    public void deletar(Integer id) {
        repository.deleteById(id);
    }
    public Cliente atualizar(Integer id, Cliente clienteAtualizado){

        return repository.findById(id)
                .map(cliente -> {
                    clienteAtualizado.setId(cliente.getId());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente acharCliente(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    }

