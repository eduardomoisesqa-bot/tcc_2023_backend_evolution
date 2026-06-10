package io.github.tcc_2023.clientes.rest;

import io.github.tcc_2023.clientes.model.entity.Cliente;
import io.github.tcc_2023.clientes.model.entity.ServicosPrestados;
import io.github.tcc_2023.clientes.model.repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceControllerTest {

    @Mock
    private ServiceRepository repository;

    @InjectMocks
    private ServiceController controller;

    @Test
    void deveSalvarServicoPrestado() {
        ServicosPrestados servico = criarServico(1, "Manutencao");

        when(repository.save(servico)).thenReturn(servico);

        ServicosPrestados resultado = controller.salvar(servico);

        assertSame(servico, resultado);
        verify(repository).save(servico);
    }

    @Test
    void deveBuscarServicoPorId() {
        ServicosPrestados servico = criarServico(1, "Manutencao");

        when(repository.findById(1)).thenReturn(Optional.of(servico));

        ServicosPrestados resultado = controller.buscarServico(1);

        assertEquals(1, resultado.getId());
        assertEquals("Manutencao", resultado.getDescricao());
        verify(repository).findById(1);
    }

    @Test
    void deveLancarNotFoundQuandoServicoNaoForEncontradoAoBuscar() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.buscarServico(1));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(repository).findById(1);
    }

    @Test
    void deveAtualizarServicoPrestado() {
        ServicosPrestados servicoExistente = criarServico(1, "Manutencao");
        ServicosPrestados servicoAtualizado = criarServico(null, "Instalacao");

        when(repository.findById(1)).thenReturn(Optional.of(servicoExistente));
        when(repository.save(servicoAtualizado)).thenReturn(servicoAtualizado);

        controller.atualizar(1, servicoAtualizado);

        assertEquals(1, servicoAtualizado.getId());
        verify(repository).findById(1);
        verify(repository).save(servicoAtualizado);
    }

    @Test
    void deveLancarNotFoundQuandoServicoNaoForEncontradoAoAtualizar() {
        ServicosPrestados servicoAtualizado = criarServico(null, "Instalacao");

        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.atualizar(1, servicoAtualizado));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(repository).findById(1);
        verify(repository, never()).save(servicoAtualizado);
    }

    @Test
    void deveValidarExistenciaAntesDeDeletarServico() {
        ServicosPrestados servico = criarServico(1, "Manutencao");

        when(repository.findById(1)).thenReturn(Optional.of(servico));

        controller.deletar(1);

        verify(repository).findById(1);
    }

    @Test
    void deveLancarNotFoundQuandoServicoNaoForEncontradoAoDeletar() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.deletar(1));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(repository).findById(1);
    }

    private ServicosPrestados criarServico(Integer id, String descricao) {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Eduardo");
        cliente.setCpf("12345678909");

        ServicosPrestados servico = new ServicosPrestados();
        servico.setId(id);
        servico.setDescricao(descricao);
        servico.setValor(BigDecimal.TEN);
        servico.setCliente(cliente);
        return servico;
    }
}
