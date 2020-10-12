package com.emergentes.controlador;

import com.emergentes.modelo.agenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int op = Integer.parseInt(request.getParameter("op"));
        int id, pos;
        HttpSession ses = request.getSession();
        ArrayList<agenda> lista = (ArrayList<agenda>) ses.getAttribute("listagenda");

        /*nuevo*/
        if (op == 1) {
            agenda a = new agenda();
            request.setAttribute("miAgenda", a);
            request.getRequestDispatcher("editar_agenda.jsp").forward(request, response);

        }
        /*editar*/
        if (op == 2) {
            id = Integer.parseInt(request.getParameter("id"));
            pos = buscarIndice(request, id);
            agenda a1 = lista.get(pos);
            request.setAttribute("miAgenda", a1);
            request.getRequestDispatcher("editar_agenda.jsp").forward(request, response);
        }
        /*eliminar*/
        if (op == 3) {
            id = Integer.parseInt(request.getParameter("id"));
            pos = buscarIndice(request, id);
            lista.remove(pos);
            ses.setAttribute("listagenda", lista);
            response.sendRedirect("index.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String hora = request.getParameter("hora");
        String actividad = request.getParameter("actividad");
        String cumplido = request.getParameter("cumplido");
        String nuevo = request.getParameter("nuevo");

        int pos=0;
        agenda agen = new agenda();
        agen.setId(id);
        agen.setHora(hora);
        agen.setActividad(actividad);
        agen.setCumplido(cumplido);

        /*obtenemos la sesion y la guardamos en la variable ses*/
        HttpSession ses = request.getSession();

        /*recuperamos en una lista*/
        ArrayList<agenda> lista = (ArrayList<agenda>) ses.getAttribute("listagenda");
        if (nuevo.equals("true")) {
            //nuevo
            lista.add(agen);
        } else {
            //editar
            //buscar
            pos=buscarIndice(request, id);
            //reemplazar
            lista.set(pos, agen);
        }
        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int buscarIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<agenda> lista = (ArrayList<agenda>) ses.getAttribute("listagenda");
        int i = 0;
        if (lista.size() > 0) {
            while (i < lista.size()) {
                if (lista.get(i).getId() == id) {
                    break;
                } else {
                    i++;
                }
            }
        }
        return i;
    }
}
