package org.webonise.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.webonise.springmvc.Entities.Employee;
import org.webonise.springmvc.Entities.Team;
import org.webonise.springmvc.Repositories.EmployeeRepository;
import org.webonise.springmvc.Repositories.TeamRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void run(String... strings) throws Exception {
        Team java = new Team("Java");
        Team ruby = new Team("Ruby");
        Team php = new Team("php");

        teamRepository.save(java);
        teamRepository.save(ruby);
        teamRepository.save(php);

        List<Employee> employees = new LinkedList<Employee>();
        employees.add(new Employee("Shubham Patil", Arrays.asList(new Team[]{java})));
        employees.add(new Employee("Shirish Parsekar", Arrays.asList(new Team[]{ruby})));
        employees.add(new Employee("Mayuri Ardad", Arrays.asList(new Team[]{php})));

        employeeRepository.save(employees);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
