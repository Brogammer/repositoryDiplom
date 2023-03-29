package kurswork.remaster.KursWorkKpiApp.dto;

import java.util.Date;


import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Variable;

public class InsertedVariableDTO {
	private Date datetime;
	private String inserted_value;
	private Employee employee;
	private Variable variable;
	private String index;
	private String comment;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getInserted_value() {
		return inserted_value;
	}
	public void setInserted_value(String inserted_value) {
		this.inserted_value = inserted_value;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public InsertedVariableDTO(Date datetime, String inserted_value, Employee employee, Variable variable, String comment) {
		super();
		this.datetime = datetime;
		this.inserted_value = inserted_value;
		this.employee = employee;
		this.variable = variable;
		this.comment = comment;
		this.index = null;
	}
	public InsertedVariableDTO(Date datetime, String inserted_value, Employee employee, Variable variable, String comment, String index) {
		super();
		this.datetime = datetime;
		this.inserted_value = inserted_value;
		this.employee = employee;
		this.variable = variable;
		this.comment = comment;
		this.index = index;
	}
	public InsertedVariableDTO() {
		super();
	}
	
	
	
}
