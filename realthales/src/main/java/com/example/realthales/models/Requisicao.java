package com.example.realthales.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Requisicao {

	private Cliente cliente;
	private Mesa mesa;
	private Pedido pedido;
	private int quantPessoas;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private boolean encerrada;

	public Requisicao(int quantPessoas, Cliente cliente) {
		this.quantPessoas = 1;
		if(quantPessoas > 1 )
			this.quantPessoas = quantPessoas;
		this.cliente = cliente;
		entrada = saida = null;
		mesa = null;
		encerrada = false;
		pedido = new Pedido();
	}

	public Mesa encerrar() {
		saida = LocalDateTime.now();
		mesa.desocupar();
		encerrada = true;
		return mesa;
	}

	public void alocarMesa(Mesa mesa) {
		if(mesa.estahLiberada(quantPessoas)){
			this.mesa = mesa;
			entrada = LocalDateTime.now();
			this.mesa.ocupar();
		}
	}

	public boolean estahEncerrada(){
		return encerrada;
	}

	public boolean ehDaMesa(int idMesa){
		return idMesa == mesa.getIdMesa();
	}

	public int quantPessoas(){
		return quantPessoas;
	}

	public String toString(){
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		StringBuilder stringReq = new StringBuilder(cliente.toString());
		if(mesa!=null){
			stringReq.append("\n"+mesa.toString()+"\n");
			stringReq.append("Entrada em "+ formato.format(entrada)+"\n");
			if(saida!=null)
				stringReq.append("Saída em "+formato.format(saida)+"\n");
		}
		else{
			stringReq.append(" Em espera.");
		}
		return stringReq.toString();
	}

	public void adicionarProdutoAoPedido(Produto produto) {
        pedido.adicionarProduto(produto);
    }

    public List<Produto> fecharConta() {
        pedido.calcularValorTotal();
        float valorTotal = pedido.getValorTotal();
        float valorCom10 = pedido.calcularValor10();
        List<Produto> produtos = pedido.getProdutos();


        pedido = new Pedido();

        return produtos;
    }
}