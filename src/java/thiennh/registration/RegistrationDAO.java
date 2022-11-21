/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thiennh.utils.DBUtils;

/**
 *
 * @author Thien's
 */
public class RegistrationDAO implements Serializable {

    public static boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select username, password from Registration where username=? and password=?");
        stm.setString(1, username);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return true;
        }
        con.close();
        return false;
    }

    public static List<RegistrationDTO> search(String name) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select * from Registration where lastname like ?");
        stm.setString(1, "%" + name + "%");
        ResultSet rs = stm.executeQuery();
        List<RegistrationDTO> list = new ArrayList<>();
        while (rs.next()) {
            RegistrationDTO r = new RegistrationDTO();
            r.setUsername(rs.getString("username"));
            r.setPassword(rs.getString("password"));
            r.setLastname(rs.getString("lastname"));
            r.setIsAdmin(rs.getBoolean("isAdmin"));
            list.add(r);
        }
        con.close();
        return list;
    }
    public static RegistrationDTO searchById(String username) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select * from Registration where username=?");
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();
        RegistrationDTO r = null;
        if (rs.next()) {
            r = new RegistrationDTO();
            r.setUsername(rs.getString("username"));
            r.setPassword(rs.getString("password"));
            r.setLastname(rs.getString("lastname"));
            r.setIsAdmin(rs.getBoolean("isAdmin"));
        }
        con.close();
        return r;
    }

    public static void delete(String username) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Delete from Registration where username=?");
        stm.setString(1, username);
        stm.executeUpdate();
        con.close();
    }

    public static void update(RegistrationDTO r) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Update Registration set password=?, lastname=?, isAdmin=? where username=?");
        stm.setString(1, r.getPassword());
        stm.setString(2, r.getLastname());
        stm.setBoolean(3, r.isIsAdmin());
        stm.setString(4, r.getUsername());
        stm.executeUpdate();
        con.close();
    }

    public static void create(RegistrationDTO r) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("insert into Registration values(?,?,?,?)");
        stm.setString(1, r.getUsername());
        stm.setString(2, r.getPassword());
        stm.setString(3, r.getLastname());
        stm.setBoolean(4, r.isIsAdmin());
        stm.executeUpdate();
        con.close();
    }
}
