package com.example.realthales.models;

public class Produto {
    int idProduto;
    String nomeProduto;
    Double valorProduto;

    public Produto(int idProduto, String nomeProduto, Double valorProduto) {
        setIdProduto(idProduto);
        setNomeProduto(nomeProduto);
        setValorProduto(valorProduto);
    }

    public String getNome() {
        return nomeProduto;
    }

    public void setNomeProduto(String nome) {
        this.nomeProduto = nome;
    }

    public Double getValor() {
        return valorProduto;
    }

    public void setValorProduto(Double valor) {
        this.valorProduto = valor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int id) {
        this.idProduto = id;
    }

}
