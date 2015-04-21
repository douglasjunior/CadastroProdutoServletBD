package br.grupointegrado.cadastroProduto.model;

import java.util.Date;

public class Produto {

    private int codigo;
    private String decricao;
    private double quantidade;
    private double valor;
    private String fornecedor;
    private Date ultimaCompra;

    public Produto() {

    }

    public Produto(int codigo, String decricao, double quantidade, double valor, String fornecedor, Date ultimaCompra) {
        this.codigo = codigo;
        this.decricao = decricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.fornecedor = fornecedor;
        this.ultimaCompra = ultimaCompra;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
