package com.amit.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.springboot.config.AppConstants;
import com.amit.springboot.entity.EmployeeRecordEntity;
import com.amit.springboot.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api_v1")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/all")
	public ResponseEntity<Object> getAll() {
		List<EmployeeRecordEntity> listData = new ArrayList<EmployeeRecordEntity>();
		service.getAll().forEach(listData::add);
		ObjectNode node = mapper.createObjectNode();
		try {
			if (listData.isEmpty()) {
				node.put("response", AppConstants.NO_DATA);
				return new ResponseEntity<>(node, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(listData, HttpStatus.OK);
		} catch (Exception e) {
			node.put("response", AppConstants.ERRORS);
			return new ResponseEntity<>(node, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/view/{id}")
	public ResponseEntity<Object> getById(@PathVariable int id) {
		Optional<EmployeeRecordEntity> listData = service.getById(id);
		ObjectNode node = mapper.createObjectNode();
		try {
			if (listData.isPresent()) {
				return new ResponseEntity<>(listData, HttpStatus.OK);
			}
			node.put("response", AppConstants.NO_DATA);
			return new ResponseEntity<>(node, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			node.put("response", AppConstants.ERRORS);
			return new ResponseEntity<>(node, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/add")
	public ResponseEntity<Object> add(@Valid @RequestBody EmployeeRecordEntity employeeRecordEntity) {
		String response = service.add(employeeRecordEntity);
		ObjectNode node = mapper.createObjectNode();
		if (response.equalsIgnoreCase(AppConstants.DATA_ALREADY_EXISTS)) {
			node.put("response", response);
			return new ResponseEntity<>(node, HttpStatus.CREATED);
		} else {
			node.put("response", response);
			return new ResponseEntity<>(node, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody EmployeeRecordEntity employeeRecordEntity) {
		String response = service.update(employeeRecordEntity);
		ObjectNode node = mapper.createObjectNode();
		if (response.equalsIgnoreCase(AppConstants.DATA_UPDATED)) {
			node.put("response", response);
			return new ResponseEntity<>(node, HttpStatus.OK);
		} else {
			node.put("response", response);
			return new ResponseEntity<>(node, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		String response = service.deleteById(id);
		ObjectNode node = mapper.createObjectNode();
		if (response.equalsIgnoreCase(AppConstants.DATA_DELETED)) {
			node.put("response", response);
			return new ResponseEntity<>(node, HttpStatus.OK);
		} else {
			node.put("response", response);
			return new ResponseEntity<>(node, HttpStatus.BAD_REQUEST);
		}
	}

}
