package kurswork.remaster.KursWorkKpiApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "criteria_formula")
public class CriteriaFormula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int crFormula_id;
	
	@ManyToOne
	@JoinColumn(name = "formula_id")
	private Formula formula;
	
	@ManyToOne
	@JoinColumn(name = "criteria_id")
	private Criteria criteria;

	public CriteriaFormula(int crFormula_id, Formula formula, Criteria criteria) {
		super();
		this.crFormula_id = crFormula_id;
		this.formula = formula;
		this.criteria = criteria;
	}

	public int getCrFormula_id() {
		return crFormula_id;
	}

	public void setCrFormula_id(int crFormula_id) {
		this.crFormula_id = crFormula_id;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public CriteriaFormula() {
		super();
	}
	
}
