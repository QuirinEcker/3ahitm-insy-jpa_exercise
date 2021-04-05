package at.htl.enity;

import org.hibernate.criterion.Order;

import javax.persistence.*;

@Entity
public class CustomerOrderPosition {
    @Id
    private Long id;
    private int orderPos;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private CustomerOrder order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    public CustomerOrderPosition(int orderPos, int quantity, CustomerOrder order, Dish dish) {
        this.orderPos = orderPos;
        this.quantity = quantity;
        this.order = order;
        this.dish = dish;
    }

    public CustomerOrderPosition(Long id, int orderPos, int quantity, CustomerOrder order, Dish dish) {
        this.id = id;
        this.orderPos = orderPos;
        this.quantity = quantity;
        this.order = order;
        this.dish = dish;
    }

    public CustomerOrderPosition(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CustomerOrderPosition(int quantity) {
        this.quantity = quantity;
    }

    public CustomerOrderPosition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderPos() {
        return orderPos;
    }

    public void setOrderPos(int orderPos) {
        this.orderPos = orderPos;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }
}
