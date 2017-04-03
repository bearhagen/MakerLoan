package hr;

import java.io.Serializable;
import java.util.ArrayList;

import administration.Department;

abstract public class Person implements Serializable {
	private String name;
	private String surName;
	private String phone;
	private Department department;
	private static ArrayList<Person> people = new ArrayList<Person>(); 
	
	public Person(String name, String surName, String phone, Department department) {
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.department = department;
		people.add(this);
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
		return people;
	}
	
	/**
	 *  Check if this person is already created
	 */
	public static boolean exist(String name, String surName, String phone) {
		for (int i = 0; i < people.size(); i++) {
			Person tmpPerson = people.get(i);
			if	(tmpPerson.getName().equals(name) && tmpPerson.getSurName().equals(surName) && tmpPerson.getPhone().equals(phone)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 *  Get values and turn them into a human friendly string
	 */
	public String toString() {
		return this.getClass().getSimpleName() + " - " + name + " " + surName + " - " + phone + " - " + department;
	}
	
	/**
	 *  Runs toString method on all the items in the people list and returns them in an array
	 */
	public static ArrayList<String> toStrings() {
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < people.size(); i++) {
			strings.add(people.get(i).toString());
		}
		
		return strings;
	}
}
