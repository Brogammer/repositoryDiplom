package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CriteriaDTO;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
@Service
public interface CriteriaService {
	public Criteria save(CriteriaDTO dto);
	public Criteria findCriteriaById (int id);
	public List<Criteria> findAll();
}
