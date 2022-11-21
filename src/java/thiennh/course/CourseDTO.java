/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.course;

import java.util.Date;

/**
 *
 * @author Thien's
 */
public class CourseDTO {
    private int cId;
    private String name;
    private String image;
    private String description;
    private double tuition;
    private Date startDate;
    private Date endDate;
    private String category;
    private String status;
    private int quantity;

    public CourseDTO() {
    }

    public CourseDTO(int cId, String name, double tuition) {
        this.cId = cId;
        this.name = name;
        this.tuition = tuition;
    }

    
    
    public CourseDTO(String name, String image, String description, double tuition, Date startDate, Date endDate, String category, String status, int quantity) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.tuition = tuition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
    }

    public CourseDTO(int cId, String name, String image, String description, double tuition, Date startDate, Date endDate, String category, String status, int quantity) {
        this.cId = cId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.tuition = tuition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
