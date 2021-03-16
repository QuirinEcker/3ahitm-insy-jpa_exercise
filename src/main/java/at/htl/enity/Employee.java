package at.htl.enity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends Person {

    private String ssn;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Employee(
            String firstname,
            String lastname,
            String address,
            LocalDate birthdate,
            String ssn,
            Restaurant restaurant
    ) {
        super(firstname, lastname, address, birthdate);
        this.ssn = ssn;
        this.restaurant = restaurant;
    }

    public Employee(
            Long id,
            String firstname,
            String lastname,
            String address,
            LocalDate birthdate,
            String ssn,
            Restaurant restaurant
    ) {
        super(id, firstname, lastname, address, birthdate);
        this.ssn = ssn;
        this.restaurant = restaurant;
    }

    public Employee() {

    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
