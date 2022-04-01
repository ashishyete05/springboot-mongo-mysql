package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.document.Course;
import com.example.model.CourseModel;
import com.example.model.Student;
import com.example.model.StudentModel;
import com.example.repo.CourseRepo;
import com.example.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentRepo studentRepo;

	@Transactional
	public String createResource(StudentModel studentModel) {

		if (!studentRepo.existsByEmail(studentModel.getEmail())) {

			Student student = new Student();
			BeanUtils.copyProperties(studentModel, student);
			try {
				studentRepo.save(student);
				studentModel.getCourses().forEach(c -> {
					Course course = new Course();
					c.setEmail(studentModel.getEmail());
					BeanUtils.copyProperties(c, course);
					try {
						courseRepo.save(course);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				});
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "Resource Added Successfully";
		} else
			return "Duplicate Resource";
	}

	public List<StudentModel> readResources() {
		List<StudentModel> students = new ArrayList<>();
		List<Student> studentList = new ArrayList<>();
		try {
			studentList = studentRepo.findAll(); // Fetch all the Students from the database.
		} catch (Exception e) {
			throw e;
		}

		if (studentList.size() > 0) { // If the above list is not empty then return the list after unwrapping all the
										// records
			studentList.stream().forEach(s -> { // Traverse through the reords
				StudentModel studentModel = new StudentModel();
				studentModel.setFirstName(s.getFirstName());
				studentModel.setLastName(s.getLastName());
				studentModel.setEmail(s.getEmail());
				List<Course> courseList = new ArrayList<>();
				List<CourseModel> courses = new ArrayList<>();
				try {
					courseList = courseRepo.findCourseByEmail(s.getEmail()); // Fetch all the courses by email ID.
				} catch (Exception e) {
					throw e;
				}
				if (courseList.size() > 0) {
					courseList.stream().forEach(c -> {
						CourseModel courseModel = new CourseModel();
						BeanUtils.copyProperties(c, courseModel);
						courses.add(courseModel);
					});
				}
				studentModel.setCourses(courses);
				students.add(studentModel);
			});
		}
		return students;
	}

	@Transactional
	public String updateResource(StudentModel studentModel) {
		if (studentRepo.existsByEmail(studentModel.getEmail())) { // Check for availability of resource by email
																	// ID. Update if resource exists.
			Student student = studentRepo.findByEmail(studentModel.getEmail()).get(0);
			BeanUtils.copyProperties(studentModel, student);
			try {
				studentRepo.save(student); // Update Student
				List<Course> courses = courseRepo.findCourseByEmail(studentModel.getEmail()); // Read the courses
																								// from the database
				for (int i = 0; i < studentModel.getCourses().size(); i++) {
					BeanUtils.copyProperties(studentModel.getCourses().get(i), courses.get(i));
				}

				courses.stream().forEach(c -> {
					Course course = courseRepo.findById(c.getId()).get();
					BeanUtils.copyProperties(c, course);
					course.setEmail(studentModel.getEmail());
					courseRepo.save(course);
				});
//	                studentModel.getCourses().stream().forEach(c -> { //Traverse through the studentModel to fetch all the courses
//	                    try {
//	                        Course course = courseRepository.findCourseByEmail(studentModel.getEmail()); //Read the courses from the database
//	                        if (Objects.nonNull(courses)){ //Update the course if exists
//	                            courses.stream().forEach(c1 -> System.out.println(c1.getId()));
//	                            c.setEmail(studentModel.getEmail());
//	                            BeanUtils.copyProperties(c, course);
//	                            try {
//	                                courseRepository.save(course);
//	                            }catch (Exception e){
//	                                throw e;
//	                            }
//	                        }
//	                    }catch (Exception e){
//	                        throw e;
//	                    }
//	                });
			} catch (Exception e) {
				throw e;
			}
			return "Resource was updated successfully";
		} else {
			return "Resource does not exist";
		}
	}

	@Transactional
	public String deleteResource(StudentModel studentModel) {
		if (studentRepo.existsByEmail(studentModel.getEmail())) {
			try {
				studentRepo.deleteByEmail(studentModel.getEmail());
				try {
					courseRepo.deleteByEmail(studentModel.getEmail());
				} catch (Exception e) {
					throw e;
				}
			} catch (Exception e) {
				throw e;
			}
			return "Removed successfully!";
		} else {
			return "Resource does not exist.";
		}
	}
}
