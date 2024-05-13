package com.example.realthales.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private float valorTotal;
    private float valorCom10;
    private List<Produto> produtos;

    public Pedido() {
        produtos = new ArrayList<>();
    }


      public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }


    public void calcularValorTotal() {
        double valorTotal = 0.0;
        for (Produto produto : produtos) {
            valorTotal += produto.getValor();
        }
    }

    private float calcularValor10(){
        valorCom10 = (float) (valorTotal * 0.10);
        return valorCom10;
    }

}
