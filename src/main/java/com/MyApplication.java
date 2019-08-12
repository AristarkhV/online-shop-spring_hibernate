package com;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Employee;
import com.service.EmployeeService;

@Component
public class MyApplication {

    final static Logger logger = Logger.getLogger(MyApplication.class);

    @Autowired
    private EmployeeService empService;

    public void performDbTasks() {
        Employee employee = new Employee(1L, "Cat", "cat", "Senior Developer", 4000);
        empService.addNewEmployee(employee);
    }
}
