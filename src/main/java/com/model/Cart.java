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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Cart", schema = "test_spring")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCart")
    private Long cartID;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_cart",
            joinColumns = {@JoinColumn(name = "idCart")},
            inverseJoinColumns = {@JoinColumn(name = "idProduct")})
    private List<Product> products;

    public Cart() {
    }

    public Cart(Long codeID, ArrayList<Product> products, User user) {
        this.cartID = codeID;
        this.user = user;
        this.products = products;
    }

    public Cart(ArrayList<Product> products, User user) {
        this.cartID = IdCreator.idCreator();
        this.user = user;
        this.products = products;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartID, cart.cartID) &&
                Objects.equals(user, cart.user) &&
                Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, user, products);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
