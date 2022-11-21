/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.order;

/**
 *
 * @author Thien's
 */
public class OrderItemDTO {
    private int orderId;
    private int cId;
    private String courseName;
    private int amount;
    private double tuition;
    private double total;

    public OrderItemDTO() {
    }

    public OrderItemDTO(int orderId, int cId, String courseName, int amount, double tuition, double total) {
        this.orderId = orderId;
        this.cId = cId;
        this.courseName = courseName;
        this.amount = amount;
        this.tuition = tuition;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
