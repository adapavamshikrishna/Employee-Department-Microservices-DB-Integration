package com.company.employee.service;

import com.company.department.entity.Department;
import com.company.department.repository.DepartmentRepository;
import com.company.employee.entity.Employee;
import com.company.employee.repository.EmployeeRepository;
import com.company.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }
    public List<Employee> getAll() { return employeeRepository.findAll(); }
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }
    public Employee create(Employee input) {
        Long departmentId = input.getDepartment() == null ? null : input.getDepartment().getId();
        if (departmentId == null) throw new IllegalArgumentException("department.id is required");
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + departmentId));
        input.setId(null);
        input.setDepartment(department);
        return employeeRepository.save(input);
    }
    public Employee update(Long id, Employee input) {
        Employee current = getById(id);
        current.setFirstName(input.getFirstName());
        current.setLastName(input.getLastName());
        current.setEmail(input.getEmail());
        current.setSalary(input.getSalary());
        Long departmentId = input.getDepartment() == null ? null : input.getDepartment().getId();
        if (departmentId == null) throw new IllegalArgumentException("department.id is required");
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + departmentId));
        current.setDepartment(department);
        return employeeRepository.save(current);
    }
    public void delete(Long id) { employeeRepository.delete(getById(id)); }
}
