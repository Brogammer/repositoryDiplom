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
@Table(name = "variables")
public class Variable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int variable_id;
	@Column(name = "variable_sign", nullable = false)
	private String variable_sign;
	@Column(name = "variable_descr", nullable = false,columnDefinition = "TEXT", length = 5000)
	private String variable_descr;
	@Column(name = "variable_restrictions", nullable = true)
	private String restrictions;
	@Column(name = "variable_count", nullable = false)
	private String count;
	
	
	@OneToMany(mappedBy = "variable",fetch = FetchType.EAGER)
	private Set<FormulaVariables> formulaVariables;
	@OneToMany(mappedBy = "variable",fetch = FetchType.EAGER)
	private Set<InsertedVariable> insertedVariables;
	
	public int getVariable_id() {
		return variable_id;
	}
	public void setVariable_id(int variable_id) {
		this.variable_id = variable_id;
	}
	public String getVariable_sign() {
		return variable_sign;
	}
	public void setVariable_sign(String variable_sign) {
		this.variable_sign = variable_sign;
	}
	public String getVariable_descr() {
		return variable_descr;
	}
	public void setVariable_descr(String variable_descr) {
		this.variable_descr = variable_descr;
	}
	public Set<FormulaVariables> getFormulaVariables() {
		return formulaVariables;
	}
	public void setFormulaVariables(Set<FormulaVariables> formulaVariables) {
		this.formulaVariables = formulaVariables;
	}
	public Set<InsertedVariable> getInsertedVariables() {
		return insertedVariables;
	}
	public void setInsertedVariables(Set<InsertedVariable> insertedVariables) {
		this.insertedVariables = insertedVariables;
	}
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Variable() {
		super();
	}
	public String getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	public Variable(int variable_id, String variable_sign, String variable_descr, String restrictions, String count,
			Set<FormulaVariables> formulaVariables, Set<InsertedVariable> insertedVariables) {
		super();
		this.variable_id = variable_id;
		this.variable_sign = variable_sign;
		this.variable_descr = variable_descr;
		this.restrictions = restrictions;
		this.count = count;
		this.formulaVariables = formulaVariables;
		this.insertedVariables = insertedVariables;
	}
	
	
	
}
