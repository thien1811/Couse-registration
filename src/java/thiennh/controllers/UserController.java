/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thiennh.config.Config;
import thiennh.registration.RegistrationDAO;
import thiennh.registration.RegistrationDTO;
import thiennh.registration.RegistrationError;

/**
 *
 * @author Thien's
 */
public class UserController extends HttpServlet {

    HttpSession session = null;

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
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "login_handler":
                login_handler(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "register_handler":
                register_handler(request, response);
                break;
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Cookie[] cookies = null;
            cookies = request.getCookies();
            session = request.getSession();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (!cookie.getName().equals("JSESSIONID")) {
                        if (!RegistrationDAO.checkLogin(cookie.getName(), cookie.getValue())) {
                            request.setAttribute("message", "UserName or Password are incorrect!");
                            request.setAttribute("action", "login");
                        } else if (RegistrationDAO.checkLogin(cookie.getName(), cookie.getValue())) {
                            request.setAttribute("controller", "course");
                            request.setAttribute("action", "search");
                            session.setAttribute("username", cookie.getName());
                            session.setAttribute("isAdmin", RegistrationDAO.searchById(cookie.getName()).isIsAdmin());
                            request.setAttribute("controller", "course");
                            request.setAttribute("action", "search");
                        }
                    }
                }
            }
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void login_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean remember = request.getParameter("remember") != null;
            boolean result = false;
            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();
            session = request.getSession();
            result = RegistrationDAO.checkLogin(username, password);
            if (result) {
                if (remember) {
                    cookie = new Cookie(username, password);
                    cookie.setMaxAge(60 * 10);
                    response.addCookie(cookie);
                }
                request.setAttribute("controller", "course");
                request.setAttribute("action", "search");
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", RegistrationDAO.searchById(username).isIsAdmin());
            } else {
                request.setAttribute("username", username);
                request.setAttribute("action", "login");
                request.setAttribute("message", "UserName or Password are incorrect!");
            }
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Destroy session
        HttpSession session = request.getSession();
        session.invalidate();

        //Clear cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }

        //Redirect to logout.jsp
        request.setAttribute("controller", "course");
        request.setAttribute("action", "search");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void register_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String lastname = request.getParameter("name");
        RegistrationDTO r = new RegistrationDTO(username, password, lastname, false);
        RegistrationError err = new RegistrationError();
        try {

            if (username.length() < 6 || username.length() > 20) {
                err.setUsernameLength("Your UserName must be from 6 to 20 characters.");
            } else {
                request.setAttribute("username", username);

            }
            if (password.length() < 6 || password.length() > 30) {
                err.setPasswordLength("Your Password must be from 6 to 30 characters.");
            }
            if (!rePassword.equals(password)) {
                err.setConfirmNotMatch("Wrong Password!");
            }
            if (lastname.length() < 2 || lastname.length() > 50) {
                err.setFullNameLength("Your name must be from 2 to 50 characters");
            } else {
                request.setAttribute("name", lastname);

            }

            if (username.length() >= 6 && username.length() <= 20
                    && password.length() >= 6 && password.length() <= 30
                    && rePassword.equals(password)
                    && lastname.length() >= 2 && lastname.length() <= 50) {
                RegistrationDAO.create(r);
                request.setAttribute("action", "login");
                request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            err.setUsernameIsExisted("This UserName is already existed!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("err", err);
            request.setAttribute("controller", "user");
            request.setAttribute("action", "register");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
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
