package kurswork.remaster.KursWorkKpiApp.dto;

import kurswork.remaster.KursWorkKpiApp.model.Department;

public class PositionRegistrationDTO {
	
	private String name;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public PositionRegistrationDTO(String name) {
		super();
		this.name = name;
	}

	public PositionRegistrationDTO() {
		super();
	}
	
	
}
