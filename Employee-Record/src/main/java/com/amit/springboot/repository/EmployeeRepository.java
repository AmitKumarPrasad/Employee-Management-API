package com.amit.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amit.springboot.entity.EmployeeRecordEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRecordEntity, Integer> {

}
