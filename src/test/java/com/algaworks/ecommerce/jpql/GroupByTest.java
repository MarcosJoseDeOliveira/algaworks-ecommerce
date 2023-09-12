package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void condicionarAgrupamentoComHaving() {
        // Total de vendas dentre as categorias que mais vendem.
        String jpql = "select cat.nome, sum(ip.precoProduto) from ItemPedido ip " +
                " join ip.produto pro join pro.categorias cat " +
                " group by cat.id " +
                " having sum(ip.precoProduto) > 1500";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));

    }

    @Test
    public void agruparEFiltrarResultado() {
        // Total de vendas por mês.
        // String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
        //        " from Pedido p " +
        //        " where year(p.dataCriacao) = year(current_date) " +
        //        " group by concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)) " +
        //        " order by concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)) ";

        // Total de vendas por categoria.
         String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
                " join ip.produto pro join pro.categorias c join ip.pedido p " +
                " where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) = month(current_date) " +
                " group by c.id";

        // Total de vendas por cliente
        // String jpql = "select c.nome, sum(c.id) from Pedido p join p.cliente c " +
        //        " group by c.nome ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));

    }

    @Test
    public void agruparResultado() {
        // Quantidade de produtos por categoria;
        // String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";

        // Total de vendas por mês.
        // String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
        //        " from Pedido p " +
        //        " group by year(p.dataCriacao), month(p.dataCriacao) ";

        // Total de vendas por categoria.
        // String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
        //        " join ip.produto pro join pro.categorias c " +
        //        " group by c.id";

        // Total de vendas por cliente
        // String jpql = "select c.nome, sum(c.id) from Pedido p join p.cliente c " +
        //        " group by c.nome ";

        // Total de vendas por dia e por categoria

        String jpql = "select day(p.dataCriacao), ct.nome, count(p.id) from Pedido p " +
                " join p.itens i join i.produto pd join pd.categorias ct " +
                " group by day(p.dataCriacao), ct.nome ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]+ " - " + arr[2]));

    }

}
