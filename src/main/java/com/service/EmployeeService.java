package com.service;

import com.model.Employee;

public interface EmployeeService {

	Employee getEmployee(Long id);

	void addNewEmployee(Employee employee);
}
