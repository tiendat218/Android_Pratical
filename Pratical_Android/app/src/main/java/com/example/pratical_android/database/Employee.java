package com.example.pratical_android.database;

public class Employee {
    public int id;
    public String employeeName;
    public String designation;
    public int salary;

    public Employee() {
        super();
    }

    public Employee(int id, String employeeName, String designation, int salary) {
        super();
        this.id = id;
        this.employeeName = employeeName;
        this.designation = designation;
        this.salary = salary;
    }

    public Employee(String employeeName, String designation, int salary) {
        this.employeeName = employeeName;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
