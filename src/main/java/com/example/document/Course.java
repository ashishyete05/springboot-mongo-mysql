package com.example.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String name;
	private String description;
	private String email;

	public Course(String id, String name, String description, String email) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.email = email;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", email=" + email + "]";
	}

}
