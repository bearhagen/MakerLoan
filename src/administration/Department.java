package administration;

import java.util.ArrayList;

import hr.Person;

public class Department {
	private String name;
	private String code;
	private static ArrayList<Department> departments = new ArrayList<Department>();
	
	public Department(String name, String code) {
		this.name = name;
		this.code = code;
		departments.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static ArrayList<Department> getDepartments() {
		return departments;
	}
}
