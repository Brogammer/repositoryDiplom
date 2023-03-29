package kurswork.remaster.KursWorkKpiApp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;

public class CalculatedDomactDTO {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date domactCalcDate; 
	
	private double calculated_value;
	
	private String calculated_calificat;
	
	private Employee employee;
	
	private Domact domact;
	
	private Double normaStiintifica;
	
	

	public Double getNormaStiintifica() {
		return normaStiintifica;
	}

	public void setNormaStiintifica(Double normaStiintifica) {
		this.normaStiintifica = normaStiintifica;
	}

	public Date getDomactCalcDate() {
		return domactCalcDate;
	}

	public void setDomactCalcDate(Date domactCalcDate) {
		this.domactCalcDate = domactCalcDate;
	}

	public double getCalculated_value() {
		return calculated_value;
	}

	public void setCalculated_value(double calculated_value) {
		this.calculated_value = calculated_value;
	}

	public String getCalculated_calificat() {
		return calculated_calificat;
	}

	public void setCalculated_calificat(String calculated_calificat) {
		this.calculated_calificat = calculated_calificat;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Domact getDomact() {
		return domact;
	}

	public void setDomact(Domact domact) {
		this.domact = domact;
	}


	public CalculatedDomactDTO(Date domactCalcDate, double calculated_value, String calculated_calificat,
			Employee employee, Domact domact, Double normaStiintifica) {
		super();
		this.domactCalcDate = domactCalcDate;
		this.calculated_value = calculated_value;
		this.calculated_calificat = calculated_calificat;
		this.employee = employee;
		this.domact = domact;
		this.normaStiintifica = normaStiintifica;
	}

	public CalculatedDomactDTO() {
		super();
	}
	
	
}
