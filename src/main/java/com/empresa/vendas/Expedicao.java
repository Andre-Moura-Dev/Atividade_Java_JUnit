package main.java.com.empresa.vendas;

import java.util.Random;

public class Expedicao {
    private static final Random random = new Random();

    public boolean separarMercadorias(Pedido pedido) {
        // Simula separação de mercadorias (90% de chance de sucesso)
        boolean sucesso = random.nextDouble() < 0.9;

        if (sucesso) {
            System.out.println("Mercadorias do pedido " + pedido.getNumero() + " separadas com sucesso!");
        } else {
            System.out.println("Falha ao separar mercadorias do pedido " + pedido.getNumero());
        }

        return sucesso;
    }
}
