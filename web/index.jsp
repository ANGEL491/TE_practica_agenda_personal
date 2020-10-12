
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.agenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("listagenda") == null) {
        ArrayList<agenda> listaux = new ArrayList<agenda>();
        session.setAttribute("listagenda", listaux);
    }
    /*recuperamos los objetos de listagenda*/
    ArrayList<agenda> lista = (ArrayList<agenda>) session.getAttribute("listagenda");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agenda Personal</h1>
        <table border="3" cellspacing="5">
            <tr><td><a href="Controlador?op=1">Nuevo</a></td></tr>
            <tr>
                <th>ID</th>
                <th>Hora</th>
                <th>Actividad</th>
                <th>Cumplido</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if (lista != null) {
                    for (agenda item : lista) {


            %>
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getHora()%></td>
                <td><%=item.getActividad()%></td>
                <th><%=item.getCumplido()%></td>
                <td>
                    <a href="Controlador?op=2&id=<%=item.getId()%>">Editar</a>
                </td>
                <td>
                    <a href="Controlador?op=3&id=<%=item.getId()%>"
                       onclick="return confirm('ESTAS SEGURO!!')">Eliminar</a>
                </td>
            </tr>   
            <%                    }
                }
            %>
        </table>
    </body>
</html>
