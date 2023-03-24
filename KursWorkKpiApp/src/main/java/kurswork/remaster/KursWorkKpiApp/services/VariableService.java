package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.VariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.Variable;
@Service
public interface VariableService {
	public Variable save (VariableDTO dto);
}
