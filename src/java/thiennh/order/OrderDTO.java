/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.order;

import java.util.Date;

/**
 *
 * @author Thien's
 */
public class OrderDTO {
    private int orderId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private Date buyDateTime;

    public OrderDTO() {
    }

    public OrderDTO(String username, Date buyDateTime) {
        this.username = username;
        this.buyDateTime = buyDateTime;
    }

    public OrderDTO(String name, String email, String phone, Date buyDateTime) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.buyDateTime = buyDateTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBuyDateTime() {
        return buyDateTime;
    }

    public void setBuyDateTime(Date buyDateTime) {
        this.buyDateTime = buyDateTime;
    }
    
    
}
