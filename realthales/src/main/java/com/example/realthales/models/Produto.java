package com.example.realthales.models;

public class Produto {
    int idProduto;
    String nomeProduto;
    Double valorProduto;

    public Produto(int idProduto, String nomeProduto, Double valorProduto) {
        setIdProduto(idProduto);
        if (valorProduto <= 0)
            valorProduto = 0.1;
        if (nomeProduto.length() == 0)
            nomeProduto = "Sem definição";
        this.valorProduto = valorProduto;
        this.nomeProduto = nomeProduto;
    }

    public String getNome() {
        return nomeProduto;
    }

    public Double getValor() {
        return valorProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int id) {
        this.idProduto = id;
    }

    @Override
    public String toString() {
        return idProduto + "." + nomeProduto + " - R$ " + String.format("%.2f", valorProduto);
    }

}
