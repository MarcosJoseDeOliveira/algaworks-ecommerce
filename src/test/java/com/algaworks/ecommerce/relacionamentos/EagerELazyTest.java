package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

public class EagerELazyTest extends EntityManagerTest {
    @Test
    public void verificarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

//        pedido.getItens().isEmpty();
    }
}
