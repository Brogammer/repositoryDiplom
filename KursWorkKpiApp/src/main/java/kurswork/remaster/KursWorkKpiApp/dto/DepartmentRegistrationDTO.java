package kurswork.remaster.KursWorkKpiApp.dto;

public class DepartmentRegistrationDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DepartmentRegistrationDTO(String name) {
		super();
		this.name = name;
	}

	public DepartmentRegistrationDTO() {
		super();
	}
	
}
