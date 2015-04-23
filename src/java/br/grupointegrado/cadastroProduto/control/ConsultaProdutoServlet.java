package br.grupointegrado.cadastroProduto.control;

import br.grupointegrado.cadastroProduto.model.Produto;
import br.grupointegrado.cadastroProduto.model.ProdutoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultaProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = (Connection) req.getAttribute("conexao");
            String descricaoParam = req.getParameter("descricao");
            List<Produto> produtos = new ProdutoDAO(conn).consultarPorDescricao(descricaoParam);
            req.setAttribute("produtos", produtos);
        } catch (Exception ex) {
            ex.printStackTrace();
            String mensagemErro = "Não foi possível realizar a consulta de produtos, tente novamente.";
            req.setAttribute("mensagem_erro", mensagemErro);
        }
        String mensagemErro = "Não foi possível realizar a consulta de produtos, tente novamente.";
        req.setAttribute("mensagem_erro", mensagemErro);
        req.getRequestDispatcher("/produto/consulta.jsp").forward(req, resp);
    }

}
