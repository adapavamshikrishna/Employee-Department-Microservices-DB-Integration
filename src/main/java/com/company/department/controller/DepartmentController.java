package com.company.department.controller;

import com.company.department.entity.Department;
import com.company.department.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service) { this.service = service; }

    @GetMapping public List<Department> getAll() { return service.getAll(); }
    @GetMapping("/{id}") public Department getById(@PathVariable Long id) { return service.getById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Department create(@Valid @RequestBody Department department) { return service.create(department); }
    @PutMapping("/{id}") public Department update(@PathVariable Long id, @Valid @RequestBody Department department) { return service.update(id, department); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
