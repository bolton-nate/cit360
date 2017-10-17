/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author natebolton
 */
public class GuessMyNumber extends HttpServlet {

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
        HttpSession session = request.getSession();
        Integer guesses;
        Integer myNumber;
        synchronized(session) { //initialize session variables if needed
            guesses = (Integer)session.getAttribute("guesses");
            if (guesses == null) {
                guesses = 0;
                session.setAttribute("guesses", guesses);
            }

            myNumber = (Integer)session.getAttribute("myNumber");
            if (myNumber == null) {
                Random r = new Random();
                myNumber = r.nextInt(101);
                session.setAttribute("myNumber", myNumber);
            }
        }
        String s = request.getParameter("theGuess");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GuessMyNumber</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Guessing Game</h1>");
            if (s == null || (s = htmlFilter(s.trim())).length() == 0) {
                out.println("<p>You must enter a valid number</p>");
                //out.println("is null");
                out.println("<h2>Guess again! Pick a number between 0 and 100.</h2>");
                out.println("<form method=\"post\" action=\"/Servlet360/guess\">");
                out.println("    <fieldset>");
                out.println("        <legend>Guess the Number</legend>");
                out.println("        Number: <input type=\"number\" name=\"theGuess\" /><br /><br />");
                out.println("        <input type=\"submit\" value=\"Guess\" />");
                out.println("    </fieldset>");
                out.println("</form>");
            } else {
                //out.println("is not null");
                int theGuess = Integer.parseInt(s);
                if (theGuess < 0 || theGuess > 100) { //check if guess is within bounds
                    out.println("<p>Your guess must be between 0 and 100 inclusive.</p>");
                    out.println("<h2>Guess again! Pick a number between 0 and 100.</h2>");
                    out.println("<form method=\"post\" action=\"/Servlet360/guess\">");
                    out.println("    <fieldset>");
                    out.println("        <legend>Guess the Number</legend>");
                    out.println("        Number: <input type=\"number\" name=\"theGuess\" /><br /><br />");
                    out.println("        <input type=\"submit\" value=\"Guess\" />");
                    out.println("    </fieldset>");
                    out.println("</form>");
                } else if (theGuess != myNumber) {
                    synchronized(session) {
                        guesses = (Integer)session.getAttribute("guesses");
                        if (guesses == null) {
                            guesses = 0;
                        } else {
                            guesses += 1;
                        }
                        session.setAttribute("guesses", guesses);
                    }
                    if (theGuess > myNumber) {
                        out.println("<p>Your Guess is wrong.  Your guess is too high</p>");
                        //out.println("<p>My number was " + myNumber + "</p>");
                        out.println("<h2>Guess again! Pick a number between 0 and 100.</h2>");
                        out.println("<form method=\"post\" action=\"/Servlet360/guess\">");
                        out.println("    <fieldset>");
                        out.println("        <legend>Guess the Number</legend>");
                        out.println("        Number: <input type=\"number\" name=\"theGuess\" /><br /><br />");
                        out.println("        <input type=\"submit\" value=\"Guess\" />");
                        out.println("    </fieldset>");
                        out.println("</form>");
                    } else if (theGuess < myNumber) {
                        out.println("<p>Your Guess is wrong.  Your guess is too low</p>");
                        //out.println("<p>My number was " + myNumber + "</p>");
                        out.println("<h2>Guess again! Pick a number between 0 and 100.</h2>");
                        out.println("<form method=\"post\" action=\"/Servlet360/guess\">");
                        out.println("    <fieldset>");
                        out.println("        <legend>Guess the Number</legend>");
                        out.println("        Number: <input type=\"number\" name=\"theGuess\" /><br /><br />");
                        out.println("        <input type=\"submit\" value=\"Guess\" />");
                        out.println("    </fieldset>");
                        out.println("</form>");
                    }
                }
                
                if (theGuess == myNumber) {
                    out.println("<p>You got it!  My number was " + myNumber + "</p>");
                    out.println("<p>It took you " + guesses + " guesses.</p>");
                    session.invalidate();
                    out.println("<p><a href=\"/Servlet360/\">Play Again?</a></p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
   // Filter the string for special HTML characters to prevent
   // command injection attack
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
