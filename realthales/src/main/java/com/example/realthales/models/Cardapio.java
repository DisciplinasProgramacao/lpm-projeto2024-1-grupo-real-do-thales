package com.example.realthales.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cardapio {
    
    private List<Item> produtos;
    private String arquivoProdutos;
    
    public Cardapio() throws FileNotFoundException{
        produtos = new ArrayList<>(11);
        arquivoProdutos = "dadosProdutos.csv";
        carregarCardapio(arquivoProdutos);
    }

    private void carregarCardapio(String nomeArquivo) throws FileNotFoundException{
        Scanner arquivo = new Scanner(new File(arquivoProdutos));
        String linha = arquivo.nextLine();
        while(arquivo.nextLine()!=null){
            String[] detalhes = linha.split(";");
            String desc = detalhes[0];
            double valor = Double.parseDouble(detalhes[1]);
            Item produto = new Item(desc, valor);
            produtos.add(produto);
        }
    }

    @Override
    public String toString(){
        StringBuilder cardapio = new StringBuilder("CARDÁPIO: \n");
        int index = 1;
        for (Item item : produtos) {
            cardapio.append(index + " - "+item+"\n");
            index++;
        }
        return cardapio.toString();
    }

    public Item getProduto(int index){
        int pos = index-1;
        Item prod = null;
        if(pos>=0 && pos < produtos.size()){
            prod = produtos.get(pos);
        }
        return prod;
    }
}
