package com.example.realthales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.realthales.models.Pedido;
import com.example.realthales.models.Produto;

public class PedidoTest {
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
    }

    @Test
    public void testAdicionarProduto() {
        Produto produto = new Produto("Produto 1", 10.0);
        pedido.adicionarProduto(produto);

        List<Produto> produtos = pedido.getProdutos();
        assertEquals(1, produtos.size());
        assertEquals(produto, produtos.get(0));
    }

    @Test
    public void testCalcularValorTotal() {
        Produto produto1 = new Produto("Produto 1", 10.0);
        Produto produto2 = new Produto("Produto 2", 20.0);
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        float valorTotal = pedido.calcularValorTotal();
        assertEquals(30.0, valorTotal, 0.01);
    }

    @Test
    public void testCalcularValor10() {
        Produto produto1 = new Produto("Produto 1", 10.0);
        Produto produto2 = new Produto("Produto 2", 20.0);
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        float valorTotal = pedido.calcularValorTotal();
        float valorCom10 = pedido.calcularValor10();
        assertEquals(30.0, valorTotal, 0.01);
        assertEquals(33.0, valorCom10, 0.01);
    }
}
