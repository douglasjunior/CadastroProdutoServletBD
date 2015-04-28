package br.grupointegrado.cadastroProduto.model;

import br.grupointegrado.cadastroProduto.util.ConversorUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ProdutoDAO {

    private final Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insere um novo Produto no Banco de Dados
     *
     * @param produto
     * @throws SQLException
     */
    public void inserir(Produto produto) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO produto (descricao, valor, quantidade, nome_fornecedor, ultima_compra) "
                + "VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, produto.getDescricao());
        ps.setDouble(2, produto.getValor());
        ps.setDouble(3, produto.getQuantidade());
        ps.setString(4, produto.getFornecedor());
        ps.setDate(5, ConversorUtil.dateParaSQLDate(produto.getUltimaCompra()));
        ps.execute();
        ps.close();
    }

    /**
     * Recupera os parâmetros recebidos via Request e cria um Objeto Produto
     *
     * @param req
     * @return
     */
    public static Produto getProdutoParametros(HttpServletRequest req) {
        String codigo = req.getParameter("codigo");
        String descricao = req.getParameter("descricao");
        String quantidade = req.getParameter("quantidade");
        String valor = req.getParameter("valor");
        String fornecedor = req.getParameter("fornecedor");
        String data = req.getParameter("data");
        return new Produto(ConversorUtil.stringParaInteger(codigo),
                descricao,
                ConversorUtil.stringParaDouble(quantidade),
                ConversorUtil.stringParaDouble(valor),
                fornecedor,
                ConversorUtil.stringParaDate(data));
    }

    /**
     * Consulta os produtos no banco de dados, por descrição, utilizando LIKE.
     *
     * @param descricaoParam
     * @return
     * @throws SQLException
     */
    public List<Produto> consultarPorDescricao(String descricaoParam) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        if (descricaoParam != null && !descricaoParam.isEmpty()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            ps.setString(1, "%" + descricaoParam + "%");
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Produto prod = montarProduto(resultado);
                produtos.add(prod);
            }
            ps.close();
        }
        return produtos;
    }

    /**
     * Consulta um produto no banco de dados utilizando o código.
     *
     * @param codigoParam
     * @return
     * @throws SQLException
     */
    public Produto consultarPorCodigo(String codigoParam) throws SQLException {
        Produto produto = null;
        if (codigoParam != null && !codigoParam.isEmpty()) {
            int codigo = ConversorUtil.stringParaInteger(codigoParam);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produto WHERE codigo = ?");
            ps.setInt(1, codigo);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                produto = montarProduto(resultado);
            }
            ps.close();
        }
        return produto;
    }

    /**
     * Monta o objeto Produto a partir do ResultSet.
     *
     * @param resultado
     * @return
     * @throws SQLException
     */
    private Produto montarProduto(ResultSet resultado) throws SQLException {
        return new Produto(resultado.getInt("codigo"),
                resultado.getString("descricao"),
                resultado.getDouble("quantidade"),
                resultado.getDouble("valor"),
                resultado.getString("nome_fornecedor"),
                resultado.getDate("ultima_compra"));
    }

    /**
     * Faz um Update nos dados do Produto
     *
     * @param produto
     * @throws java.sql.SQLException
     */
    public void alterar(Produto produto) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE produto SET descricao = ?, valor = ?, quantidade = ?, nome_fornecedor = ?, ultima_compra = ? "
                + " WHERE codigo = ?");
        ps.setString(1, produto.getDescricao());
        ps.setDouble(2, produto.getValor());
        ps.setDouble(3, produto.getQuantidade());
        ps.setString(4, produto.getFornecedor());
        ps.setDate(5, ConversorUtil.dateParaSQLDate(produto.getUltimaCompra()));
        // temos que passar também o código que será utilizado no WHERE
        ps.setInt(6, produto.getCodigo());
        ps.execute();
        ps.close();
    }

    /**
     * Excluir um produto do banco de dados a partir do códigoF
     *
     * @param codigoParam
     * @throws SQLException
     */
    public void excluir(String codigoParam) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM produto WHERE codigo = ?");
        ps.setInt(1, ConversorUtil.stringParaInteger(codigoParam));
        ps.execute();
        ps.close();
    }
}
