package io.github.tcc_2023.clientes.rest;

import io.github.tcc_2023.clientes.model.entity.Cliente;
import io.github.tcc_2023.clientes.model.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteService service;

    @InjectMocks
    private ClienteController controller;

    @Test
    void deveSalvarCliente() {
        Cliente cliente = criarCliente(1, "Eduardo");

        when(service.salvar(cliente)).thenReturn(cliente);

        Cliente resultado = controller.salvar(cliente);

        assertSame(cliente, resultado);
        verify(service).salvar(cliente);
    }

    @Test
    void deveBuscarClientePorId() {
        Cliente cliente = criarCliente(1, "Eduardo");

        when(service.acharCliente(1)).thenReturn(cliente);

        Cliente resultado = controller.acharCliente(1);

        assertEquals(1, resultado.getId());
        assertEquals("Eduardo", resultado.getNome());
        verify(service).acharCliente(1);
    }

    @Test
    void deveAtualizarCliente() {
        Cliente clienteAtualizado = criarCliente(null, "Maria");

        controller.atualizar(1, clienteAtualizado);

        verify(service).atualizar(1, clienteAtualizado);
    }

    @Test
    void deveDeletarCliente() {
        controller.deletar(1);

        verify(service).deletar(1);
    }

    private Cliente criarCliente(Integer id, String nome) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf("12345678909");
        return cliente;
    }
}
