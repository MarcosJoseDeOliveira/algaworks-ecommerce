package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PropriedadesTransientesTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertEquals("Fernando", cliente.getPrimeiroNome());
   }
}