package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.SubgroupRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Subgroup;
@Service
public interface SubgroupService {
	public Subgroup findById(int id);
	public List<Subgroup> findAll();
	public List<Subgroup> findAllByDomact(Domact domact);
	public Subgroup save(SubgroupRegistrationDTO dto);
	
}
