package main.java.com.empresa.vendas;

import java.util.Random;

public class BancoDeDados {
    private static final Random random = new Random();

    public boolean verificarCredito(Cliente cliente) {
        // Simula verificação de crédito (80% de chance de aprovação)
        return random.nextDouble() < 0.8;
    }

    public boolean emitirNotaFiscal(Pedido pedido) {
        // Simula emissão de nota fiscal (sempre bem sucedida)
        return true;
    }
}