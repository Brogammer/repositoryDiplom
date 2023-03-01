package kurswork.remaster.KursWorkKpiApp.services;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;

public interface InsertedVariableService {
	public InsertedVariable save (InsertedVariableDTO dto);
}
