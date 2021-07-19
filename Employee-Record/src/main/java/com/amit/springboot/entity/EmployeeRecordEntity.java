package com.amit.springboot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRecordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "employee_name")
	private String name;

	@Column(name = "employee_course")
	private String course;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_record_entity_id", nullable = false, updatable = false)
	private List<EmployeeSubjectEntity> employeeSubjects = new ArrayList<EmployeeSubjectEntity>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public List<EmployeeSubjectEntity> getEmployeeSubjects() {
		return employeeSubjects;
	}

	public void setEmployeeSubjects(List<EmployeeSubjectEntity> employeeSubjects) {
		this.employeeSubjects = employeeSubjects;
	}

	@Override
	public String toString() {
		return "EmployeeRecordEntity [id=" + id + ", name=" + name + ", course=" + course + ", employeeSubjects="
				+ employeeSubjects + "]";
	}

}
