package at.htl.enity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate openingDate;

    @ManyToMany
    @JoinTable(
            name = "Restaurant_Customer",
            joinColumns = {@JoinColumn(name = "restaurant_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private List<Customer> customers;

    @ManyToMany
    @JoinTable(
            name = "Restaurant_Dishes",
            joinColumns = {@JoinColumn(name = "restaurant_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
    private List<Dish> offeredDishes;

    @ManyToOne
    @JoinColumn(name = "opening_hours_id")
    private OpeningHours openingHours;

    public Restaurant(
            Long id,
            String name,
            String address,
            String phoneNumber,
            LocalDate openingDate,
            OpeningHours openingHours
    ) {
        this();
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openingDate = openingDate;
        this.openingHours = openingHours;
    }


    public Restaurant(
            String name,
            String address,
            String phoneNumber,
            LocalDate openingDate,
            OpeningHours openingHours
    ) {
        this();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openingDate = openingDate;
        this.openingHours = openingHours;
    }

    public Restaurant() {
        this.customers = new ArrayList<>();
        this.offeredDishes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Dish> getOfferedDishes() {
        return offeredDishes;
    }

    public void setOfferedDishes(List<Dish> offeredDishes) {
        this.offeredDishes = offeredDishes;
    }
}
