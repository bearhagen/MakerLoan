package administration;

import java.util.ArrayList;

public class Course {
	private String name;
	private String courseID;
	private Department department;
	private static ArrayList<Course> courses = new ArrayList<Course>();
	
	public Course(String name, String courseID, Department department) {
		this.name = name;
		this.courseID = courseID;
		this.department = department;
		courses.add(this);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public static ArrayList<Course> getCourses() {
		return courses;
	}
	
	/**
	 *  Check if this course is already created
	 * @param courseID
	 * @return boolean
	 */
	public static boolean exist(String courseID) {
		for (int i = 0; i < courses.size(); i++) {						
			if	(courses.get(i).getCourseID().equals(courseID)) {
				return true;
			}
		}
		
		return false;
	}
}
