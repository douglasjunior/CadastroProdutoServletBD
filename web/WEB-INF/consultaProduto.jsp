<%@page import="java.util.List"%>
<%@page import="br.grupointegrado.cadastroProduto.modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Produto</title>
    </head>
    <body>
        <h1>Consulta Produto</h1>
        <% if (mensagemErro != null) {%>
        <p><%= mensagemErro%></p>
        <% }%>
        <button onclick="window.location = 'CadastrarProduto'" >Cadastrar</button>
        <!--
        <a href="CadastrarProduto">Cadastrar</a>
        -->
        <br /><br />

        <form method="GET" action="ConsultaProduto" >
            <label>Busca:</label>
            <input type="text" name="busca" value="" />
            <input type="submit" value="Buscar" />
        </form>

        <% if (produtos != null) { %>
        <!-- aqui vai a tabela HTML de produtos -->
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Valor</th>
                <th>Fornecedor</th>
                <th>Última compra</th>
                <th>Opções</th>
            </tr>
            <% for (Produto p : produtos) {%>
            <tr>
                <td><%= p.getId()%></td>
                <td><%= p.getDescricao()%></td>
                <td><%= p.getQuantidade()%></td>
                <td><%= p.getValor()%></td>
                <td><%= p.getFornecedor()%></td>
                <td><%= p.getUltimaCompraString()%></td>
                <td>
                    <a href="ConsultaProduto?acao=excluir&id=<%= p.getId()%>" >Excluir</a> |
                    <a href="CadastrarProduto?id=<%= p.getId()%>" >Alterar</a>
                </td>
            </tr>
            <% } %>
        </table>
        <% }%>
    </body>
</html>
