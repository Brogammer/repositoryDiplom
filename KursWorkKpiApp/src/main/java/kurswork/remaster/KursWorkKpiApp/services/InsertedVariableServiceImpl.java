package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
import kurswork.remaster.KursWorkKpiApp.repositories.InsertedVariableRepository;

@Service
public class InsertedVariableServiceImpl implements InsertedVariableService{

	
	
	@Autowired
	InsertedVariableRepository insertedVariableRepository;
	
	
	@Override
	public InsertedVariable save(InsertedVariableDTO dto) {
		InsertedVariable insertedVariable = new InsertedVariable(0, dto.getDatetime(), dto.getInserted_value(), 
				dto.getEmployee(), dto.getVariable());
		return insertedVariableRepository.save(insertedVariable);
	}

}
