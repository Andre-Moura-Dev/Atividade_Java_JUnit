package test.java.com.empresa.vendas;

import main.java.com.empresa.vendas.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class VendasTest {
    private Vendas vendas;
    private BancoDeDados bancoDeDadosMock;
    private Expedicao expedicaoMock;
    private Financeiro financeiroMock;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        bancoDeDadosMock = new BancoDeDados() {
            @Override
            public boolean verificarCredito(Cliente cliente) {
                return true; // Sempre aprova para teste
            }
        };

        expedicaoMock = new Expedicao() {
            @Override
            public boolean separarMercadorias(Pedido pedido) {
                return true; // Sempre sucesso para teste
            }
        };

        financeiroMock = new Financeiro(new Banco() {
            @Override
            public boolean processarPagamento(Cliente cliente, double valor) {
                return true; // Sempre pago para teste
            }
        });

        vendas = new Vendas(bancoDeDadosMock, expedicaoMock, financeiroMock);

        Cliente cliente = new Cliente("Teste", "111.222.333-44");
        pedido = new Pedido(3, cliente);
        pedido.adicionarItem("Produto Teste");
    }

    @Test
    void testProcessarPedidoComSucesso() {
        vendas.processarPedido(pedido);
        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());
    }

    @Test
    void testProcessarPedidoComFalhaNoCredito() {
        BancoDeDados bdFalhaCredito = new BancoDeDados() {
            @Override
            public boolean verificarCredito(Cliente cliente) {
                return false;
            }
        };

        Vendas vendasFalha = new Vendas(bdFalhaCredito, expedicaoMock, financeiroMock);
        vendasFalha.processarPedido(pedido);
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
    }
}