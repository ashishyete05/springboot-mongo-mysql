package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/info")
	public String info() {
		return "Application is Up";
	}

	@PostMapping("/createresource")
	public String createStudent(@RequestBody StudentModel studentModel) {
		return studentService.createResource(studentModel);
	}

	@GetMapping("/readresources")
	public List<StudentModel> readResources() {
		return studentService.readResources();
	}

	@PutMapping("/updateresource")
	public String updateStudent(@RequestBody StudentModel studentModel) {
		return studentService.updateResource(studentModel);
	}

	@DeleteMapping("/deleteresource")
	public String deleteStudent(@RequestBody StudentModel studentmodel) {
		return studentService.deleteResource(studentmodel);
	}

}
