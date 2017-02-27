package hr;

import java.util.ArrayList;

import administration.Course;
import administration.Department;

public class StudentAssistant extends Student {

	public StudentAssistant(String name, String surName, String phone, Department department, ArrayList<Course> courses, String studentID) {
		super(name, surName, phone, department, courses, studentID);
	}

}
