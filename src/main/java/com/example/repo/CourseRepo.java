package com.example.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.document.Course;

@Repository
public interface CourseRepo extends MongoRepository<Course, String> {

	public void deleteByEmail(String email);

	public List<Course> findCourseByEmail(String email);

}
