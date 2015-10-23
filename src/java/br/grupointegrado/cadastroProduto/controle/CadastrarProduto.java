package br.grupointegrado.cadastroProduto.controle;

import br.grupointegrado.cadastroProduto.modelo.Produto;
import br.grupointegrado.cadastroProduto.modelo.ProdutoDAO;
import br.grupointegrado.cadastroProduto.util.Util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Util.stringParaInt(req.getParameter("id"));
        String descricao = req.getParameter("descricao");
        double quantidade = Util.stringParaDouble(req.getParameter("quantidade"));
        double valor = Util.stringParaDouble(req.getParameter("valor"));
        String forncedor = req.getParameter("fornecedor");
        Date ultimaCompra = Util.stringParaData(req.getParameter("ultima_compra"));
        // cria uma instancia do produto com os parâmetros do formulário
        Produto produto = 
                new Produto(id, descricao, quantidade, valor, forncedor, ultimaCompra);
        // recupera a conexão criada pelo filtro
        Connection conexao = (Connection) req.getAttribute("conexao");
        // instancia o DAO passando a conexao aberta
        ProdutoDAO dao = new ProdutoDAO(conexao);
        try {
            dao.inserir(produto);
            // se a inserção ocorrer com sucesso, encaminha 
            // para a página de consulta
            resp.sendRedirect("ConsultaProduto");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // se ocorrer algum erro, mantem o usuário na página de
            // cadastro e mostra mensagem de erro
            String mensagemErro = "Não foi possível inserir o produto no banco de dados.";
            req.setAttribute("mensagem_erro", mensagemErro);
            req.setAttribute("produto", produto);
            req.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp").forward(req, resp);
        }
    }
        
}
