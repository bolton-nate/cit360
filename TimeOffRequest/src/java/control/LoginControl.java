/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        
        response.setContentType("text/html;charset=UTF-8");//application/json
        //System.out.println("USERLOG:  " + request.getReader().readLine());
        
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while( (str = br.readLine()) != null ){
            sb.append(str);
        }
        str = URLDecoder.decode(sb.toString(), "UTF-8");
        
        System.out.println("USERLOG:  " + str);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) parser.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("USERLOG:  " + jsonObj.toString());
//        System.out.println("USERLOG:  " + jsonObj.get("username"));
        
        String u = (String) jsonObj.get("username");
        String p = (String) jsonObj.get("password");
        PrintWriter out = response.getWriter();
        int result = LoginControl.authenticateUser(u, p);
        jsonObj = new JSONObject();
        if ( result == 1) {
            //TODO:  Success... now what?
            HibernateControl hc = new HibernateControl();
            Employees employee = hc.getEmployeeByUsername(u);
            System.out.println("EMPLOYEE FIRST NAME: " + employee.getFirstName());
            response.setContentType("application/json");
            jsonObj.put("response", "Logged In!");
            jsonObj.put("emp_id", employee.getEmpId());
            jsonObj.put("employeeTitle", employee.getEmployeeType().getTypeTitle());
            jsonObj.put("firstname", employee.getFirstName());
            jsonObj.put("lastname", employee.getLastName());
            System.out.println(jsonObj);
            //out.println("Logged In!");
            //System.out.println("Logged In!");
        } else if (result == 0) {
            jsonObj.put("response", "Error, wrong username or password.");
            //out.println("Error, wrong username or password.");
        } else {
            jsonObj.put("response", "Error, something went wrong.");
            //out.println("Error, something went wrong.");
            
        }
        out.println(jsonObj);
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
