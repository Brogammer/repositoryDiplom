package kurswork.remaster.KursWorkKpiApp.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.FormulaDTO;
import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Formula;
@Service
public interface FormulaService {

	public double evaluateFormula(Formula formula, List<List<InsertedVariableDTO>> listsOfInsertedVarDTO);
	public Formula save(FormulaDTO formulaDTO);
	public boolean checkRestrictions(InsertedVariableDTO dto, String insertedValue);
}
