package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.FormulaVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.FormulaVariables;
import kurswork.remaster.KursWorkKpiApp.repositories.FormulaVariableRepository;
@Service
public class FormulaVariableServiceImpl implements FormulaVariableService{

	@Autowired
	FormulaVariableRepository formulaVariableRepository;
	
	@Override
	public FormulaVariables save(FormulaVariableDTO dto) {
		
		FormulaVariables formulaVariables = new FormulaVariables(0, dto.getVariable(), dto.getFormula());
		return formulaVariableRepository.save(formulaVariables);
	}

}
