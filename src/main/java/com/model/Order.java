package com.model;

import com.util.IdCreator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`Order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder")
    private Long orderID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idUser")
    private User user;

    @Column(name = "email")
    private String email;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_order",
            joinColumns = {@JoinColumn(name = "idOrder")},
            inverseJoinColumns = {@JoinColumn(name = "idProduct")})
    private List<Product> orderProducts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "code_order",
            joinColumns = {@JoinColumn(name = "idOrder")},
            inverseJoinColumns = {@JoinColumn(name = "idCode")})
    private Code code;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Order() {
    }

    public Order(User user, String email, String deliveryAddress, List<Product> orderProducts) {
        this.orderID = IdCreator.idCreator();
        this.email = email;
        this.user = user;
        this.orderProducts = orderProducts;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(Long orderID, User user, String email, String deliveryAddress, List<Product> orderProducts) {
        this.orderID = orderID;
        this.email = email;
        this.user = user;
        this.orderProducts = orderProducts;
        this.deliveryAddress = deliveryAddress;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) &&
                Objects.equals(user, order.user) &&
                Objects.equals(email, order.email) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(orderProducts, order.orderProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, user, email, deliveryAddress, orderProducts);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", user=" + user +
                ", email='" + email + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderProducts=" + orderProducts +
                '}';
    }
}
