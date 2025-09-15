package org.estruturadados.pilha;

import org.estruturadados.model.Cliente;
import org.estruturadados.model.Endereco;
import org.estruturadados.model.Compra;
import org.estruturadados.model.ItemCompra;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Pilha {
    public static void main(String[] args) {
        PilhaEncadeada<Cliente> pilhaClientes = new PilhaEncadeada<>();
        PilhaEncadeada<Compra> pilhaCompras = new PilhaEncadeada<>();

        // Adicionar clientes à pilha
        pilhaClientes.empilhar(new Cliente(
                "12345678901",
                "Lucas Martins",
                "(11) 91234-5678",
                LocalDate.of(1988, 5, 10),
                new Endereco("Rua das Flores", "123", null, "Centro", "São Paulo", "SP", "01000-000")
        ));
        pilhaClientes.empilhar(new Cliente(
                "98765432100",
                "Marina Souza",
                "(21) 99876-5432",
                LocalDate.of(1995, 8, 25),
                new Endereco("Avenida Brasil", "456", "Apto 101", "Copacabana", "Rio de Janeiro", "RJ", "22000-000")
        ));
        pilhaClientes.empilhar(new Cliente(
                "45678912309",
                "Carlos Lima",
                "(31) 93456-7890",
                LocalDate.of(1990, 12, 3),
                new Endereco("Praça da Liberdade", "789", null, "Savassi", "Belo Horizonte", "MG", "30140-010")
        ));

        // Atender clientes na ordem inversa e gerar pedidos
        while (!pilhaClientes.estaVazia()) {
            Cliente c = pilhaClientes.desempilhar();
            Compra p = new Compra(c);
            p.adicionarItem(new ItemCompra("Camiseta", 2, new BigDecimal("59.90")));
            p.adicionarItem(new ItemCompra("Boné", 1, new BigDecimal("39.90")));
            p.aplicarDesconto(new BigDecimal("10.00"));
            pilhaCompras.empilhar(p);
            System.out.println("Gerado " + p);
        }

        System.out.println("\nProcessando pedidos (ordem inversa):");
        while (!pilhaCompras.estaVazia()) {
            Compra p = pilhaCompras.desempilhar();
            p.finalizar();
            System.out.println("Finalizado " + p);
        }

        System.out.println("\nFluxo com pilha concluído.");
    }
}