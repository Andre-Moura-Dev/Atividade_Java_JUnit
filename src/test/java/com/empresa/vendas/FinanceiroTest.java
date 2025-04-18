package test.java.com.empresa.vendas;

import main.java.com.empresa.vendas.Banco;
import main.java.com.empresa.vendas.Financeiro;
import main.java.com.empresa.vendas.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class FinanceiroTest {
    private Financeiro financeiro;
    private Banco bancoMock;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        bancoMock = new Banco() {
            @Override
            public boolean processarPagamento(Cliente cliente, double valor) {
                return true; // Força retorno verdadeiro para teste
            }
        };

        financeiro = new Financeiro(bancoMock);
        Cliente cliente = new Cliente("Maria Souza", "987.654.321-00");
        pedido = new Pedido(2, cliente);
        pedido.adicionarItem("Produto B");
        pedido.adicionarItem("Produto C");
    }

    @Test
    void testProcessarCobrancaComSucesso() {
        assertTrue(financeiro.processarCobranca(pedido));
    }

    @Test
    void testCalcularValorPedido() {
        // 2 itens * 50.0 = 100.0
        Financeiro financeiroReflection = financeiro;
        assertEquals(100.0,
                TestUtils.invokePrivateMethod(financeiroReflection, "calcularValorPedido", pedido));
    }
}

// Classe auxiliar para testar métodos privados
class TestUtils {
    public static double invokePrivateMethod(Object obj, String methodName, Object... args) {
        try {
            var method = obj.getClass().getDeclaredMethod(methodName, Pedido.class);
            method.setAccessible(true);
            return (double) method.invoke(obj, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}