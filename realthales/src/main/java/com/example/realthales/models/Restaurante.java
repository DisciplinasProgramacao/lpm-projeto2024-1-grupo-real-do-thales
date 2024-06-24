package com.example.realthales.models;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Restaurante {

	private static int MAX_MESAS = 10;
	private List<Mesa> mesas;
	private List<Cliente> clientes;
	private List<Requisicao> atendidas;
	private List<Requisicao> espera;
	private Cardapio cardapio;

	public Restaurante() throws FileNotFoundException {
		mesas = new ArrayList<>(MAX_MESAS);
		clientes = new ArrayList<>();
		atendidas = new ArrayList<>();
		espera = new ArrayList<>();
		criarMesas();
		cardapio = new Cardapio();
	}

	private void criarMesas() {

		for (int i = 0; i < 4; i++) {
			mesas.add(new Mesa(4));
		}
		for (int i = 0; i < 4; i++) {
			mesas.add(new Mesa(6));
		}
		for (int i = 0; i < 2; i++) {
			mesas.add(new Mesa(8));
		}
	}

	public void addCliente(Cliente novo) {
		clientes.add(novo);
	}

	public Cliente localizarCliente(int idCli) throws IllegalArgumentException {
		return clientes.stream()
				.filter(cliente -> cliente.hashCode() == idCli)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
	}

	private Mesa localizarMesaDisponivel(int quantPessoas) throws IllegalStateException {
		return mesas.stream()
				.filter(mesa -> mesa.estahLiberada(quantPessoas))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Não há mesas disponíveis para a quantidade de pessoas"));
	}

	public Requisicao encerrarAtendimento(int idMesa) throws IllegalArgumentException {
		Requisicao encerrada = atendidas.stream()
				.filter(requisicao -> !requisicao.estahEncerrada() && requisicao.ehDaMesa(idMesa))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado para a mesa " + idMesa));

		encerrada.encerrar();
		return encerrada;
	}

	public Requisicao processarFila() throws IllegalStateException {
		Requisicao atendida = espera.stream()
				.filter(requisicao -> {
					try {
						Mesa mesaLivre = localizarMesaDisponivel(requisicao.quantPessoas());
						if (mesaLivre != null) {
							atenderRequisicao(requisicao, mesaLivre);
							return true;
						}
					} catch (IllegalStateException e) {
						// Continue checking other requests
					}
					return false;
				})
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Nenhuma requisição pode ser atendida no momento"));

		espera.remove(atendida);
		return atendida;
	}

	public void registrarRequisicao(Requisicao numeroMesa) {
		espera.add(numeroMesa);
	}

	private void atenderRequisicao(Requisicao requisicao, Mesa mesa) {
		requisicao.alocarMesa(mesa);
		atendidas.add(requisicao);
	}

	public String statusMesas() {
		StringBuilder status = new StringBuilder();
		status.append("Mesas livres: \n");
		mesas.stream()
				.filter(mesa -> mesa.estahLiberada(1))
				.forEach(mesa -> status.append(mesa).append("\n"));
		status.append("Mesas em atendimento: \n");
		mesas.stream()
				.filter(mesa -> !mesa.estahLiberada(1))
				.forEach(mesa -> status.append(mesa).append("\n"));
		return status.toString();
	}

	public String filaDeEspera() {
		if (espera.isEmpty()) {
			return "Fila vazia";
		}
		return espera.stream()
				.map(Requisicao::toString)
				.collect(Collectors.joining("\n", "Fila de espera: \n", ""));
	}

	public String exibirCardapio() {
		return cardapio.toString();
	}

	public String adicionarProdutoAMesa(int codigoProduto, int idMesa) throws IllegalArgumentException {
		Produto produto = Optional.ofNullable(cardapio.getProduto(codigoProduto))
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

		Requisicao requisicao = atendidas.stream()
				.filter(req -> req.ehDaMesa(idMesa) && !req.estahEncerrada())
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada ou atendimento já encerrado"));

		requisicao.adicionarProdutoAoPedido(produto);
		return "Produto adicionado à mesa " + idMesa;
	}
}