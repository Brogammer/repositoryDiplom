package kurswork.remaster.KursWorkKpiApp.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.UserRole;
import kurswork.remaster.KursWorkKpiApp.repositories.EmployeeRepository;
import kurswork.remaster.KursWorkKpiApp.repositories.UserRoleRepository;
@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public UserRole findUserRoleById(int id) {
		
		return userRoleRepository.findById(id).get();
	}
	@Override
	public void save(UserRole role) {
		userRoleRepository.save(role);
	}
	@Override
	public void assignEmployeeRole (Integer userId, Integer roleId) {
		Employee employee = employeeRepository.findById(userId).orElse(null);
		UserRole role = userRoleRepository.findById(roleId).orElse(null);
		Set<UserRole> userRoles = employee.getUserRoles();
		userRoles.add(role);
		employee.setUserRoles(userRoles);
		employeeRepository.save(employee);
	}
	
	public void unassignEmployeeRole (Integer userId, Integer roleId) {
		Employee employee = employeeRepository.findById(userId).orElse(null);
		Set<UserRole> userRoles = employee.getUserRoles();
		userRoles.removeIf(e -> e.getRole_id() == roleId);
		employeeRepository.save(employee);
	};
}
