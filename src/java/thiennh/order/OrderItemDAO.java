/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import thiennh.utils.DBUtils;

/**
 *
 * @author Thien's
 */
public class OrderItemDAO {
        public static boolean create(OrderItemDTO o) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("insert into [dbo].[OrderItem] values(?,?,?,?,?,?)");
        stm.setInt(1, o.getOrderId());
        stm.setInt(2, o.getcId());
        stm.setString(3, o.getCourseName());
        stm.setInt(4, o.getAmount());
        stm.setDouble(5, o.getTuition());
        stm.setDouble(6, o.getTotal());
        int rs = stm.executeUpdate();
        con.close();
        return rs != 0;
    }
}
