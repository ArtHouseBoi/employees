package com.company.employees.services;

import com.company.employees.models.Department;
import com.company.employees.models.Employee;
import com.company.employees.repos.DepartmentRepo;
import com.company.employees.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    public Iterable<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public Employee saveEmployee(Employee employee) {
        employee.setDepId(null);
        return employeeRepo.save(employee);
    }

    public void deleteById(Long id) throws IllegalArgumentException {
        employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
        employeeRepo.deleteById(id);
    }

    public void updateEmployee(Long id, Employee newEmployee) throws IllegalArgumentException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
        if (newEmployee.getFirstName() != null) {
            employee.setFirstName(newEmployee.getFirstName());
        }
        if (newEmployee.getLastName() != null) {
            employee.setLastName(newEmployee.getLastName());
        }
        if (newEmployee.getPhoneNumber() != null) {
            employee.setPhoneNumber(newEmployee.getPhoneNumber());
        }
        employeeRepo.save(employee);
    }

    public List<Employee> getFilteredList(Long depId) throws IllegalArgumentException{
        List<Employee> filteredList = new ArrayList<Employee>();
        if (!departmentRepo.existsById(depId)) {
            throw new IllegalArgumentException();
        }
        for (Employee i : employeeRepo.findAll()) {
            if (i.getDepId() == depId) {
                filteredList.add(i);
            }
        }
        return filteredList;
    }

    public void delFromDep(Long id) throws IllegalArgumentException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
        employee.setDepId(null);
        employeeRepo.save(employee);
    }

    public void addInDep(Long id, Long depId) throws Exception {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
        if (!departmentRepo.existsById(depId)) {
            throw new IllegalArgumentException();
        }
        if (employee.getDepId() != null) {
            throw new Exception();
        }
        employee.setDepId(depId);
        employeeRepo.save(employee);
    }

    public void addDep(Department department) {
        departmentRepo.save(department);
    }

}
