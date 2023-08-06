package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarXmlNota() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarArquivo("/nota-fiscal.xml"));

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVerificacao.getXml());
        Assertions.assertTrue(notaFiscalVerificacao.getXml().length > 0);

        /*
        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(
                            System.getProperty("user.home") + "/xml.xml")).toFile());
            out.write(notaFiscalVerificacao.getXml());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        */
    }

    @Test
    public void salvarFoto() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Produto produto = new Produto();
        produto.setNome("Pendrive Kingston");
        produto.setDescricao("Capacidade de 8GB 2.0 Vermerlho");
        produto.setPreco(BigDecimal.TEN);
        produto.setFoto(carregarArquivo("/pendrive.webp"));


        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao.getFoto());
        Assertions.assertTrue(produtoVerificacao.getFoto().length > 0);

        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(
                            System.getProperty("user.home") + "/foto.jpg")).toFile());
            out.write(produtoVerificacao.getFoto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] carregarArquivo(String arquivo) {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(arquivo).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}