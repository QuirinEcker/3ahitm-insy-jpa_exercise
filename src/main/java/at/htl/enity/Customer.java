package at.htl.enity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends Person {

    @ManyToMany(mappedBy = "customers")
    private List<Restaurant> restaurants;

    private int points;

    public Customer() {
        this.restaurants = new ArrayList<>();
    }

    public Customer(
            Long id,
            String firstname,
            String lastname,
            String address,
            LocalDate birthDate,
            int points
    ) {
        super(id, firstname, lastname, address, birthDate);
        this.restaurants = new ArrayList<>();
        this.points = points;
    }

    public Customer(
            String firstname,
            String lastname,
            String address,
            LocalDate birthDate,
            int points
    ) {
        super(firstname, lastname, address, birthDate);
        this.points = points;
        this.restaurants = new ArrayList<>();
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
