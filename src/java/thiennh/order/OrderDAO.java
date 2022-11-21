/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import thiennh.utils.DBUtils;

/**
 *
 * @author Thien's
 */
public class OrderDAO {

    public static boolean create(OrderDTO o) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("insert into [dbo].[Order] values(?,?,?,?,?)");
        stm.setString(1, o.getUsername());
        stm.setString(2, o.getName());
        stm.setString(3, o.getEmail());
        stm.setString(4, o.getPhone());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stm.setString(5, sdf.format(o.getBuyDateTime()));
        int rs = stm.executeUpdate();
        con.close();
        return rs != 0;
    }

    public static int searchId(OrderDTO o) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select orderId from [dbo].[Order] where (username=? and BuyDateTime=?) or "
                + " (BuyDateTime=? and phone=?)");
        stm.setString(1, o.getUsername());
        stm.setString(4, o.getPhone());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        stm.setString(2, sdf.format(o.getBuyDateTime()));
        stm.setString(3, sdf.format(o.getBuyDateTime()));
        ResultSet rs = stm.executeQuery();
        int id = 0;
        if (rs.next()) {
            id=rs.getInt("orderId");
        }
        con.close();
        return id;
    }
}
