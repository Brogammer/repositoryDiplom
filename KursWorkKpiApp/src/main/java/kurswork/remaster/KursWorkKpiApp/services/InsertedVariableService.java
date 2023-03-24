package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
@Service
public interface InsertedVariableService {
	public InsertedVariable save (InsertedVariableDTO dto);
}
