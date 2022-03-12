package com.example.exam.model;

import com.example.exam.entity.Employee;
import com.example.exam.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private Connection connection;

    public EmployeeModel(){
        connection = ConnectionHelper.getConnection();
    }

    public List<Employee> findAll() throws SQLException{
        List<Employee> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from employees");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double salary = resultSet.getDouble("salary");
            list.add(new Employee(id, name, salary));
        }
        return list;
    }

    public Employee findById(int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("select * from employees where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String name = resultSet.getString("name");
            double salary = resultSet.getDouble("salary");
            return new Employee(id, name, salary);
        }
        return null;
    }

    public Employee add(Employee employee) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into employees (name, salary) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setDouble(2, employee.getSalary());
        int affectedRows = preparedStatement.executeUpdate();
        if(affectedRows > 0){
            ResultSet resultSetGeneratedKeys = preparedStatement.getGeneratedKeys();
            if(resultSetGeneratedKeys.next()){
                int id = resultSetGeneratedKeys.getInt(1);
                employee.setId(id);
                return employee;
            }
        }
        return null;
    }

    public Employee update(int id, Employee updateEmployee) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("update employees set name = ?, salary = ? where id = ?");
        preparedStatement.setString(1, updateEmployee.getName());
        preparedStatement.setDouble(2, updateEmployee.getSalary());
        preparedStatement.setInt(3, id);
        int affectedRows = preparedStatement.executeUpdate();
        if(affectedRows > 0){
            updateEmployee.setId(id);
            return updateEmployee;
        }
        return null;
    }
}
