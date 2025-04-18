package main.java.com.empresa.vendas;

public class Vendas {
    private BancoDeDados bancoDeDados;
    private Expedicao expedicao;
    private Financeiro financeiro;

    public Vendas(BancoDeDados bancoDeDados, Expedicao expedicao, Financeiro financeiro) {
        this.bancoDeDados = bancoDeDados;
        this.expedicao = expedicao;
        this.financeiro = financeiro;
    }

    public void processarPedido(Pedido pedido) {
        // 1. Verificar crédito do cliente
        if (!bancoDeDados.verificarCredito(pedido.getCliente())) {
            pedido.setStatus(StatusPedido.CANCELADO);
            System.out.println("Pedido " + pedido.getNumero() + " cancelado devido a problemas de crédito.");
            return;
        }

        // 2. Emitir nota fiscal
        if (!bancoDeDados.emitirNotaFiscal(pedido)) {
            pedido.setStatus(StatusPedido.CANCELADO);
            System.out.println("Pedido " + pedido.getNumero() + " cancelado - falha na emissão da nota fiscal.");
            return;
        }

        pedido.setStatus(StatusPedido.APROVADO);
        System.out.println("Pedido " + pedido.getNumero() + " aprovado.");

        // 3. Processar expedição
        pedido.setStatus(StatusPedido.EM_EXPEDICAO);
        if (!expedicao.separarMercadorias(pedido)) {
            pedido.setStatus(StatusPedido.CANCELADO);
            System.out.println("Pedido " + pedido.getNumero() + " cancelado - falha na expedição.");
            return;
        }

        // 4. Processar cobrança
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        if (financeiro.processarCobranca(pedido)) {
            pedido.setStatus(StatusPedido.FINALIZADO);
            System.out.println("Pedido " + pedido.getNumero() + " finalizado com sucesso!");
        } else {
            pedido.setStatus(StatusPedido.CANCELADO);
            System.out.println("Pedido " + pedido.getNumero() + " cancelado devido a inadimplência.");
        }
    }
}