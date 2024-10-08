package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.EmployeeRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
@Service
public interface EmployeeService extends UserDetailsService {
	public Employee findEmployeeById(int id);
	public Employee save(EmployeeRegistrationDTO registrationDTO);
	public List<Employee> findAll();
	Boolean hasAlreadyLoginExisted (String login);
	public Employee save(Employee employee);
	public Employee findByLogin(String login);
	public Employee update(Employee employee);
	
}
