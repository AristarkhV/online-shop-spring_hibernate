package com.dao;

import java.io.Serializable;

import com.model.Employee;

public interface EmployeeDao {

	Serializable save(Employee employee);

	Employee findById(Serializable id);

}
