<%@page import="br.grupointegrado.cadastroProduto.modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    Produto produto = (Produto) request.getAttribute("produto");
    // garante que o produto nunca será nulo para preencher o formulário
    if (produto == null)
        produto = new Produto(0, "", 0, 0, "", null);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <h1>Cadastro de Produtos</h1>
        <% if (mensagemErro != null) { %>
        <p><%= mensagemErro %></p>
        <% }%>
        <form method="POST" action="CadastrarProduto" >
            <input type="hidden" name="id" value="<%= produto.getId() %>" />
            <label>Descrição:</label>
            <input type="text" name="descricao" value="<%= produto.getDescricao()%>" />
            <br />
            <label>Quantidade:</label>
            <input type="text" name="quantidade" value="<%= produto.getQuantidade()%>" />
            <br />
            <label>Valor:</label>
            <input type="text" name="valor" value="<%= produto.getValor()%>" />
            <br />
            <label>Fornecedor:</label>
            <input type="text" name="fornecedor" value="<%= produto.getFornecedor()%>" />
            <br />
            <label>Última compra:</label>
            <input type="text" name="ultima_compra" value="<%= produto.getUltimaCompraString()%>" />
            <br /><br />
            <input type="submit" value="Salvar" />
        </form>
        <br />
        <a href="ConsultaProduto" >Voltar</a>
    </body>
</html>
