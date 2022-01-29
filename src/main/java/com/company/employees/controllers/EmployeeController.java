package com.company.employees.controllers;

import com.company.employees.models.Department;
import com.company.employees.models.Employee;
import com.company.employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee")
    private @ResponseBody Iterable<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @DeleteMapping(path = "/employee/{id}")
    private ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteById(id);
        }catch (IllegalArgumentException e ) {
            return  ResponseEntity.notFound().build();
        }
     return ResponseEntity.ok().body("deleted");
    }

    @PostMapping (path = "/employee")
    private ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok().body("employee saved, id = " + employee.getId());
    }

    @PutMapping (path = "/employee/{id}")
    private ResponseEntity<String> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        try{
        employeeService.updateEmployee(id,employee); } catch (IllegalArgumentException e) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("updated");
    }

    @GetMapping(path = "/employee/{depId}")
    private @ResponseBody
    ResponseEntity<List<Employee>> getFilteredList(@PathVariable("depId") Long id) {
        List<Employee> list = new ArrayList<>();
        try {
            list = employeeService.getFilteredList(id);
        } catch (IllegalArgumentException e) {
            return  ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok().body(list);
    }

    @PutMapping (path = "/addInDepartment/{id}/{depId}")
    private ResponseEntity<String>  addInDep (@PathVariable("id") Long id, @PathVariable("depId") Long depId) {
        try {
            employeeService.addInDep(id, depId);
        } catch (IllegalArgumentException e) {
            return  ResponseEntity.notFound().build();
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("the employee has already been assigned to the department");
        }
        return ResponseEntity.ok().body("added");
    }

    @PutMapping (path = "/deleteFromDepartment/{id}")
    private ResponseEntity<String> delFromDep (@PathVariable("id") Long id) {
        try {
            employeeService.delFromDep(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("deleted");
    }

    @PostMapping (path = "/department")
    private ResponseEntity<String> createDep(@RequestBody Department department) {
        employeeService.addDep(department);
        return ResponseEntity.ok().body("department saved, id = " + department.getId());
    }
}
