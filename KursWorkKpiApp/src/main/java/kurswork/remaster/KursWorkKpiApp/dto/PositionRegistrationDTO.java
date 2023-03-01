package kurswork.remaster.KursWorkKpiApp.dto;

import kurswork.remaster.KursWorkKpiApp.model.Department;

public class PositionRegistrationDTO {
	
	private String name;
	
	private Department department;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PositionRegistrationDTO(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	public PositionRegistrationDTO() {
		super();
	}
	
	
}
