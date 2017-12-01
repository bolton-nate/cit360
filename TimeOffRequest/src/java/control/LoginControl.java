/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author natebolton
 */
public class LoginControl extends HttpServlet {

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
//        JSONObject json = new JSONObject();
//        ArrayList <String> parameterNames = new ArrayList<>();
//        Enumeration jsonNames = request.getParameterNames();
//           while (jsonNames.hasMoreElements()) {
//               String parameterName = (String) jsonNames.nextElement();
//               parameterNames.add(parameterName);
//           }
        String u=htmlFilter(request.getParameter("uname"));
        String p=htmlFilter(request.getParameter("pw"));
        System.out.println("USERLOG:  " + u + " " + p);
        
//        if (LoginControl.authenticateUser(parameterNames.get(0), parameterNames.get(1))) {
        PrintWriter out = response.getWriter();
        int result = LoginControl.authenticateUser(u, p);
        if ( result == 1) {
            //TODO:  Success... now what?
            out.println("Logged In!");
            //System.out.println("Logged In!");
        } else if (result == 0) {
            out.println("Error, wrong username or password.");
        } else {
            out.println("Error, something went wrong.");
        }
//        if (u.equals("admin") && p.equals("admin")) {
//            RequestDispatcher rd=request.getRequestDispatcher("/view/employeeMainMenu.jsp");  
//            rd.forward(request, response);  
//        } else {
//            try (PrintWriter out = response.getWriter()) {
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet GuessMyNumber</title>");            
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<div>Welcome to Time Off Requests</div>");
//                out.println("<p style=\"color:red;\">Wrong Username or Password.  Please Try Again.</p>");  
//                out.println("<form method=\"post\" action=\"/TimeOffRequest/logincontrol\">");
//                out.println("    <fieldset>");
//                out.println("        <legend>Time Off Request System Login</legend>");
//                out.println("        <div>");
//                out.println("            Username:<br>");
//                out.println("            <input type=\"text\" size=25 name=\"userid\">");
//                out.println("        </div>");
//                out.println("        <div>");
//                out.println("            Password:<br>");
//                out.println("            <input type=\"Password\" size=25 name=\"pwd\">");
//                out.println("        </div>");
//                out.println("        <input type=\"submit\" value=\"Login\" />");
//                out.println("    </fieldset>");
//                out.println("</form>");
//                out.println("</body>");
//                out.println("</html>");
//            }
//        }
    }
    
    public static int authenticateUser(String uname, String pword) {
        String pwordCheck;
        HibernateControl hc = new HibernateControl();
        pwordCheck = hc.getPassword(uname);
        //System.out.println("pwordCheck is: " + pwordCheck);
        if (pword == null) {
            return 2;
        }
        if (pword.equals(pwordCheck)) {
            return 1;
        }
        
        return 0;
    }
    
    private static String htmlFilter(String message) {
        if (message == null) return null;
        int len = message.length();
        StringBuffer result = new StringBuffer(len + 20);
        char aChar;

        for (int i = 0; i < len; ++i) {
            aChar = message.charAt(i);
            switch (aChar) {
                case '<': result.append("&lt;"); break;
                case '>': result.append("&gt;"); break;
                case '&': result.append("&amp;"); break;
                case '"': result.append("&quot;"); break;
                default: result.append(aChar);
            }
        }
        return (result.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
