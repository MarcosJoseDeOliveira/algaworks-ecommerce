package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirRegistro() {
        Cliente cliente = new Cliente();

//        cliente.setId(3);
        cliente.setNome("Rejane Abadia");
        cliente.setSexo(SexoCliente.FEMININO);
        cliente.setCpf("222.333.444-55");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
    }

    @Test
    public void buscarPorIdentificador() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals("Fernando Medeiros", cliente.getNome());
    }

    @Test
    public void atualizarRegistro() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Marcos Kalebe");
        cliente.setCpf("111.222.333-44");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertEquals("Marcos Kalebe", clienteVerificacao.getNome());
    }

    @Test
    public void removerRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNull(clienteVerificacao);
    }
}
