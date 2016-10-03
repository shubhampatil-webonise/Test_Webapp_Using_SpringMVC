package org.webonise.springmvc.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.webonise.springmvc.Entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
