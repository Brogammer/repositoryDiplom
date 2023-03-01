package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CriteriaFormulaDTO;
import kurswork.remaster.KursWorkKpiApp.model.CriteriaFormula;
import kurswork.remaster.KursWorkKpiApp.repositories.CriteriaFormulaRepository;
@Service
public class CriteriaFormulaServiceImpl implements CriteriaFormulaService{

	@Autowired
	CriteriaFormulaRepository criteriaFormulaRepository;
	
	@Override
	public CriteriaFormula save(CriteriaFormulaDTO criteriaFormulaDTO) {
		// TODO Auto-generated method stub
		
		CriteriaFormula criteriaFormula = new CriteriaFormula(0, criteriaFormulaDTO.getFormula(), criteriaFormulaDTO.getCriteria());
		return criteriaFormulaRepository.save(criteriaFormula);
	}

}
