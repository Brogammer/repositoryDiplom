package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CriteriaDTO;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.repositories.CriteriaRepository;
@Service
public class CriteriaServiceImpl implements CriteriaService{

	@Autowired
	CriteriaRepository criteriaRepository;
	@Override
	public Criteria save(CriteriaDTO dto) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria(0, dto.getCriteria_name(), dto.getCriteria_descr(), dto.getSubgroup(), null, null);
		return criteriaRepository.save(criteria);
	}
	@Override
	public Criteria findCriteriaById(int id) {
		// TODO Auto-generated method stub
		return criteriaRepository.findById(id).orElse(null);
	}
	@Override
	public List<Criteria> findAll() {
		// TODO Auto-generated method stub
		return criteriaRepository.findAll();
	}

}
