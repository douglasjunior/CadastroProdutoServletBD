<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <h1>Cadastro de Produtos</h1>
        
        <form method="POST" action="CadastrarProduto" >
            <input type="hidden" name="id" value="" />
            <label>Descrição:</label>
            <input type="text" name="descricao" value="" />
            <br />
            <label>Quantidade:</label>
            <input type="text" name="quantidade" value="" />
            <br />
            <label>Valor:</label>
            <input type="text" name="valor" value="" />
            <br />
            <label>Fornecedor:</label>
            <input type="text" name="fornecedor" value="" />
            <br />
            <label>Última compra:</label>
            <input type="text" name="ultima_compra" value="" />
            <br /><br />
            <input type="submit" value="Salvar" />
        </form>
        <br />
        <a href="ConsultaProduto" >Voltar</a>
    </body>
</html>
