package br.grupointegrado.cadastroProduto.model;

import br.grupointegrado.cadastroProduto.util.ConversorUtil;
import java.util.Date;

public class Produto {

    private int codigo;
    private String descricao;
    private double quantidade;
    private double valor;
    private String fornecedor;
    private Date ultimaCompra;

    public Produto() {
        descricao = "";
        fornecedor = "";
    }

    public Produto(int codigo, String descricao, double quantidade, double valor, String fornecedor, Date ultimaCompra) {
        this.codigo = codigo;
        this.descricao = descricao;
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

    public String getUltimaCompraString() {
        return ConversorUtil.dateParaString(ultimaCompra);
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
