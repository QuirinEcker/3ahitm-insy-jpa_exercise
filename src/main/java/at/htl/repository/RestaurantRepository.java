package at.htl.repository;

import at.htl.enity.Restaurant;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class RestaurantRepository extends Repository<Restaurant, Long> {
}
