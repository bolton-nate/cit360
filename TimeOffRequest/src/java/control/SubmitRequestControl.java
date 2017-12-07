/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.*;
import com.google.gson.Gson;
import java.io.BufferedReader;
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
public class SubmitRequestControl extends HttpServlet {

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
        //PROCESS REQUEST
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while( (str = br.readLine()) != null ){
            sb.append(str);
        }
        str = URLDecoder.decode(sb.toString(), "UTF-8");
        
        //System.out.println("SUBMITREQUEST CONTROL:  " + str);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) parser.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //PUT DATA IN DB
        Gson gson = new Gson();
        Requests req = null;
        try {
            req = gson.fromJson(jsonObj.toJSONString(), Requests.class);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        HibernateControl hc = new HibernateControl();
        boolean success = false;
        if (req != null) {
            success = hc.insertRequest(req);
        } else {
            System.out.println("ERROR INSERTING REQUEST!");
        }
        
        //SEND BACK RESPONSE
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            jsonObj = new JSONObject();
            if (success) {
                jsonObj.put("response", "Request Submitted Successfully");
            } else {
                jsonObj.put("response", "Error Submitting Request, Please Try Again");
            }
            System.out.println("Sending Response" + jsonObj.toJSONString());
            out.println(jsonObj);
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
