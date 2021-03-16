package at.htl.repository;

import at.htl.enity.CustomerOrder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerOrderRepository extends Repository<CustomerOrder, Long> {
}
