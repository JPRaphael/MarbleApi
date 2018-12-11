package com.jpraphael.marble.viewmodel;

public class ItemPedido {
    private String nome;
    private Double valorPedra;
    private Double quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorPedra() {
        return valorPedra;
    }

    public void setValorPedra(Double valorPedra) {
        this.valorPedra = valorPedra;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public ItemPedido(String nome, Double valorPedra, Double quantidade) {
        this.nome = nome;
        this.valorPedra = valorPedra;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "nome='" + nome + '\'' +
                ", valorPedra=" + valorPedra +
                ", quantidade=" + quantidade +
                '}';
    }


}
