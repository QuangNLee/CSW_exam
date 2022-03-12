package com.example.exam.model;

import com.example.exam.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeModelTest {
    private EmployeeModel employeeModel;

    @BeforeEach
    void setUp(){
        employeeModel = new EmployeeModel();
    }

    @Test
    void findAll() throws SQLException {
        Employee employee = new Employee("Quang", 10000000);
        Employee employee1 = employeeModel.add(employee);
        List<Employee> employeeList = employeeModel.findAll();
        assertThat(employeeList.size()).isGreaterThan(0);
    }

    @Test
    void findById() throws SQLException {
        Employee employee = new Employee("Diep", 12000000);
        Employee insertEmployee = employeeModel.add(employee);
        Employee foundEmployee = employeeModel.findById(insertEmployee.getId());
        assertThat(foundEmployee).isNotNull();
    }

    @Test
    void add() throws SQLException {
        Employee employee = new Employee("Nguyen Bich Diep", 15000000);
        EmployeeModel employeeModel = new EmployeeModel();
        Employee employee1 = employeeModel.add(employee);
        System.out.println(employee1.getId());
        assertThat(employee1.getId()).isNotEqualTo(0);
    }

    @Test
    void update() throws SQLException {
        Employee employee = new Employee("Christian", 15000000);
        Employee insertEmployee = employeeModel.add(employee);
        Employee updateEmployee = new Employee("Le Ngoc Quang", 12500000);
        Employee updatedEmployee = employeeModel.update(insertEmployee.getId(), updateEmployee);
        assertThat(updatedEmployee.getName()).isEqualTo(updatedEmployee.getName());
        assertThat(updatedEmployee.getSalary()).isEqualTo(updateEmployee.getSalary());
    }
}