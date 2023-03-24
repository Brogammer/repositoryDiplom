package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DepartmentRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Department;
@Service
public interface DepartmentService {
	public List<Department> findAll();
	public Department findById(int id);
	public Department save(DepartmentRegistrationDTO dto);
}
