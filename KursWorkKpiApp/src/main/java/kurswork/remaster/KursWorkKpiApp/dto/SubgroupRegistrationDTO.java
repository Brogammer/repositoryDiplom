package kurswork.remaster.KursWorkKpiApp.dto;


import kurswork.remaster.KursWorkKpiApp.model.Domact;

public class SubgroupRegistrationDTO {
	private String subgroup_name;
	
	private Domact domact;

	public String getSubgroup_name() {
		return subgroup_name;
	}

	public void setSubgroup_name(String subgroup_name) {
		this.subgroup_name = subgroup_name;
	}

	public Domact getDomact() {
		return domact;
	}

	public void setDomact(Domact domact) {
		this.domact = domact;
	}

	public SubgroupRegistrationDTO(String subgroup_name, Domact domact) {
		super();
		this.subgroup_name = subgroup_name;
		this.domact = domact;
	}

	public SubgroupRegistrationDTO() {
		super();
	}
	
	
}
