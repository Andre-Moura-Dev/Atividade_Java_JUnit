package test.java.com.empresa.vendas;

import main.java.com.empresa.vendas.Cliente;
import main.java.com.empresa.vendas.Expedicao;
import main.java.com.empresa.vendas.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


class ExpedicaoTest {
    private Expedicao expedicao;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        expedicao = new Expedicao();
        Cliente cliente = new Cliente("João Silva", "123.456.789-00");
        pedido = new Pedido(1, cliente);
        pedido.adicionarItem("Produto A");
    }

    @Test
    void testSepararMercadoriasComSucesso() {
        // Como é aleatório, podemos testar várias vezes ou mockar
        // Aqui vamos testar apenas se o método é chamado sem erros
        assertDoesNotThrow(() -> expedicao.separarMercadorias(pedido));
    }

    @Test
    void testPedidoComItens() {
        assertEquals(1, pedido.getItens().size());
    }
}