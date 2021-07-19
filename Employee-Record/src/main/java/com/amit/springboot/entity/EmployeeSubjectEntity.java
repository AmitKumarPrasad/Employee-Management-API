package com.amit.springboot.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "employee_subject")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeSubjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Employee_subjects")
	private String subjectName;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_record_entity_id", insertable = false, updatable = false)
	private EmployeeRecordEntity employeeRecordEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public EmployeeRecordEntity getEmployeeRecordEntity() {
		return employeeRecordEntity;
	}

	public void setEmployeeRecordEntity(EmployeeRecordEntity employeeRecordEntity) {
		this.employeeRecordEntity = employeeRecordEntity;
	}

	@Override
	public String toString() {
		return "EmployeeSubjectEntity [id=" + id + ", subjectName=" + subjectName + ", employeeRecordEntity="
				+ employeeRecordEntity + "]";
	}

}
