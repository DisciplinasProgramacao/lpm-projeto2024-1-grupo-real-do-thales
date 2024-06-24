package com.example.realthales;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

import com.example.realthales.models.Cardapio;
import com.example.realthales.models.Cliente;
import com.example.realthales.models.Requisicao;
import com.example.realthales.models.Restaurante;
import com.example.realthales.models.MenuFechado;


public class RealthalesApplication {
    static Scanner teclado;
    static Restaurante restaurante;

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
        System.out.println("8 - Cadastrar Pedido Fechado");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    static void mostrarFila() {
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
        if (novaRequisicao != null) {
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

    static void cadastrarPedido() throws FileNotFoundException {
        Cardapio cardapio = new Cardapio();
        cabecalho();
        System.out.println(cardapio);

        System.out.print("Escolha o número do produto: ");
        int numeroProduto = Integer.parseInt(teclado.nextLine());

        System.out.print("Qual o número da mesa? ");
        int numeroMesa = Integer.parseInt(teclado.nextLine());

        // Adiciona o produto à mesa
        String resultado = restaurante.adicionarProdutoAMesa(numeroProduto, numeroMesa);
        System.out.println(resultado);
        pausa();
    }

    static void mostrarPedido() {
        int idMesa;
        cabecalho();
        System.out.print("Digite o número da mesa para ver o pedido: ");
        idMesa = Integer.parseInt(teclado.nextLine());
    
        Optional<Requisicao> requisicao = restaurante.getAtendidas().stream()
            .filter(r -> r.ehDaMesa(idMesa) && !r.estahEncerrada())
            .findFirst();
    
        if (requisicao.isPresent()) {
            System.out.println("Pedido da mesa " + idMesa + ":");
            System.out.println(requisicao.get().pedidoDetalhes());
        } else {
            System.out.println("Mesa " + idMesa + " não está em atendimento ou atendimento já encerrado.");
        }
        pausa();
    }

    static void cadastrarPedidoFechado() {
        cabecalho();
        MenuFechado menuFechado = new MenuFechado();
        menuFechado.FazerPedidoFechado(null);
        pausa();
    }


    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in);
        int opcao;
        restaurante = new Restaurante();
        do {
            opcao = MenuPrincipal();
            switch (opcao) {
                case 1 -> exibirMesas();
                case 2 -> mostrarFila();
                case 3 -> solicitarMesa();
                case 4 -> {
                    System.out.print("Qual o número da mesa para encerrar o pedido? ");
                    int idMesa = Integer.parseInt(teclado.nextLine());
                    Requisicao req = restaurante.encerrarAtendimento(idMesa);
                    if (req != null) {
                        float valorTotal = req.retornarValor();
                        System.out.println("Valor total: " + valorTotal);
                        float valorTotal10 = req.retornarValor10();
                        System.out.println("Valor total com taxa de serviço: " + valorTotal10);
                        float valorPorPessoa = req.retornarValorPorPessoa();
                        System.out.println("Valor por pessoa: " + valorPorPessoa);
                    } else {
                        System.out.println("Mesa " + idMesa + " não está em atendimento");
                    }
                    pausa();
                }
                case 5 -> {
                    Requisicao atendida = processarFila();
                    if (atendida != null) {
                        System.out.println(atendida);
                    } else {
                        System.out.println("Fila vazia ou mesas não disponíveis. Favor verificar a situação.");
                    }
                    pausa();
                }
                case 6 -> mostrarPedido();
                case 7 -> cadastrarPedido();
                case 8 -> cadastrarPedidoFechado();
            }
        } while (opcao != 0);
        teclado.close();
    }
}
