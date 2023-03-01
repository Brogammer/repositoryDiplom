package kurswork.remaster.KursWorkKpiApp.services;

import kurswork.remaster.KursWorkKpiApp.dto.FormulaVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.FormulaVariables;

public interface FormulaVariableService {
	public FormulaVariables save (FormulaVariableDTO dto);
}
