package com.example.model;

public class CourseModel {

	private String name;
	private String email;
	private String description;

	public CourseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CourseModel [name=" + name + ", email=" + email + ", description=" + description + "]";
	}

	public CourseModel(String name, String email, String description) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
	}

}
