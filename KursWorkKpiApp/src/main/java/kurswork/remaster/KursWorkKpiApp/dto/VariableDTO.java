package kurswork.remaster.KursWorkKpiApp.dto;


public class VariableDTO {
	private String variable_sign;
	private String variable_descr;
	private String restrictions;
	private String count;
	public String getVariable_sign() {
		return variable_sign;
	}
	public void setVariable_sign(String variable_sign) {
		this.variable_sign = variable_sign;
	}
	public String getVariable_descr() {
		return variable_descr;
	}
	public void setVariable_descr(String variable_descr) {
		this.variable_descr = variable_descr;
	}
	public String getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
	public VariableDTO(String variable_sign, String variable_descr, String restrictions, String count) {
		super();
		this.variable_sign = variable_sign;
		this.variable_descr = variable_descr;
		this.restrictions = restrictions;
		this.count = count;
	}
	public VariableDTO() {
		super();
	}
	
	
	
}
