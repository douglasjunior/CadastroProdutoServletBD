package br.grupointegrado.cadastroProduto.control;

import br.grupointegrado.cadastroProduto.model.Produto;
import br.grupointegrado.cadastroProduto.model.ProdutoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroProdutoServlet extends HttpServlet {

    /**
     * Confira se sua tabela está igual a esta
     */
    private String createTable = "CREATE TABLE IF NOT EXISTS `produto` (\n"
            + "  `codigo` INT NOT NULL AUTO_INCREMENT,\n"
            + "  `descricao` VARCHAR(200) NOT NULL,\n"
            + "  `quantidade` DOUBLE NULL,\n"
            + "  `valor` DOUBLE NULL,\n"
            + "  `nome_fornecedor` VARCHAR(200) NULL,\n"
            + "  `ultima_compra` DATE NULL,\n"
            + "  PRIMARY KEY (`codigo`));";

    /**
     * Cria uma conexão JDBC
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private Connection abrirConexao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/produtos";
        return DriverManager.getConnection(url, "root", "root");
    }

    /**
     * Encerra uma conexão JDBC
     * @param conn 
     */
    private void fecharConexao(Connection conn) {
        try {
            conn.close();
        } catch (Exception ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = abrirConexao();

            /*
             Crie um método no DAO para facilitar a recuperação dos parâmetros
             */
            Produto produto = ProdutoDAO.getProdutoParametros(req);

            /*
             Se o código é ZERO, então devemos Inserir o Produto, 
             caso contrário é uma Alteração
             */
            if (produto.getCodigo() == 0) {
                incluirProduto(conn, produto);
            } else {
                alterarProduto(conn, produto);
            }
            
            /*
             Após executar a operação, redireciona para a página de consulta 
             */
            resp.sendRedirect("/CadastroProdutos/produto/consulta.jsp");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            /*
            Utilizando o FINALLY garantimos que a conexão sempre será fechada
            */
            fecharConexao(conn);
        }
    }

    private void alterarProduto(Connection conn, Produto produto) {
        // implementar a alteração utilizando o DAO
    }

    private void incluirProduto(Connection conn, Produto produto) throws SQLException {
        new ProdutoDAO(conn).inserir(produto);
    }
}
