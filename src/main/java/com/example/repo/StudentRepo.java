/**
 * 
 */
package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Student;

/**
 * @author AC03582
 *
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	public boolean existsByEmail(String email);

	public List<Student> findByEmail(String email);

	public void deleteByEmail(String email);

}
