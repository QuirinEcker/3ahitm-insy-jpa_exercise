package at.htl.repository;

import at.htl.enity.Customer;
import at.htl.enity.Dish;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository extends Repository<Customer, Long> {

    public List<Dish> getEatenDishes(Long id) {
        var query = getEntityManager().createQuery(
                "select d " +
                        "from Customer c " +
                        "join c.customerOrders co " +
                        "join co.orderPositions op " +
                        "join op.dish d " +
                        "where c.id = :id",
                Dish.class
        );

        query.setParameter("id", id);

        return query.getResultList();
    }

}
