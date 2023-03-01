package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inserted_variables")
public class InsertedVariable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int insVar_id;
	@Column(name = "datetime", nullable = false)
	private Date datetime;
	@Column(name = "inserted_value", nullable = false)
	private String inserted_value;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "variable_id", nullable = false)
	private Variable variable;
	public int getInsVar_id() {
		return insVar_id;
	}
	public void setInsVar_id(int insVar_id) {
		this.insVar_id = insVar_id;
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
	public InsertedVariable(int insVar_id, Date datetime, String inserted_value, Employee employee, Variable variable) {
		super();
		this.insVar_id = insVar_id;
		this.datetime = datetime;
		this.inserted_value = inserted_value;
		this.employee = employee;
		this.variable = variable;
	}
	public InsertedVariable() {
		super();
	}
	
	
	
}

