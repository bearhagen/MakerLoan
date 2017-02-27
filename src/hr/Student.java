package hr;

import java.util.ArrayList;
import administration.Course;
import administration.Department;

public class Student extends Person {	
	private String studentID;
	private Course course;
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public Student(String name, String surName, String phone, Department department, ArrayList<Course> courses, String studentID) {
		super(name, surName, phone, department);
		this.courses.addAll(courses);
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
}
