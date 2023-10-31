package com.example;

public class Produto {
    private String produto;
    private double preco;
    private double estoque;

    public Produto(String produto, double preco, double estoque) {
        this.produto = produto;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }
}
