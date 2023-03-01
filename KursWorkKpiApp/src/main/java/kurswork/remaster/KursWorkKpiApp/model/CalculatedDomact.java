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
	private Date domactCalcDatetaime;
	
	@Column(name = "calculated_value", nullable = false)
	private double calculated_value;
	
	@Column(name = "calculated_calificat", nullable = false)
	private String calculated_calificat;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "domact_id", nullable = false)
	private Domact domact;
	
	
	
}
