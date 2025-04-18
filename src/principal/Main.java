package principal;

import main.java.com.empresa.vendas.*;

public class Main {
        public static void main(String[] args) {
            BancoDeDados bancoDeDados = new BancoDeDados();
            Expedicao expedicao = new Expedicao();
            Financeiro financeiro = new Financeiro(new Banco());
            Vendas vendas = new Vendas(bancoDeDados, expedicao, financeiro);

            // Criar um pedido de exemplo
            Cliente cliente = new Cliente("Carlos Oliveira", "555.666.777-88");
            Pedido pedido = new Pedido(1001, cliente);
            pedido.adicionarItem("Notebook");
            pedido.adicionarItem("Mouse");
            pedido.adicionarItem("Teclado");

            // Processar o pedido
            vendas.processarPedido(pedido);
        }
    }