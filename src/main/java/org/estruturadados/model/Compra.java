package org.estruturadados.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compra {
    private UUID id;
    private LocalDateTime data;
    private Cliente usuario;
    private List<ItemCompra> itens;
    private BigDecimal desconto; // absoluto
    private StatusPedido status;

    public Compra(Cliente usuario) {
        this.id = UUID.randomUUID();
        this.data = LocalDateTime.now();
        this.usuario = usuario;
        this.itens = new ArrayList<>();
        this.desconto = BigDecimal.ZERO;
        this.status = StatusPedido.ABERTO;
    }

    public void adicionarItem(ItemCompra item) {
        if (status != StatusPedido.ABERTO) throw new IllegalStateException("Compra não está aberta");
        itens.add(item);
    }

    public void aplicarDesconto(BigDecimal valor) {
        if (valor == null || valor.signum() < 0) throw new IllegalArgumentException("Desconto inválido");
        this.desconto = valor;
    }

    public BigDecimal getTotalBruto() {
        return itens.stream()
                .map(ItemCompra::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalLiquido() {
        BigDecimal bruto = getTotalBruto();
        BigDecimal liq = bruto.subtract(desconto);
        return liq.signum() < 0 ? BigDecimal.ZERO : liq;
    }

    public UUID getId() { return id; }
    public LocalDateTime getData() { return data; }
    public Cliente getCliente() { return usuario; }
    public List<ItemCompra> getItens() { return itens; }
    public BigDecimal getDesconto() { return desconto; }
    public StatusPedido getStatus() { return status; }

    public void finalizar() { this.status = StatusPedido.FINALIZADO; }
    public void cancelar() { this.status = StatusPedido.CANCELADO; }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", itens=" + itens.size() +
                ", bruto=" + getTotalBruto() +
                ", desconto=" + desconto +
                ", liquido=" + getTotalLiquido() +
                ", status=" + status +
                '}';
    }
}
