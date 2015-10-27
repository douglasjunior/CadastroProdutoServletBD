package br.grupointegrado.cadastroProduto.modelo;

import br.grupointegrado.cadastroProduto.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * @param id
     */
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ? ";
        
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        
        ps.close();
    }

    /**
     * Faz o SELECT no banco e retorna uma lista de Objetos Produto.
     *
     * @param busca
     * @return
     */
    public List<Produto> buscarPorDescricao(String busca) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        if (busca == null || busca.isEmpty()) {
            return produtos;
        }

        String sql = "SELECT * FROM produto WHERE descricao LIKE ? ";

        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, "%" + busca + "%");
        
        // executa o SELECT no banco e retorna o resultado
        ResultSet resultado = ps.executeQuery();
        while(resultado.next()){
            Produto produto = new Produto();
            
            produto.setId(resultado.getInt("id"));
            produto.setDescricao(resultado.getString("descricao"));
            produto.setValor(resultado.getDouble("valor"));
            produto.setQuantidade(resultado.getDouble("quantidade"));
            produto.setFornecedor(resultado.getString("fornecedor"));
            produto.setUltimaCompra(resultado.getDate("ultima_compra"));
            
            produtos.add(produto);
        }
        resultado.close();
        
        return produtos;
    }
}
