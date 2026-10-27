package com.company.department.service;

import com.company.department.entity.Department;
import com.company.department.repository.DepartmentRepository;
import com.company.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    public DepartmentService(DepartmentRepository repository) { this.repository = repository; }

    public List<Department> getAll() { return repository.findAll(); }
    public Department getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));
    }
    public Department create(Department department) { return repository.save(department); }
    public Department update(Long id, Department input) {
        Department current = getById(id);
        current.setName(input.getName());
        current.setDescription(input.getDescription());
        return repository.save(current);
    }
    public void delete(Long id) { repository.delete(getById(id)); }
}
