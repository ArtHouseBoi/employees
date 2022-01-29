package com.company.employees.repos;

import com.company.employees.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo extends CrudRepository<Department,Long> {
}
