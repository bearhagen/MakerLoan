package hr;

import administration.Department;

abstract public class Employee extends Person {
	public Employee(String name, String surName, String phone, Department department) {
		super(name, surName, phone, department);
	}

	private String employeeID;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
}
