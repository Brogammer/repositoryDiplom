package kurswork.remaster.KursWorkKpiApp.dto;


import kurswork.remaster.KursWorkKpiApp.model.Formula;
import kurswork.remaster.KursWorkKpiApp.model.Variable;

public class FormulaVariableDTO {
	private Variable variable;
	
	private Formula formula;

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public FormulaVariableDTO(Variable variable, Formula formula) {
		super();
		this.variable = variable;
		this.formula = formula;
	}

	public FormulaVariableDTO() {
		super();
	}
	
	
}
