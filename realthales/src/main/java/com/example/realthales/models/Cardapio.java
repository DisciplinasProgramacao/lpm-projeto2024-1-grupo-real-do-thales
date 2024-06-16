package com.example.realthales.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cardapio {

    private List<Produto> produtos;
    private String arquivoProdutos;

    public Cardapio() throws FileNotFoundException {
        produtos = new ArrayList<>(11);
        arquivoProdutos = "realthales/src/main/resources/dadosProdutos.csv";
        carregarCardapio(arquivoProdutos);
    }

    private void carregarCardapio(String nomeArquivo) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(nomeArquivo));
        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine();
            String[] detalhes = linha.split(";");
            String desc = detalhes[0];
            double valor = Double.parseDouble(detalhes[1]);
            Produto produto = new Produto(desc, valor);
            produtos.add(produto);
        }
        arquivo.close();
    }

    @Override
    public String toString() {
        StringBuilder cardapio = new StringBuilder("CARDÁPIO: \n");
        int index = 1;
        for (Produto item : produtos) {
            cardapio.append(index + " - " + item + "\n");
            index++;
        }
        return cardapio.toString();
    }

    public Produto getProduto(int index) {
        int pos = index - 1;
        Produto prod = null;
        if (pos >= 0 && pos < produtos.size()) {
            prod = produtos.get(pos);
        }
        return prod;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}