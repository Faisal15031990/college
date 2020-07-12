package com.api.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.college.entity.College;
public interface CollegeRepository extends JpaRepository<College, Long>{
	public List<College> findByCollegeName(String collegeName);
}