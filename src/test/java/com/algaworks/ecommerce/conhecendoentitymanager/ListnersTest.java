package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListnersTest extends EntityManagerTest {

    @Test
    public void carregarEntidades() {
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = entityManager.find(Pedido.class, 1);
    }


    @Test
    public void acionarCallbacks() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assertions.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }
}
