package br.grupointegrado.cadastroProduto.model;

import br.grupointegrado.cadastroProduto.util.ConversorUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

public class ProdutoDAO {

    private final Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insere um novo Produto no Banco de Dados
     * @param produto
     * @throws SQLException 
     */
    public void inserir(Produto produto) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO produto (descricao, valor, quantidade, nome_fornecedor, ultima_compra) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, produto.getDecricao());
        ps.setDouble(2, produto.getValor());
        ps.setDouble(3, produto.getQuantidade());
        ps.setString(4, produto.getFornecedor());
        ps.setDate(5, ConversorUtil.dateParaSQLDate(produto.getUltimaCompra()));
        ps.execute();
    }

    /**
     * Recupera os parâmetros recebidos via Request e cria um Objeto Produto
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
}
