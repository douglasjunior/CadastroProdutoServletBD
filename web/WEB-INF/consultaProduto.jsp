<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Produto</title>
    </head>
    <body>
        <h1>Consulta Produto</h1>
        
        <button onclick="window.location='CadastrarProduto'" >Cadastrar</button>
        <!--
        <a href="CadastrarProduto">Cadastrar</a>
        -->
        <br /><br />
        
        <form method="GET" action="ConsultaProduto" >
            <label>Busca:</label>
            <input type="text" name="busca" value="" />
            <input type="submit" value="Buscar" />
        </form>
                
    </body>
</html>
