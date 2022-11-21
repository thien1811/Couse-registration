/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import thiennh.course.CourseDTO;

/**
 *
 * @author Thien's
 */
public class Cart {

    private List<CourseDTO> list = null;

    public Cart() {
        list = new ArrayList<>();
    }

    public void add(CourseDTO c) {
        list.add(c);
    }

    public void update(CourseDTO c, int quantity) {
        CourseDTO c2=new CourseDTO();
        for (CourseDTO c1 : list) {
            if (c1.getcId() == c.getcId()) {
                c2=c1;
            }
        }
        if (c2.getcId() == c.getcId()) {
            this.list.remove(c2);
            c.setQuantity(quantity);
            list.add(c);
        }
    }

    public List<CourseDTO> getList() {
        return list;
    }

    public void removeItemFromCart(int index) {
        list.remove(index);
    }

    public void empty() {
        list.clear();
    }
}
