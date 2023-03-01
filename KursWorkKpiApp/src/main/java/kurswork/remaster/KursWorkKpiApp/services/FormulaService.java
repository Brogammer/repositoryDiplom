package kurswork.remaster.KursWorkKpiApp.services;

import kurswork.remaster.KursWorkKpiApp.dto.FormulaDTO;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Formula;

public interface FormulaService {

	public double evaluateFormula(Formula formula, Employee employee);
	public Formula save(FormulaDTO formulaDTO);
}
