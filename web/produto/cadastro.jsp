<%@page import="br.grupointegrado.cadastroProduto.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    Produto produto = (Produto) request.getAttribute("produto");
    /*
     Se o produto vier NULL significa que estamos fazendo uma INCLUSÃO.
     Porém, se ele estiver NULL teremos problema ao preencher o formulário, ocorrerá NullPointer.
     Por isso é uma boa opção criar um produto vazio.
     */
    if (produto == null) {
        produto = new Produto();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/includes/header.jsp" %>
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/menu.jsp" %>
        
        <br />

        <% if (mensagemErro != null) {%>
        <%-- aqui criamos uma "class" em CSS para utilizar em todas as nossas mensagens de erro --%>
        <p class="mensagemErro"><%=mensagemErro%></p> 
        <br />
        <% }%>

        <form method="POST" action="/CadastroProdutos/CadastroProdutoServlet" >
            <%-- Vamos manter o código como campo oculto, e usar um <span> para exibí-lo na tela :) --%>
            <input type="text" name="codigo" value="<%=produto.getCodigo()%>" hidden="hidden" />
            Código: <span><%=produto.getCodigo()%></span> <br />
            Descrição: <input type="text" name="descricao" value="<%=produto.getDescricao()%>" /> <br />
            Valor: <input type="text" name="valor" value="<%=produto.getValor()%>" /> <br />
            Quantidade: <input type="text" name="quantidade" value="<%=produto.getQuantidade()%>" /> <br />
            Nome Fornecedor: <input type="text" name="fornecedor" value="<%=produto.getFornecedor()%>" /> <br />
            Dt Última Compra: <input type="text" name="data" value="<%=produto.getUltimaCompraString()%>" /> <br /><br />
            <input type="submit" value="Salvar" />
        </form>
    </body>
</html>
