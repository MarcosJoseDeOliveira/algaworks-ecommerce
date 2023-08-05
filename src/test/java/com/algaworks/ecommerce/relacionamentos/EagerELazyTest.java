package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class EagerELazyTest extends EntityManagerTest {
    @Test
    public void verificarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

//        pedido.getItens().isEmpty();
    }
}
