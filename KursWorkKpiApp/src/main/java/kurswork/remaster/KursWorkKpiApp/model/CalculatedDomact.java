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
@Table(name = "calculated_domact")
public class CalculatedDomact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int calcdomact_id;
	
	@Column(name = "domact_calc_datetime", nullable = false)
	private Date domactCalcDate; 
	
	@Column(name = "calculated_value", nullable = false)
	private double calculated_value;
	
	@Column(name = "calculated_calificat", nullable = false)
	private String calculated_calificat;
	
	@Column(name = "norma_stiint_didact", nullable = false)
	private Double normaStiintifica;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "domact_id", nullable = false)
	private Domact domact;

	public int getCalcdomact_id() {
		return calcdomact_id;
	}

	public void setCalcdomact_id(int calcdomact_id) {
		this.calcdomact_id = calcdomact_id;
	}

	public Date getDomactCalcDate() {
		return domactCalcDate;
	}

	public void setDomactCalcDate(Date domactCalcDatetaime) {
		this.domactCalcDate = domactCalcDatetaime;
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

	

	public Double getNormaStiintifica() {
		return normaStiintifica;
	}

	public void setNormaStiintifica(Double normaStiintifica) {
		this.normaStiintifica = normaStiintifica;
	}

	public CalculatedDomact(int calcdomact_id, Date domactCalcDate, double calculated_value,
			String calculated_calificat, Double normaStiintifica, Employee employee, Domact domact) {
		super();
		this.calcdomact_id = calcdomact_id;
		this.domactCalcDate = domactCalcDate;
		this.calculated_value = calculated_value;
		this.calculated_calificat = calculated_calificat;
		this.normaStiintifica = normaStiintifica;
		this.employee = employee;
		this.domact = domact;
	}

	public CalculatedDomact() {
		super();
	}
	
	
	
}
