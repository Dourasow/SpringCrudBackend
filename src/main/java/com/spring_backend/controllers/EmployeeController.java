package com.spring_backend.controllers;

import com.spring_backend.exceptions.SourceNotFoundException;
import com.spring_backend.model.Employee;
import com.spring_backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/vi")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    //Add Employee
    @PostMapping("/employees")
    public Employee addNew(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Get Employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new SourceNotFoundException("Employee Id not found!" + id));

        return ResponseEntity.ok(employee);
    }

    //update Employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee employee1 = employeeRepository.findById(id).orElseThrow(()->
                new SourceNotFoundException("Employee Id not found" + id));

        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());

        Employee updateEmployee = employeeRepository.save(employee1);
        return ResponseEntity.ok(updateEmployee);
    }


    //delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow( () ->
                new SourceNotFoundException("Employee Id not found" + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);


    }
}
