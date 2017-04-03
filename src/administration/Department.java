package administration;

import java.util.ArrayList;

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
	
	/**
	 *  Check if this department is already created
	 * @param code
	 * @return boolean
	 */
	public static boolean exist(String code) {
		for (int i = 0; i < departments.size(); i++) {						
			if	(departments.get(i).getCode().equals(code)) {
				return true;
			}
		}

		return false;
	}
	
	// Get values and turn them into a human friendly string
	public String toString() {
		return code + " - " + name;
	}
	
	/**
	 * Runs toString method on all the items in the departments list and returns them in an array
	 */
	public static ArrayList<String> toStrings() {
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < departments.size(); i++) {
			strings.add(departments.get(i).toString());
		}
		
		return strings;
	}
}
