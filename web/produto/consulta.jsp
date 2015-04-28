<%@page import="java.util.List"%>
<%@page import="br.grupointegrado.cadastroProduto.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/includes/header.jsp" %>
        <title>Consulta de Produtos</title>
        <script type="text/javascript">
            function excluirProduto(codigo) {
                var result = confirm("Deseja excluir o produto " + codigo + " ?");
                if (result) {
                    window.location =
                            "/CadastroProdutos/ConsultaProdutoServlet?" +
                            "delete=true&codigo=" + codigo;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/menu.jsp" %>

        <br />

        <form method="GET" action="/CadastroProdutos/ConsultaProdutoServlet" >
            Descrição: <input type="text" name="descricao" value="" />
            <input type="submit" value="Consultar" /> <br /><br />
        </form>

        <% if (mensagemErro != null) {%>
        <br />
        <%-- aqui criamos uma "class" em CSS para utilizar em todas as nossas mensagens de erro --%>
        <p class="mensagemErro"><%=mensagemErro%></p> 
        <br />
        <% } %>

        <%
            if (produtos != null && !produtos.isEmpty()) {
        %>
        <table border="1" >
            <tr>
                <th>Codigo</th>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Valor</th>
                <th>Fornecedor</th>
                <th>Última Compra</th>
                <th></th>
            </tr>
            <% for (Produto produto : produtos) {%>
            <tr>
                <td><%=produto.getCodigo()%></td>
                <td><%=produto.getDescricao()%></td>
                <td><%=produto.getQuantidade()%></td>
                <td><%=produto.getValor()%></td>
                <td><%=produto.getFornecedor()%></td>
                <td><%=produto.getUltimaCompraString()%></td>
                <td><a href="/CadastroProdutos/CadastroProdutoServlet?codigo=<%=produto.getCodigo()%>" >Alterar</a>
                    <a href="#" onclick="excluirProduto(<%=produto.getCodigo()%>)" >Excluir</a></td>
            </tr>
            <% } %>
        </table>

        <% }%>

    </body>
</html>
