package com.example.realthales.models;

import java.util.Scanner;

public class MenuFechado {

    private static final double PRECO_MENU = 32.00;
    private static final double TAXA_SERVICO = 0.10; // 10%

    public void FazerPedidoFechado(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exibir opções de comida
        System.out.println("Escolha uma comida:");
        String[] comidas = {"1. Falafel Assado", "2. Caçarola de Legumes"};
        for (String comida : comidas) {
            System.out.println(comida);
        }
        int opcaoComida = scanner.nextInt();

        // Exibir opções de bebida
        System.out.println("Escolha duas bebidas:");
        String[] bebidas = {"1. Copo de Suco", "2. Refrigerante Orgânico", "3. Cerveja Vegana"};
        for (String bebida : bebidas) {
            System.out.println(bebida);
        }
        int opcaoBebida1 = scanner.nextInt();
        int opcaoBebida2 = scanner.nextInt();

        // Validar escolhas
        if (opcaoComida < 1 || opcaoComida > comidas.length || 
            opcaoBebida1 < 1 || opcaoBebida1 > bebidas.length || 
            opcaoBebida2 < 1 || opcaoBebida2 > bebidas.length || 
            opcaoBebida1 == opcaoBebida2) {
            System.out.println("Escolhas inválidas. Por favor, tente novamente.");
            return;
        }

        // Mostrar seleção
        System.out.println("Você escolheu:");
        System.out.println("Comida: " + comidas[opcaoComida - 1].substring(3)); // Remover número da opção
        System.out.println("Bebida 1: " + bebidas[opcaoBebida1 - 1].substring(3));
        System.out.println("Bebida 2: " + bebidas[opcaoBebida2 - 1].substring(3));

        // Calcular o custo total com taxa de serviço
        double totalComTaxa = PRECO_MENU + (PRECO_MENU * TAXA_SERVICO);
        System.out.printf("O custo total com taxa de serviço é: R$ %.2f%n", totalComTaxa);

        
        
    }

}