package com.ie.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ie.entities.Employee;
import com.ie.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	//ʹ��Shiro���ע�ⲻ�ܼӵ�Service�㣬ֻ�ܼ���Controller��
	//@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo,int pageSize){
		Pageable pageable=new PageRequest(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
	}
	//@Transactional(readOnly=true)
	public Employee getByLastName(String lastName) {
		return employeeRepository.getByLastName(lastName);
	}
	//@Transactional
	public void save(Employee employee) {
		if(employee.getId()==null) {
			employee.setCreateTime(new Date());
		}
		employeeRepository.saveAndFlush(employee);
	}
	//@Transactional(readOnly=true)
	public Employee get(Integer id) {
		return employeeRepository.findOne(id);
	}
	//@Transactional
	public void delete(Integer id) {
		employeeRepository.delete(id);
	}
}
