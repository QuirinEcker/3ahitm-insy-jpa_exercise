package at.htl.boundry;

import at.htl.enity.Customer;
import at.htl.repository.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("customer")
public class CustomerService {

    @Inject
    CustomerRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> get() {
        return repository.listAll();
    }

}
