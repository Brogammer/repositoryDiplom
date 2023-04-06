package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.SubgroupRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Subgroup;
import kurswork.remaster.KursWorkKpiApp.repositories.SubgroupRepository;

@Service
public class SubgroupServiceImpl implements SubgroupService{

	
	@Autowired
	SubgroupRepository subgroupRepository;
	@Autowired
	DomactService domactService;
	@Override
	public Subgroup findById(int id) {
		// TODO Auto-generated method stub
		return subgroupRepository.findById(id).orElse(null);
	}

	@Override
	public List<Subgroup> findAll() {
		// TODO Auto-generated method stub
		return subgroupRepository.findAll().stream().distinct().collect(Collectors.toList());
	}

	@Override
	public Subgroup save(SubgroupRegistrationDTO dto) {
		// TODO Auto-generated method stub
		Subgroup subgroup = new Subgroup(0, dto.getSubgroup_name(), dto.getDomact(), null);
		return subgroupRepository.save(subgroup);
	}

	@Override
	public List<Subgroup> findAllByDomact(Domact domact) {
		// TODO Auto-generated method stub
		return domactService.findById(domact.getDomact_id()).getSubgroups().stream().distinct().collect(Collectors.toList());
	}

}
