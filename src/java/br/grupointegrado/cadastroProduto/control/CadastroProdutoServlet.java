package br.grupointegrado.cadastroProduto.control;

import br.grupointegrado.cadastroProduto.model.Produto;
import br.grupointegrado.cadastroProduto.model.ProdutoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         Crie um método no DAO para facilitar a recuperação dos parâmetros
         */
        Produto produto = ProdutoDAO.getProdutoParametros(req);
        try {
            Connection conn = (Connection) req.getAttribute("conexao");

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
            ex.printStackTrace();
            String mensagemErro = "Não foi possível salvar este produto, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
            req.setAttribute("produto", produto);
            req.getRequestDispatcher("/produto/cadastro.jsp")
                    .forward(req, resp);
        }
    }

    private void alterarProduto(Connection conn, Produto produto) {
        // implementar a alteração utilizando o DAO
    }

    private void incluirProduto(Connection conn, Produto produto) throws SQLException {
        new ProdutoDAO(conn).inserir(produto);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = (Connection) req.getAttribute("conexao");
            String codigoParam = req.getParameter("codigo");
            Produto produto = new ProdutoDAO(conn).consultarPorCodigo(codigoParam);
            req.setAttribute("produto", produto);
        } catch (Exception ex) {
            ex.printStackTrace();
            String mensagemErro = "Não foi possível alterar este produto, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
        }
        req.getRequestDispatcher("/produto/cadastro.jsp").forward(req, resp);
    }

}
