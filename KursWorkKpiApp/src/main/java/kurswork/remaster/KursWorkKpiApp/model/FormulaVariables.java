package kurswork.remaster.KursWorkKpiApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formula_variables")
public class FormulaVariables {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int formVar_id;
	
	@ManyToOne
	@JoinColumn(name = "variable_id", nullable = false)
	private Variable variable;
	
	@ManyToOne
	@JoinColumn(name = "formula_id", nullable = false)
	private Formula formula;

	public int getFormVar_id() {
		return formVar_id;
	}

	public void setFormVar_id(int formVar_id) {
		this.formVar_id = formVar_id;
	}

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

	public FormulaVariables(int formVar_id, Variable variable, Formula formula) {
		super();
		this.formVar_id = formVar_id;
		this.variable = variable;
		this.formula = formula;
	}

	public FormulaVariables() {
		super();
	}
	
}
