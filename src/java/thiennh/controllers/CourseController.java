/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thiennh.config.Config;
import thiennh.course.CourseDAO;
import thiennh.course.CourseDTO;

/**
 *
 * @author Thien's
 */
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

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
            case "search":
                search(request, response);
                break;
            case "searchByCategory":
                searchByCategory(request, response);
                break;
        }
    }

    protected void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String search = request.getParameter("search");
            String page = request.getParameter("page");
            int intpage = 1 ;
            int totalpage = 0;
            int itemsPerPage=20;
            if (page != null) {
                intpage = Integer.parseInt(page);
            }
            List<CourseDTO> list = CourseDAO.search(search);
            List<CourseDTO> sublist = new LinkedList<>();
            List<Integer> pageList = new LinkedList<>();
            if (list.size() > 0) {
                totalpage = list.size() % itemsPerPage == 0 ? list.size() / itemsPerPage : (list.size() / itemsPerPage) + 1;
                for (int i = 0; i < totalpage; i++) {
                    pageList.add(i);
                }
                int n = (intpage - 1) * itemsPerPage;

                if (list.size() >= n + itemsPerPage) {
                    sublist = list.subList(n, n + itemsPerPage);
                } else {
                    sublist = list.subList(n, list.size());
                }
            }
            request.setAttribute("sublist", sublist);
            request.setAttribute("search", search);
            request.setAttribute("page", intpage);
            request.setAttribute("noOfPage", pageList);
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void searchByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String category = request.getParameter("category");
            String page = request.getParameter("page");
            int intpage = 0;
            int totalpage = 0;
            if (page != null) {
                intpage = Integer.parseInt(page);
            }else{
                intpage =1;
            }
            List<CourseDTO> list = CourseDAO.searchByCategory(category);
            List<CourseDTO> sublist = new LinkedList<>();
            List<Integer> pageList = new LinkedList<>();
            if (list.size() > 0) {
                totalpage = list.size() % 20 == 0 ? list.size() / 20 : (list.size() / 20) + 1;
                for (int i = 0; i < totalpage; i++) {
                    pageList.add(i);
                }
                System.out.println(totalpage);
                int n = (intpage - 1) * 20;
                System.out.println(n);
//                for (i = n + 1; i < n + 20; i++) {
//                    if(list.){
//                        sublist.add(list.get(i));
//                    }
//                }
                if (list.size() >= n + 20) {
                    sublist = list.subList(n, n + 20);
                } else {
                    sublist = list.subList(n, list.size());
                }
            }
            request.setAttribute("sublist", sublist);
            request.setAttribute("noOfPage", pageList);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "search");
            request.setAttribute("category", category);
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
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
