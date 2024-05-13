package com.example.realthales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RealthalesApplication {

	static Scanner teclado;
    static Restaurante restaurante;

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static void cabecalho() {
        limparTela();
        System.out.println(" LPM VEGAN - v0.1 ");
        System.out.println("=====================");
    }

    static int MenuPrincipal() {
        int opcao;
        cabecalho();    
        System.out.println("1 - Verificar Mesas");
        System.out.println("2 - Verificar Fila");
        System.out.println("3 - Solicitar Mesa");
        System.out.println("4 - Encerrar Mesa");
        System.out.println("5 - Processar Fila");
		System.out.println("6 - Verificar Pedidos");
		System.out.println("7 - Cadastrar Pedido");
		System.out.println("8 - Encerrar Pedido");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static void mostrarFila(){
        cabecalho();
        System.out.println(restaurante.filaDeEspera());
        pausa();
    }

    static void exibirMesas() {
        cabecalho();
        System.out.println(restaurante.statusMesas());
        pausa();
    }
    
    static void solicitarMesa() {
        int idCli;
        cabecalho();
        System.out.print("Digite o id do cliente: ");
        idCli = Integer.parseInt(teclado.nextLine());
        Cliente localizado = restaurante.localizarCliente(idCli);
        if (localizado == null) {
            System.out.println("Cliente não localizado. Vamos cadastrá-lo:");
            localizado = cadastrarNovoCliente();
            restaurante.addCliente(localizado);
        }
        criarRequisicao(localizado);
    }

    static Cliente cadastrarNovoCliente() {
        String nome;
        System.out.print("Nome do cliente: ");
        nome = teclado.nextLine();
        Cliente novo = new Cliente(nome);
        System.out.println("Cliente cadastrado: " + novo);
        pausa();
        return novo;
    }

    static void criarRequisicao(Cliente cliente) {
        int quantasPessoas;
        cabecalho();
        System.out.print("Para quantas pessoas será a mesa? ");
        quantasPessoas = Integer.parseInt(teclado.nextLine());
        Requisicao novaRequisicao = new Requisicao(quantasPessoas, cliente);
        restaurante.registrarRequisicao(novaRequisicao);
        novaRequisicao = processarFila();
        if(novaRequisicao!=null){
            System.out.println(novaRequisicao);
        } else {
            System.out.println("Não há mesas disponíveis no momento. Requisição em espera");
        }
        pausa();
    }

    static Requisicao processarFila() {
       return restaurante.processarFila();
    }

    static void encerrarMesa() {
        int idMesa;
        cabecalho();
        System.out.print("Qual o número da mesa para encerrar atendimento? ");
        idMesa = Integer.parseInt(teclado.nextLine());
        Requisicao finalizada = restaurante.encerrarAtendimento(idMesa);
        if (finalizada != null) {
            System.out.println(finalizada);
        } else {
            System.out.println("Mesa " + idMesa + " não está em atendimento");
        }
        pausa();
    }

	static void cadastrarPedido(){
        int idMesa;
        int idPedido;
        String produtosPedidos;
        System.out.println("Qual o número da mesa que deseja cadastrar o pedido? ");
        idMesa = Integer.parseInt(teclado.nextLine());
        Pedido novo = new Pedido();
        System.out.println("Pedido da mesa " + idMesa + "de id #" + idPedido + ": " + novo.adicionarProduto());
    }

    static void vizaulizarPedidos(){
            String nome;
            System.out.println("Pedidos do restaurante " + nome + ":");
            for (Pedido pedido : pedidos) {
                System.out.println("Pedido #" + pedido.getNumero() + ":");
                System.out.println("  Mesa: " + Mesa.idMesa);
                System.out.println("  Itens do pedido:");
                for (Produto item : pedido.getItens()) {
                    System.out.println("    - " + item.getNome() + ": R$" + item.getPreco());
                }
                System.out.println("  Total: R$" + pedido.getTotal());
                System.out.println("-------------");
            }
    }
	public static void main(String[] args) {
		SpringApplication.run(RealthalesApplication.class, args);
		teclado = new Scanner(System.in);
        int opcao;
        restaurante = new Restaurante();
        do {
            opcao = MenuPrincipal();
            switch (opcao) {
                case 1 -> exibirMesas();
                case 2 -> mostrarFila();
                case 3 -> solicitarMesa();
                case 4 -> encerrarMesa();
                case 5 -> {
                    Requisicao atendida = processarFila();
                    if(atendida!=null){
                        System.out.println(atendida);
                    }
                    else{
                        System.out.println("Fila vazia ou mesas não disponíveis. Favor verificar a situação.");
                    }
                    pausa();
                }

            }
        } while (opcao != 0);
        teclado.close();
	}

}