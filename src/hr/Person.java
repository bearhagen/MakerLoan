package hr;

import java.util.ArrayList;

import administration.Department;

abstract public class Person {
	private String name;
	private String surName;
	private String phone;
	private Department department;
	private static ArrayList<Person> persons = new ArrayList<Person>(); 
	
	public Person(String name, String surName, String phone, Department department) {
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.department = department;
		persons.add(this);
	}

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
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public static ArrayList<Person> getPersons() {
		return persons;
	}
}
