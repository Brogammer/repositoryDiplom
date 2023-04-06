package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.VariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.Variable;
import kurswork.remaster.KursWorkKpiApp.repositories.VariableRepository;

@Service
public class VariableServiceImpl implements VariableService {
	@Autowired
	VariableRepository variableRepository;

	@Override
	public Variable save(VariableDTO dto) {
		// TODO Auto-generated method stub
		Variable variable = new Variable(0, dto.getVariable_sign().trim(), dto.getVariable_descr().trim(), dto.getRestrictions(), dto.getCount(), null, null);
		return variableRepository.save(variable);
	}
}
