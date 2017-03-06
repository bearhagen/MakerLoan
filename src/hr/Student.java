package hr;

import java.util.ArrayList;
import administration.Course;
import administration.Department;

public class Student extends Person {	
	private String studentID;
	public static int studentCounter;
	private Course course;
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public Student(String name, String surName, String phone, Department department, ArrayList<Course> courses) {
		super(name, surName, phone, department);
		studentID = makeStudentID();
		this.courses.addAll(courses);
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	public static String makeStudentID() {
		return String.format("%06d", ++studentCounter);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " - " + studentID + " - " + getName() + " " + getSurName() + " - " + getPhone() + " - " + getDepartment();
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
}
