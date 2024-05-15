package com.example.realthales.models;

public class Item{

    private String descricao;
    private double preco;

    public Item(String descricao, double preco){
        if(preco <=0 ) preco = 0.1;
        if(descricao.length()==0) descricao = "Sem definição";
        this.preco = preco;
        this.descricao = descricao;
    }

    public double getPreco(){
        return preco;
    }

    @Override
    public String toString(){
        return descricao + " - R$ "+ String.format("%.2f", preco);
    }

}