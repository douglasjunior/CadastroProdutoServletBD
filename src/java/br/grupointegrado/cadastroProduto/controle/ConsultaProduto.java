package br.grupointegrado.cadastroProduto.controle;

import br.grupointegrado.cadastroProduto.modelo.Produto;
import br.grupointegrado.cadastroProduto.modelo.ProdutoDAO;
import br.grupointegrado.cadastroProduto.util.Util;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultaProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conexao = (Connection) req.getAttribute("conexao");
            ProdutoDAO dao = new ProdutoDAO(conexao);
            
            String acao = req.getParameter("acao");
            if ("excluir".equals(acao)) {
                int id = Util.stringParaInt(req.getParameter("id"));
                dao.excluir(id);
            }
            
            String busca = req.getParameter("busca");
            List<Produto> produtos = dao.buscarPorDescricao(busca);

            req.setAttribute("produtos", produtos);
        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem_erro", "Não foi possível consultar os produtos.");
        }
        req.getRequestDispatcher("/WEB-INF/consultaProduto.jsp").forward(req, resp);
    }

}
