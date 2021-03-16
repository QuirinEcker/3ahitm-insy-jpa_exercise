package at.htl.repository;

import at.htl.enity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomerRepository extends Repository<Customer, Long> {

}
