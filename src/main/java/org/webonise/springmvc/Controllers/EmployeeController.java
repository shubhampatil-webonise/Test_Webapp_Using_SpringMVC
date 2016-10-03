package org.webonise.springmvc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.webonise.springmvc.Entities.Employee;
import org.webonise.springmvc.Entities.Team;
import org.webonise.springmvc.Repositories.EmployeeRepository;
import org.webonise.springmvc.Repositories.TeamRepository;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/employees/{id}")
    public String employee(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeRepository.findOne(id));
        model.addAttribute("teams", teamRepository.findAll());
        return "employee";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employeesList";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String saveEmployee(@RequestParam String name, Model model) {
        Employee employee = new Employee();
        employee.setName(name);
        employeeRepository.save(employee);

        model.addAttribute("employee", employee);
        model.addAttribute("teams", teamRepository.findAll());
        return "redirect:/employee/" + employee.getId();
    }

    @RequestMapping(value = "/employee/{id}/teams", method = RequestMethod.POST)
    public String addEmployeeToTeam(@PathVariable int id, @RequestParam int teamId, Model model) {
        Team team = teamRepository.findOne(teamId);
        Employee employee = employeeRepository.findOne(id);

        if (employee != null) {
            if (!employee.isMember(team)) {
                employee.getTeamList().add(team);
            }

            employeeRepository.save(employee);
            model.addAttribute("employee", employeeRepository.findOne(id));
            model.addAttribute("teams", teamRepository.findAll());
            return "redirect:/employee/" + employee.getId();
        }

        model.addAttribute("employees", employeeRepository.findAll());
        return "redirect:/employees";
    }
}
