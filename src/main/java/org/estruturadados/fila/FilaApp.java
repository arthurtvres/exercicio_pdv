package org.estruturadados.fila;

import org.estruturadados.model.Cliente;
import org.estruturadados.model.Endereco;
import org.estruturadados.model.Compra;
import org.estruturadados.model.ItemCompra;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FilaApp {
    public static void main(String[] args) {
        Fila<Cliente> filaUsuarios = new Fila<>(10);
        Fila<Compra> filaCompras = new Fila<>(10);

        // Cadastrar usuarios (enfileirar)
        filaUsuarios.enfileirar(new Cliente(
                "12312312312",
                "Lucas Martins",
                "(11) 91234-5678",
                LocalDate.of(1988, 5, 10),
                new Endereco("Rua das Flores", "123", null, "Centro", "São Paulo", "SP", "01000-000")
        ));
        filaUsuarios.enfileirar(new Cliente(
                "32132132132",
                "Marina Souza",
                "(21) 99876-5432",
                LocalDate.of(1995, 8, 25),
                new Endereco("Avenida Brasil", "456", "Apto 101", "Copacabana", "Rio de Janeiro", "RJ", "22000-000")
        ));
        filaUsuarios.enfileirar(new Cliente(
                "45678912309",
                "Carlos Lima",
                "(31) 93456-7890",
                LocalDate.of(1990, 12, 3),
                new Endereco("Praça da Liberdade", "789", null, "Savassi", "Belo Horizonte", "MG", "30140-010")
        ));

        // Atender usuarios por ordem de chegada e gerar compras
        while (!filaUsuarios.estaVazia()) {
            Cliente u = filaUsuarios.desenfileirar();
            Compra c = new Compra(u);
            c.adicionarItem(new ItemCompra("Teclado Mecânico", 1, new BigDecimal("299.90")));
            c.adicionarItem(new ItemCompra("Cadeira Gamer", 1, new BigDecimal("999.90")));
            c.aplicarDesconto(new BigDecimal("50.00"));
            filaCompras.enfileirar(c);
            System.out.println("Gerado " + c);
        }

        System.out.println("\nProcessando compras: ");
        while (!filaCompras.estaVazia()) {
            Compra c = filaCompras.desenfileirar();
            c.finalizar();
            System.out.println("Finalizado " + c);
        }

        System.out.println("\nFluxo com fila concluído.");
    }
}