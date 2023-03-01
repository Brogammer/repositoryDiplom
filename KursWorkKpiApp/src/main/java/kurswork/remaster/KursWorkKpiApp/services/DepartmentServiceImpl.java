package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DepartmentRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Department;
import kurswork.remaster.KursWorkKpiApp.repositories.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Department findById(int id) {
		// TODO Auto-generated method stub
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public Department save(DepartmentRegistrationDTO dto) {
		Department department = new Department(0, dto.getName(), null);
		return departmentRepository.save(department);
	}

}
