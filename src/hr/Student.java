package hr;

import administration.Course;

public class Student extends Person {	
	private String studentID;
	private Course course;
	
	public Student(String name, String surName, String phone, Course course, String studentID) {
		super(name, surName, phone, course.getDepartment());
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
}
