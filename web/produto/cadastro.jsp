<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/menu.jsp" %>
        
        <br />
        
        <form method="POST" action="/CadastroProdutos/CadastroProdutoServlet" >
            <%-- Vamos manter o código como campo oculto, e usar um <span> para exibí-lo na tela :) --%>
            <input type="text" name="codigo" value="" hidden="hidden" />
            Código: <span>0</span> <br />
            Descrição: <input type="text" name="descricao" value="" /> <br />
            Valor: <input type="text" name="valor" value="" /> <br />
            Quantidade: <input type="text" name="quantidade" value="" /> <br />
            Nome Fornecedor: <input type="text" name="fornecedor" value="" /> <br />
            Dt Última Compra: <input type="text" name="data" value="" /> <br /><br />
            <input type="submit" value="Salvar" />
        </form>
    </body>
</html>
