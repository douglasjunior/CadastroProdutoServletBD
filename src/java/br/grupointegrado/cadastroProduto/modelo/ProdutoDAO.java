package br.grupointegrado.cadastroProduto.modelo;

import br.grupointegrado.cadastroProduto.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por realizar a comunicação com os Produtos do banco de
 * dados
 *
 * @author Douglas
 */
public class ProdutoDAO {

    // conexão com o banco de dados criada no ConexaoFiltro
    private Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /**
     * Insere o Produto no banco de dados
     *
     * @param produto
     */
    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto "
                + " (descricao, quantidade, valor, fornecedor, ultima_compra) "
                + " VALUES (?, ?, ?, ?, ?) ";
 
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, produto.getDescricao());
        st.setDouble(2, produto.getQuantidade());
        st.setDouble(3, produto.getValor());
        st.setString(4, produto.getFornecedor());
        st.setDate(5, Util.dateParaSQL(produto.getUltimaCompra()));
        
        st.execute();
        st.close();
    }

    /**
     * Altera os dados do Produto no banco de dados
     *
     * @param produto
     */
    public void alterar(Produto produto) {
    }

    /**
     * Exclui o produto do banco de dados
     *
     * @param produto
     */
    public void excluir(Produto produto) {
    }
}
