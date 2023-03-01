package kurswork.remaster.KursWorkKpiApp.services;

import kurswork.remaster.KursWorkKpiApp.model.UserRole;

public interface UserRoleService {
	public UserRole findUserRoleById(int id);
	public void save(UserRole role);
	public void assignEmployeeRole (Integer userId, Integer roleId);
}
