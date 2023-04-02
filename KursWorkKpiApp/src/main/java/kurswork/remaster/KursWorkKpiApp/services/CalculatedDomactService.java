package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CalculatedDomactDTO;
import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;

@Service
public interface CalculatedDomactService {
	public CalculatedDomact save (CalculatedDomactDTO dto);
	public List<CalculatedDomact> findAll ();
	public List<CalculatedDomact> findAllByDate(java.util.Date date);
	public List<CalculatedDomact> deleteAllByYear(java.util.Date date);
}
