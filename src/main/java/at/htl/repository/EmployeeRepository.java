package at.htl.repository;

import at.htl.enity.Employee;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository extends Repository<Employee, Long> {
}
