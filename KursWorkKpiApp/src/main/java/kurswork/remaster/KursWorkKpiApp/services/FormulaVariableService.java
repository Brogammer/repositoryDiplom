package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.FormulaVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.FormulaVariables;
@Service
public interface FormulaVariableService {
	public FormulaVariables save (FormulaVariableDTO dto);
}
