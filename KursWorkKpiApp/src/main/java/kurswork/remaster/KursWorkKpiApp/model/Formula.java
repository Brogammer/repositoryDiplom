package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "formulas")
public class Formula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int formula_id;
	
	@Column(name = "formula_string", nullable = false)
	private String formula_string;
	
	@OneToMany(mappedBy = "formula" ,fetch = FetchType.EAGER)
	private Set<CriteriaFormula> CriteriaFormulas;
	@OneToMany(mappedBy = "formula",fetch = FetchType.EAGER)
	private Set<FormulaVariables> formulaVariables;
	public int getFormula_id() {
		return formula_id;
	}
	public void setFormula_id(int formula_id) {
		this.formula_id = formula_id;
	}
	public String getFormula_string() {
		return formula_string;
	}
	public void setFormula_string(String formula_string) {
		this.formula_string = formula_string;
	}
	public Set<CriteriaFormula> getCriteriaFormulas() {
		return CriteriaFormulas;
	}
	public void setCriteriaFormulas(Set<CriteriaFormula> criteriaFormulas) {
		CriteriaFormulas = criteriaFormulas;
	}
	public Set<FormulaVariables> getFormulaVariables() {
		return formulaVariables;
	}
	public void setFormulaVariables(Set<FormulaVariables> formulaVariables) {
		this.formulaVariables = formulaVariables;
	}
	public Formula(int formula_id, String formula_string, Set<CriteriaFormula> criteriaFormulas,
			Set<FormulaVariables> formulaVariables) {
		super();
		this.formula_id = formula_id;
		this.formula_string = formula_string;
		CriteriaFormulas = criteriaFormulas;
		this.formulaVariables = formulaVariables;
	}
	public Formula() {
		super();
	}
	
	
}
