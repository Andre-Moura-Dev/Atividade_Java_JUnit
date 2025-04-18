package main.java.com.empresa.vendas;

public class Financeiro {
    private Banco banco;

    public Financeiro(Banco banco) {
        this.banco = banco;
    }

    public boolean processarCobranca(Pedido pedido) {
        double valor = calcularValorPedido(pedido);

        if (banco.processarPagamento(pedido.getCliente(), valor)) {
            System.out.println("Pagamento do pedido " + pedido.getNumero() + " processado com sucesso!");
            return true;
        } else {
            System.out.println("Cliente " + pedido.getCliente().getNome() + " inadimplente. Enviando cobrança...");
            banco.enviarCobranca(pedido.getCliente(), valor);
            return false;
        }
    }

    private double calcularValorPedido(Pedido pedido) {
        // Simula cálculo do valor baseado na quantidade de itens
        return pedido.getItens().size() * 50.0;
    }
}