/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thiennh.cart.Cart;
import thiennh.config.Config;
import thiennh.course.CourseDAO;
import thiennh.course.CourseDTO;
import thiennh.order.OrderDAO;
import thiennh.order.OrderDTO;
import thiennh.order.OrderItemDAO;
import thiennh.order.OrderItemDTO;

/**
 *
 * @author Thien's
 */
public class CartController extends HttpServlet {

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
            case "add":
                add(request, response);
                break;
            case "view":
                view(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "delete_handler":
                delete_handler(request, response);
                break;
            case "information":
                information(request, response);
                break;
            case "checkout":
                checkout(request, response);
                break;
        }
    }

    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int cId = Integer.parseInt(request.getParameter("cId"));
            double tuition = Double.parseDouble(request.getParameter("tuition"));
            int quantity = 1;
            CourseDTO c = new CourseDTO(cId, name, tuition);
            CourseDTO c1 = new CourseDTO();
            //Tao doi tuong session
            HttpSession session = request.getSession();
            //Lay cart tu session
            Cart cart = (Cart) session.getAttribute("cart");
            //Neu trong session chua co cart thi tao cart moi
            if (cart == null) {
                cart = new Cart();
                c.setQuantity(quantity);
                // Them product vao cart
                cart.add(c);
            } else {
                for (CourseDTO course : cart.getList()) {
                    if (course.getcId() == cId) {
                        c1 = course;
                    }
                }
                if (c1.getcId() == cId) {
                    quantity = cart.getList().get(cart.getList().indexOf(c1)).getQuantity() + 1;
                    cart.removeItemFromCart(cart.getList().indexOf(c1));
                }
                c.setQuantity(quantity);
                // Them product vao cart
                cart.add(c);
            }
            session.setAttribute("cart", cart);
            String search = request.getParameter("search");
            String page = request.getParameter("page");
            int intpage = 0;
            int totalpage = 0;
            if (page != null) {
                intpage = Integer.parseInt(page);
            }
            List<CourseDTO> list = CourseDAO.search(search);
            List<CourseDTO> sublist = new LinkedList<>();
            List<Integer> pageList = new LinkedList<>();
            if (list.size() > 0) {
                totalpage = list.size() % 20 == 0 ? list.size() / 20 : (list.size() / 20) + 1;
                for (int i = 0; i < totalpage; i++) {
                    pageList.add(i);
                }
                int n = (intpage - 1) * 20;
                
                if (list.size() >= n + 20) {
                    sublist = list.subList(n, n + 20);
                } else {
                    sublist = list.subList(n, list.size());
                }
            }
            request.setAttribute("sublist", sublist);
            request.setAttribute("search", search);
            request.setAttribute("page", page);
            request.setAttribute("noOfPage", pageList);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "search");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Tao doi tuong session
        HttpSession session = request.getSession();
        //Lay cart tu session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            List<CourseDTO> list = cart.getList();
            request.setAttribute("list", list);
        }
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int cId = Integer.parseInt(request.getParameter("cId"));
        double tuition = Double.parseDouble(request.getParameter("tuition"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        CourseDTO c = new CourseDTO(cId, name, tuition);
        //Tao doi tuong session
        HttpSession session = request.getSession();
        //Lay cart tu session
        Cart cart = (Cart) session.getAttribute("cart");
        
        cart.update(c, quantity);
        if (cart != null) {
            List<CourseDTO> list = cart.getList();
            request.setAttribute("list", list);
        }
        request.setAttribute("action", "view");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cId = Integer.parseInt(request.getParameter("cId"));
        //Tao doi tuong session
        HttpSession session = request.getSession();
        //Lay cart tu session
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            List<CourseDTO> list = cart.getList();
            request.setAttribute("list", list);
        }
        request.setAttribute("cId", cId);
        request.setAttribute("action", "view");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void delete_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cId = Integer.parseInt(request.getParameter("cId"));
        //Tao doi tuong session
        HttpSession session = request.getSession();
        //Lay cart tu session
        Cart cart = (Cart) session.getAttribute("cart");
        CourseDTO c1 = new CourseDTO();
        for (CourseDTO course : cart.getList()) {
            if (course.getcId() == cId) {
                c1 = course;
            }
        }
        if (c1.getcId() == cId) {
            cart.removeItemFromCart(cart.getList().indexOf(c1));
        }
        if (cart != null) {
            List<CourseDTO> list = cart.getList();
            request.setAttribute("list", list);
        }
        request.setAttribute("action", "view");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void information(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Tao doi tuong session
            HttpSession session = request.getSession();
            //Lay cart tu session
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                request.setAttribute("action", "view");
                request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
            } else {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String username = (String) session.getAttribute("username");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                OrderDTO o = new OrderDTO(name, email, phone, date);
                OrderDAO.create(o);
                int orderId = OrderDAO.searchId(o);
                List<CourseDTO> list = cart.getList();
                for (CourseDTO c : list) {
                    OrderItemDTO oi = new OrderItemDTO();
                    oi.setOrderId(orderId);
                    oi.setcId(c.getcId());
                    oi.setCourseName(c.getName());
                    oi.setAmount(c.getQuantity());
                    oi.setTuition(c.getTuition());
                    oi.setTotal(c.getQuantity() * c.getTuition());
                    OrderItemDAO.create(oi);
                }
                cart.empty();
                request.setAttribute("orderId", orderId);
                request.setAttribute("action", "checkout");
            }
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Tao doi tuong session
            HttpSession session = request.getSession();
            //Lay cart tu session
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                request.setAttribute("action", "view");
                request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
            } else {
                String username = (String) session.getAttribute("username");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                OrderDTO o = new OrderDTO(username, date);
                OrderDAO.create(o);
                int orderId = OrderDAO.searchId(o);
                List<CourseDTO> list = cart.getList();
                for (CourseDTO c : list) {
                    OrderItemDTO oi = new OrderItemDTO();
                    oi.setOrderId(orderId);
                    oi.setcId(c.getcId());
                    oi.setCourseName(c.getName());
                    oi.setAmount(c.getQuantity());
                    oi.setTuition(c.getTuition());
                    oi.setTotal(c.getQuantity() * c.getTuition());
                    OrderItemDAO.create(oi);
                }
                cart.empty();
                request.setAttribute("orderId", orderId);
            }
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
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
