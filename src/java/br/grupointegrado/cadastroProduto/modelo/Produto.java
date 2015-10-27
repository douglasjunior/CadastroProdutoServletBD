package br.grupointegrado.cadastroProduto.modelo;

import br.grupointegrado.cadastroProduto.util.Util;
import java.util.Date;

public class Produto {

    private int id;
    private String descricao;
    private double quantidade;
    private double valor;
    private String fornecedor;
    private Date ultimaCompra;

    public Produto(int id, String descricao, double quantidade, double valor,
            String fornecedor, Date ultimaCompra) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.fornecedor = fornecedor;
        this.ultimaCompra = ultimaCompra;
    }

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    public String getUltimaCompraString(){
        return Util.dataParaString(getUltimaCompra());
    }

    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
