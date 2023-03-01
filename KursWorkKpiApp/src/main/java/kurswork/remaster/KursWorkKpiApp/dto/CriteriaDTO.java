package kurswork.remaster.KursWorkKpiApp.dto;


import kurswork.remaster.KursWorkKpiApp.model.Subgroup;

public class CriteriaDTO {
	
	private String criteria_name;
	private String criteria_descr;
	
	private Subgroup subgroup;

	public String getCriteria_name() {
		return criteria_name;
	}

	public void setCriteria_name(String criteria_name) {
		this.criteria_name = criteria_name;
	}

	public String getCriteria_descr() {
		return criteria_descr;
	}

	public void setCriteria_descr(String criteria_descr) {
		this.criteria_descr = criteria_descr;
	}

	public Subgroup getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(Subgroup subgroup) {
		this.subgroup = subgroup;
	}

	public CriteriaDTO(String criteria_name, String criteria_descr, Subgroup subgroup) {
		super();
		this.criteria_name = criteria_name;
		this.criteria_descr = criteria_descr;
		this.subgroup = subgroup;
	}

	public CriteriaDTO() {
		super();
	}
	
	
}
