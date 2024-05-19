package com.example.realthales.models;

import java.util.List;

public class Pedido {

    private float valorTotal;
    private float valorCom10;
    private List<Produto> produtos;

    public Pedido(float valorTotal, float valorCom10, List<Produto> produtos) {
        this.valorTotal = valorTotal;
        this.valorCom10 = valorCom10;
        this.produtos = produtos;
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
        valorCom10 = (float) (valorTotal * 0.10);
        return valorCom10;
    }

}
