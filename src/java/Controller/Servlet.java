/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Angge
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

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
        
        request.setCharacterEncoding("UTF-8");
        
        //View > Controller.
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String cedula = request.getParameter("cedula");
        double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        
        //Controller > Model
        Empleado empleado = new Empleado(nombre , email, cedula, sueldo);
            
        //Model
        //METODOS
        empleado.calcularAFP();
        empleado.calcularSFS();
        empleado.calcularIRS();
        empleado.calcularSueldoF();
        empleado.enviarDatos();
        
        //Model > Controller
        empleado.llamarEmpleadoActual();
        request.setAttribute("nombre", empleado.getNombre());
        request.setAttribute("sueldoBase", empleado.getSueldo());
        request.setAttribute("afp", empleado.getAfp());
        request.setAttribute("sfs", empleado.getSfs());
        request.setAttribute("irs", empleado.getIrs());
        request.setAttribute("sueldoF", empleado.getSueldoF());
       
        //Controller > View
        RequestDispatcher rd = request.getRequestDispatcher("JspEmpleado.jsp");
        rd.forward(request, response);
        
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

}
