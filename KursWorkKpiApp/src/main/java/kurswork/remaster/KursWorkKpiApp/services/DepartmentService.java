package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import kurswork.remaster.KursWorkKpiApp.dto.DepartmentRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Department;

public interface DepartmentService {
	public List<Department> findAll();
	public Department findById(int id);
	public Department save(DepartmentRegistrationDTO dto);
}
