package hr;

import administration.Department;

public class Person {
	private String name;
	private String surName;
	private String phone;
	private Department Department;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepartment() {
		return Department;
	}

	public void setDepartment(Department department) {
		Department = department;
	}
}
