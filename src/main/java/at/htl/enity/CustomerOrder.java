package at.htl.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<CustomerOrderPosition> orderPositions;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private LocalDate orderDate;


    public CustomerOrder(Customer customer, Restaurant restaurant, LocalDate orderDate) {
        this();
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderDate = orderDate;
    }

    public CustomerOrder(Long id, Customer customer, Restaurant restaurant, LocalDate orderDate) {
        this();
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderDate = orderDate;
    }

    public CustomerOrder() {
        this.orderPositions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<CustomerOrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<CustomerOrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

