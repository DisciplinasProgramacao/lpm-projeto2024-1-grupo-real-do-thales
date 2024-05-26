package com.example.realthales.models;

import java.util.LinkedList;
import java.util.List;

public class Pedido {

    private float valorTotal;
    private float valorCom10;
    private List<Produto> produtos;

    public Pedido(){
        this.valorTotal = 0;
        this.valorCom10 = 0;
        this.produtos = new LinkedList<>();
    }


      public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public float calcularValorTotal() {
        float valorTotal = (float) 0.0;
        for (Produto produto : produtos) {
            valorTotal += produto.getValor();
        }
        return valorTotal;
    }

    public float calcularValor10(){
        valorCom10 = (float) (valorTotal+(valorTotal * 0.10));
        return valorCom10;
    }

}
