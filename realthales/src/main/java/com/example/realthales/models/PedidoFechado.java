package com.example.realthales.models;

import java.util.Scanner;

public class PedidoFechado extends Pedido{

    private static final double MENU_FIXO_PRECO = 32;
    private static final double TAXA_SERVICO = 0.10;
    private int quantidadePessoas;

    public PedidoFechado() {
        super();
    }

    public void recebeQuantidadePessoas(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Quantas pessoas irão pedir? ");
        quantidadePessoas = teclado.nextInt();
        teclado.close();
    }

    @Override
    public float calcularValorTotal() {
        return (float) ((MENU_FIXO_PRECO * quantidadePessoas) * TAXA_SERVICO);
    }

    @Override
    public void adicionarProduto(Produto produto) {
        if (EProdutoMenuFechado.produtoEhValido(produto.getIdProduto())) {
            getProdutos().add(produto);
        } else {
            throw new IllegalArgumentException("Opção não está disponível no menu fechado.");
        }
    }

    public String mostrarPedido() {
        StringBuilder mostra = new StringBuilder();
        mostra.append("Itens do Pedido (Menu Fechado):\n");
        mostra.append(String.format(" - Comida e Bebidas (Preço Fixo): R$%.2f\n", MENU_FIXO_PRECO));
        mostra.append(String.format("Preço Total: R$%.2f\n", calcularValorTotal()));
        return mostra.toString();
    }
}

