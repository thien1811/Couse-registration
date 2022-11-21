/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import thiennh.config.Config;
import thiennh.course.CourseDAO;
import thiennh.course.CourseDTO;

/**
 *
 * @author Thien's
 */
public class AdminController extends HttpServlet {

    private List<String> categoryList = new LinkedList<>();
    private List<String> statusList = new LinkedList<>();

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        categoryList.add("Piano");
        categoryList.add("Guitar");
        categoryList.add("Drawing");
        statusList.add("active");
        statusList.add("in-active");
    }

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
        System.out.println(action);
        if (controller == null) {
            controller = request.getParameter("controller");
        }
        if (action == null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "search":
                search(request, response);
                break;
            case "searchByCategory":
                searchByCategory(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "create_handler":
                create_handler(request, response);
                break;
        }
    }

    protected void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String search = request.getParameter("search");
            String page = request.getParameter("page");
            int intpage = 0;
            int totalpage = 0;
            if (page != null) {
                intpage = Integer.parseInt(page);
            }
            List<CourseDTO> list = CourseDAO.searchForAd(search);
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

                if (list.size() >= n + 20) {
                    sublist = list.subList(n, n + 20);
                } else {
                    sublist = list.subList(n, list.size());
                }
            }
            request.setAttribute("sublist", sublist);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("statusList", statusList);
            request.setAttribute("search", search);
            request.setAttribute("page", page);
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
            } else {
                intpage = 1;
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
            request.setAttribute("controller", "admin");
            request.setAttribute("action", "search");
            request.setAttribute("category", category);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("statusList", statusList);
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
        if (!isMultipartContent) {
            return;
        }
        try {
            int cId = 0;
            String name = null;
            String description = null;
            Double tuition = 0.0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            String category = null;
            String status = null;
            int quantity = 0;
            String search = null;
            String page = null;

            String image = "";

            response.setContentType("text/html");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List< FileItem> fields = upload.parseRequest(request);
            Iterator< FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return;
            }

            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    if (cId == 0) {
                        if (fileItem.getFieldName().equals("cId")) {
                            cId = Integer.parseInt(fileItem.getString());
                        }
                    }
                    if (name == null) {
                        if (fileItem.getFieldName().equals("name")) {
                            name = fileItem.getString();
                        }
                    }
                    if (description == null) {
                        if (fileItem.getFieldName().equals("description")) {
                            description = fileItem.getString();
                        }
                    }
                    if (tuition == 0.0) {
                        if (fileItem.getFieldName().equals("tuition")) {
                            tuition = Double.parseDouble(fileItem.getString());
                        }
                    }
                    if (startDate == null) {
                        if (fileItem.getFieldName().equals("startDate")) {
                            startDate = sdf.parse(fileItem.getString());
                        }
                    }
                    if (endDate == null) {
                        if (fileItem.getFieldName().equals("endDate")) {
                            endDate = sdf.parse(fileItem.getString());
                        }
                    }
                    if (category == null) {
                        if (fileItem.getFieldName().equals("category")) {
                            category = fileItem.getString();
                        }
                    }
                    if (status == null) {
                        if (fileItem.getFieldName().equals("status")) {
                            status = fileItem.getString();
                        }
                    }
                    if (quantity == 0) {
                        if (fileItem.getFieldName().equals("quantity")) {
                            quantity = Integer.parseInt(fileItem.getString());
                        }
                    }
                    if (page == null) {
                        if (fileItem.getFieldName().equals("page")) {
                            page = fileItem.getString();
                        }
                    }
                    if (search == null) {
                        if (fileItem.getFieldName().equals("search")) {
                            search = fileItem.getString();
                        }
                    }
                } else {
                    if (fileItem.getSize() > 0) {
                        image = fileItem.getName();
                        fileItem.write(new File("D:\\JavaWeb\\SE162041_Assignment_SU3W\\web\\images\\" + image));
                    } else {
                        image = CourseDAO.searchById(cId).getImage();
                    }
                }
            }
            CourseDTO c = new CourseDTO(cId, name, image, description, tuition, startDate, endDate, category, status, quantity);
            if (!CourseDAO.update(c)) {
                request.setAttribute("action", "search");
                request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
            }
            int intpage = 0;
            int totalpage = 0;
            if (page != null) {
                intpage = Integer.parseInt(page);
            }
            List<CourseDTO> list = CourseDAO.searchForAd(search);
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

                if (list.size() >= n + 20) {
                    sublist = list.subList(n, n + 20);
                } else {
                    sublist = list.subList(n, list.size());
                }
            }
            request.setAttribute("sublist", sublist);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("statusList", statusList);
            request.setAttribute("search", search);
            request.setAttribute("action", "search");
            request.setAttribute("page", page);
            request.setAttribute("noOfPage", pageList);
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("statusList", statusList);
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void create_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
        if (!isMultipartContent) {
            return;
        }
        try {
            String name = null;
            String description = null;
            Double tuition = 0.0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            String category = null;
            String status = null;
            int quantity = 0;

            String file_name = null;
            String image = "";

            response.setContentType("text/html");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List< FileItem> fields = upload.parseRequest(request);
            Iterator< FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return;
            }

            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    if (name == null) {
                        if (fileItem.getFieldName().equals("name")) {
                            name = fileItem.getString();
                        }
                    }
                    if (description == null) {
                        if (fileItem.getFieldName().equals("description")) {
                            description = fileItem.getString();
                        }
                    }
                    if (tuition == 0.0) {
                        if (fileItem.getFieldName().equals("tuition")) {
                            tuition = Double.parseDouble(fileItem.getString());
                        }
                    }
                    if (startDate == null) {
                        if (fileItem.getFieldName().equals("startDate")) {
                            startDate = sdf.parse(fileItem.getString());
                        }
                    }
                    if (endDate == null) {
                        if (fileItem.getFieldName().equals("endDate")) {
                            endDate = sdf.parse(fileItem.getString());
                        }
                    }
                    if (category == null) {
                        if (fileItem.getFieldName().equals("category")) {
                            category = fileItem.getString();
                        }
                    }
                    if (status == null) {
                        if (fileItem.getFieldName().equals("status")) {
                            status = fileItem.getString();
                        }
                    }
                    if (quantity == 0) {
                        if (fileItem.getFieldName().equals("quantity")) {
                            quantity = Integer.parseInt(fileItem.getString());
                        }
                    }
                } else {
                    if (fileItem.getSize() > 0) {
                        image = fileItem.getName();
                        fileItem.write(new File("D:\\JavaWeb\\SE162041_Assignment_SU3W\\web\\images\\" + image));
                        System.out.println(image);
                    }
                }
            }
            CourseDTO c = new CourseDTO(name, image, description, tuition, startDate, endDate, category, status, quantity);
            CourseDAO.create(c);
            request.setAttribute("action", "search");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
