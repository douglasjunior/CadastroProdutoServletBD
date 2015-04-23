package br.grupointegrado.cadastroProduto.control;

import br.grupointegrado.cadastroProduto.model.Produto;
import br.grupointegrado.cadastroProduto.model.ProdutoDAO;
import java.io.IOException;
import java.sql.Connection;
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
             Após executar a operação, redireciona para a página de consulta.
             Aqui pode ser utilizado SendRedirect, pois não é necessário enviar nenhum atributo para a página.
             */
            resp.sendRedirect("/CadastroProdutos/produto/consulta.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            /*
             Se ocorrer algum erro ao tentar Salvar o produto, seja na Inclusão ou na Alteração, é enviada uma mensagem de erro e o produto de volta para a página.
             O produto precisa ser enviado de volta, pois o formulário precisa ser preenchido novamente com os dados que o usuário acabou de informar.
             */
            String mensagemErro = "Não foi possível salvar este produto, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
            req.setAttribute("produto", produto);
            req.getRequestDispatcher("/produto/cadastro.jsp").forward(req, resp);
        }
    }

    private void alterarProduto(Connection conn, Produto produto) throws SQLException {
        new ProdutoDAO(conn).alterar(produto);
    }

    private void incluirProduto(Connection conn, Produto produto) throws SQLException {
        new ProdutoDAO(conn).inserir(produto);
    }

    /**
     * doGet é chamado quando o usuário clica em "Alterar" na página de Consulta
     * de Produtos
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = (Connection) req.getAttribute("conexao");
            /*
             Recupera o código passado por parâmetro e busca o produto a ser alterado no banco de dados
             */
            String codigoParam = req.getParameter("codigo");
            Produto produto = new ProdutoDAO(conn).consultarPorCodigo(codigoParam);
            /*
             Envia o produto a ser alterado para a página JSP
             */
            req.setAttribute("produto", produto);
        } catch (Exception ex) {
            ex.printStackTrace();
            String mensagemErro = "Não foi possível alterar este produto, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
        }
        /*
         Encaminha a requisição e o produto para a página de cadastro.jsp
         */
        req.getRequestDispatcher("/produto/cadastro.jsp").forward(req, resp);
    }

}
