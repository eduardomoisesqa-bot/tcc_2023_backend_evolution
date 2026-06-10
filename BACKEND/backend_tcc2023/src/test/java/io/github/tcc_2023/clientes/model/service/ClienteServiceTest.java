package io.github.tcc_2023.clientes.model.service;

import io.github.tcc_2023.clientes.model.entity.Cliente;
import io.github.tcc_2023.clientes.model.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void deveSalvarClienteComSucesso() {
        Cliente cliente = criarCliente(1, "Eduardo");

        when(repository.save(cliente)).thenReturn(cliente);

        Cliente resultado = service.salvar(cliente);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Eduardo", resultado.getNome());
        verify(repository).save(cliente);
    }

    @Test
    void deveBuscarClientePorIdComSucesso() {
        Cliente cliente = criarCliente(1, "Eduardo");

        when(repository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente resultado = service.acharCliente(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Eduardo", resultado.getNome());
        verify(repository).findById(1);
    }

    @Test
    void deveLancarErroQuandoClienteNaoForEncontradoAoBuscar() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.acharCliente(1));

        assertEquals("Cliente n\u00e3o encontrado", exception.getMessage());
        verify(repository).findById(1);
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        Cliente clienteExistente = criarCliente(1, "Eduardo");
        Cliente clienteAtualizado = criarCliente(null, "Maria");

        when(repository.findById(1)).thenReturn(Optional.of(clienteExistente));
        when(repository.save(clienteAtualizado)).thenReturn(clienteAtualizado);

        Cliente resultado = service.atualizar(1, clienteAtualizado);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Maria", resultado.getNome());
        verify(repository).findById(1);
        verify(repository).save(clienteAtualizado);
    }

    @Test
    void deveLancarErroQuandoClienteNaoForEncontradoAoAtualizar() {
        Cliente clienteAtualizado = criarCliente(null, "Maria");

        when(repository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.atualizar(1, clienteAtualizado));

        assertEquals("Cliente n\u00e3o encontrado", exception.getMessage());
        verify(repository).findById(1);
    }

    @Test
    void deveDeletarClientePorId() {
        service.deletar(1);

        verify(repository).deleteById(1);
    }

    private Cliente criarCliente(Integer id, String nome) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf("12345678909");
        return cliente;
    }
}

