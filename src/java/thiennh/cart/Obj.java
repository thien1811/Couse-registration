/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.cart;

/**
 *
 * @author Thien's
 */
public class Obj {
    private int orderId;
    private int cId;
    private String name;
    private int quantity;
    private double tuition;
    private float total;

    public Obj() {
    }

    public Obj(int cId) {
        this.cId = cId;
    }

    public Obj(int cId, String name, double tuition) {
        this.cId = cId;
        this.name = name;
        this.tuition = tuition;
    }

    public Obj(int cId, String name, int quantity, double tuition, float total) {
        this.cId = cId;
        this.name = name;
        this.quantity = quantity;
        this.tuition = tuition;
        this.total = total;
    }


    public Obj(int orderId, int cId, String name, int quantity, double tuition, float total) {
        this.orderId = orderId;
        this.cId = cId;
        this.name = name;
        this.quantity = quantity;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    
}
