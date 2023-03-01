package kurswork.remaster.KursWorkKpiApp.dto;

public class FormulaDTO {
	private String formula_string;

	public String getFormula_string() {
		return formula_string;
	}

	public void setFormula_string(String formula_string) {
		this.formula_string = formula_string;
	}

	public FormulaDTO(String formula_string) {
		super();
		this.formula_string = formula_string;
	}

	public FormulaDTO() {
		super();
	}
	
}
