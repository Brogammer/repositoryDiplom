package kurswork.remaster.KursWorkKpiApp.dto;


public class DomActRegistrationDTO {

	private String domact_name;

	public String getDomact_name() {
		return domact_name;
	}

	public void setDomact_name(String domact_name) {
		this.domact_name = domact_name;
	}

	public DomActRegistrationDTO(String domact_name) {
		super();
		this.domact_name = domact_name;
	}

	public DomActRegistrationDTO() {
		super();
	}
	
}
