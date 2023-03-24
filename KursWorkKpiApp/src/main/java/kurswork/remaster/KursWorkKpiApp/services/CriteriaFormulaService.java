package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CriteriaFormulaDTO;
import kurswork.remaster.KursWorkKpiApp.model.CriteriaFormula;
@Service
public interface CriteriaFormulaService {
	public CriteriaFormula save(CriteriaFormulaDTO criteriaFormulaDTO);
}
