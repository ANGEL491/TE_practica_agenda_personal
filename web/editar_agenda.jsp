
<%@page import="com.emergentes.modelo.agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    agenda item = (agenda) request.getAttribute("miAgenda");
    boolean nuevo = true;
    if (item.getId() > 0) {
        nuevo = false;
    }
%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de activadades</h1>
        <form action="Controlador" method="post">
            <table>
                <tr>
                    <td>Id</td>
                    <td><input type="text" name="id" value="<%=item.getId()%>"></td>
                </tr>
                <tr>
                    <td>Hora</td>
                    <td><input type="time" name="hora" value="<%=item.getHora()%>"></td>
                </tr>
                <tr>
                    <td>Actividad</td>
                    <td><input type="text" name="actividad" value="<%=item.getActividad()%>"></td>
                </tr>
                <tr>
                    <td>Cumplido</td>
                    <td><input type="text" name="id" value="<%=item.getCumplido()%>"></td>
                </tr>

                <tr>
                    <!--mandamos un parametro oculto para ver si el registro es nuevo o no-->
                <input type="hidden" name="nuevo" value="<%=nuevo%>">
                <td></td>
                <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
