package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.model.UserRole;
@Service
public interface UserRoleService {
	public UserRole findUserRoleById(int id);
	public void save(UserRole role);
	public void assignEmployeeRole (Integer userId, Integer roleId);
}
