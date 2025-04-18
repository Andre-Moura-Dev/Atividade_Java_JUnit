package main.java.com.empresa.vendas;

import java.util.Random;

public class Banco {
    private static final Random random = new Random();

    public boolean processarPagamento(Cliente cliente, double valor) {
        // Simula processamento de pagamento (70% de chance de sucesso)
        return random.nextDouble() < 0.7;
    }

    public boolean enviarCobranca(Cliente cliente, double valor) {
        // Simula envio de cobrança (sempre bem sucedido)
        return true;
    }
}