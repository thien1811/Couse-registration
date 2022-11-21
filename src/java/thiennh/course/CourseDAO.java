/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.naming.NamingException;
import thiennh.registration.RegistrationDTO;
import thiennh.utils.DBUtils;

/**
 *
 * @author Thien's
 */
public class CourseDAO {

    public static List<CourseDTO> search(String name) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select * from Course where name like ? and status='active' and quantity > 0 order by StartDate desc");
        stm.setString(1, "%" + name + "%");
        ResultSet rs = stm.executeQuery();
        List<CourseDTO> list = new LinkedList<>();
        while (rs.next()) {
            CourseDTO c = new CourseDTO();
            c.setcId(rs.getInt("cId"));
            c.setName(rs.getString("name"));
            c.setImage(rs.getString("image"));
            c.setDescription(rs.getString("description"));
            c.setTuition(rs.getDouble("tuition"));
            c.setStartDate(rs.getDate("startDate"));
            c.setEndDate(rs.getDate("endDate"));
            c.setCategory(rs.getString("category"));
            c.setStatus(rs.getString("status"));
            c.setQuantity(rs.getInt("quantity"));
            list.add(c);
        }
        con.close();
        return list;
    }

    public static List<CourseDTO> searchForAd(String name) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select * from Course where name like ? order by StartDate desc");
        stm.setString(1, "%" + name + "%");
        ResultSet rs = stm.executeQuery();
        List<CourseDTO> list = new LinkedList<>();
        while (rs.next()) {
            CourseDTO c = new CourseDTO();
            c.setcId(rs.getInt("cId"));
            c.setName(rs.getString("name"));
            c.setImage(rs.getString("image"));
            c.setDescription(rs.getString("description"));
            c.setTuition(rs.getDouble("tuition"));
            c.setStartDate(rs.getDate("startDate"));
            c.setEndDate(rs.getDate("endDate"));
            c.setCategory(rs.getString("category"));
            c.setStatus(rs.getString("status"));
            c.setQuantity(rs.getInt("quantity"));
            list.add(c);
        }
        con.close();
        return list;
    }

    public static CourseDTO searchById(int cId) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select * from Course where cId=?");
        stm.setInt(1, cId);
        ResultSet rs = stm.executeQuery();
        CourseDTO c = null;
        if (rs.next()) {
            c = new CourseDTO();
            c.setcId(rs.getInt("cId"));
            c.setName(rs.getString("name"));
            c.setImage(rs.getString("image"));
            c.setDescription(rs.getString("description"));
            c.setTuition(rs.getDouble("tuition"));
            c.setStartDate(rs.getDate("startDate"));
            c.setEndDate(rs.getDate("endDate"));
            c.setCategory(rs.getString("category"));
            c.setStatus(rs.getString("status"));
            c.setQuantity(rs.getInt("quantity"));
        }
        con.close();
        return c;
    }

    public static List<CourseDTO> searchByCategory(String category) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select * from Course where category = ? order by StartDate desc");
        stm.setString(1, category);
        ResultSet rs = stm.executeQuery();
        List<CourseDTO> list = new LinkedList<>();
        while (rs.next()) {
            CourseDTO c = new CourseDTO();
            c.setcId(rs.getInt("cId"));
            c.setName(rs.getString("name"));
            c.setImage(rs.getString("image"));
            c.setDescription(rs.getString("description"));
            c.setTuition(rs.getDouble("tuition"));
            c.setStartDate(rs.getDate("startDate"));
            c.setEndDate(rs.getDate("endDate"));
            c.setCategory(rs.getString("category"));
            c.setStatus(rs.getString("status"));
            c.setQuantity(rs.getInt("quantity"));
            list.add(c);
        }
        con.close();
        return list;
    }

    public static boolean update(CourseDTO c) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Update Course set name=?, image=?, description=?, tuition=?, "
                + "startDate=?, endDate=?, category=?, status=?, quantity=?   where cId=?");
        stm.setString(1, c.getName());
        stm.setString(2, c.getImage());
        stm.setString(3, c.getDescription());
        stm.setDouble(4, c.getTuition());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(5, sdf.format(c.getStartDate()));
        stm.setString(6, sdf.format(c.getEndDate()));
        stm.setString(7, c.getCategory());
        stm.setString(8, c.getStatus());
        stm.setInt(9, c.getQuantity());
        stm.setInt(10, c.getcId());
        int rs = stm.executeUpdate();
        con.close();
        return rs != 0;
    }

    public static boolean create(CourseDTO c) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("insert into Course values(?,?,?,?,?,?,?,?,?)");
        stm.setString(1, c.getName());
        stm.setString(2, c.getImage());
        stm.setString(3, c.getDescription());
        stm.setDouble(4, c.getTuition());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(5, sdf.format(c.getStartDate()));
        stm.setString(6, sdf.format(c.getEndDate()));
        stm.setString(7, c.getCategory());
        stm.setString(8, c.getStatus());
        stm.setInt(9, c.getQuantity());
        int rs=stm.executeUpdate();
        con.close();
        return rs!=0;
    }
}
