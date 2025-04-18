package main.java.com.empresa.vendas;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private Cliente cliente;
    private List<String> itens;
    private StatusPedido status;

    public Pedido(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.EM_ANALISE;
    }

    public void adicionarItem(String item) {
        itens.add(item);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<String> getItens() {
        return new ArrayList<>(itens);
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}

public enum StatusPedido {
    EM_ANALISE,
    APROVADO,
    EM_EXPEDICAO,
    AGUARDANDO_PAGAMENTO,
    FINALIZADO,
    CANCELADO
}