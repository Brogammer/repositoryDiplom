package kurswork.remaster.KursWorkKpiApp.dto;


import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.model.Formula;

public class CriteriaFormulaDTO {

	private Formula formula;
	
	private Criteria criteria;

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

	public CriteriaFormulaDTO(Formula formula, Criteria criteria) {
		super();
		this.formula = formula;
		this.criteria = criteria;
	}

	public CriteriaFormulaDTO() {
		super();
	}
	
	
}
