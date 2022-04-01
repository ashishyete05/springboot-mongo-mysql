package com.example.model;

import java.util.List;

import com.example.document.Course;

public class StudentModel {

	private String firstName;
	private String lastName;
	private String email;
	private List<CourseModel> courses;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CourseModel> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseModel> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "StudentModel [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", courses="
				+ courses + "]";
	}

	public StudentModel(String firstName, String lastName, String email, List<CourseModel> courses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.courses = courses;
	}

	public StudentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
