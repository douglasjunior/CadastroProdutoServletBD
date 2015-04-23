package br.grupointegrado.cadastroProduto.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ConexaoFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Connection conn = null;
        try {
            conn = abrirConexao();
            request.setAttribute("conexao", conn);
            chain.doFilter(request, response);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * Cria uma conexão JDBC
     *
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
     *
     * @param conn
     */
    private void fecharConexao(Connection conn) {
        try {
            conn.close();
        } catch (Exception ex) {
        }
    }
    
    @Override
    public void destroy() {
    }
}
