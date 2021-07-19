package com.amit.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amit.springboot.config.AppConstants;
import com.amit.springboot.entity.EmployeeRecordEntity;
import com.amit.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public List<EmployeeRecordEntity> getAll() {
		return repository.findAll();
	}

	public Optional<EmployeeRecordEntity> getById(int id) {
		return repository.findById(id);
	}

	public String add(EmployeeRecordEntity employeeRecordEntity) {
		Optional<EmployeeRecordEntity> listData = getById(employeeRecordEntity.getId());
		String response = "";
		if (listData.isPresent()) {
			response = AppConstants.DATA_ALREADY_EXISTS;
			return response;
		} else {
			repository.save(employeeRecordEntity);
			response = AppConstants.DATA_SAVED;
			return response;
		}

	}

	public String update(EmployeeRecordEntity employeeRecordEntity) {
		Optional<EmployeeRecordEntity> listData = getById(employeeRecordEntity.getId());
		String response = "";
		if (listData.isPresent()) {
			repository.save(employeeRecordEntity);
			response = AppConstants.DATA_UPDATED;
			return response;
		} else {
			response = AppConstants.NO_DATA;
			return response;
		}
	}

	public String deleteById(int id) {
		Optional<EmployeeRecordEntity> listData = getById(id);
		String response = "";
		if (listData.isPresent()) {
			repository.deleteById(id);
			response = AppConstants.DATA_DELETED;
			return response;
		} else {
			response = AppConstants.NO_DATA;
			return response;
		}
	}

}
