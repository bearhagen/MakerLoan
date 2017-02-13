package hr;

public class Employee extends Person {
	public Employee(String name) {
		super(name);
	}

	private String employeeID;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
}
