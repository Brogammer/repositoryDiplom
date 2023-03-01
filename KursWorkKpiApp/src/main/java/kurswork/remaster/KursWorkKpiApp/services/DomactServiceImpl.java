package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DomActRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.repositories.DomactRepository;

@Service
public class DomactServiceImpl implements DomactService{

	
	
	@Autowired
	DomactRepository domactRepository;
	
	@Override
	public Domact findById(int id) {
		// TODO Auto-generated method stub
		return domactRepository.findById(id).orElse(null);
	}

	@Override
	public List<Domact> findAll() {
		// TODO Auto-generated method stub
		return domactRepository.findAll();
	}

	@Override
	public Domact save(DomActRegistrationDTO dto) {
		// TODO Auto-generated method stub
		Domact domact = new Domact(0, dto.getDomact_name(), null, null, null);
		return domactRepository.save(domact);
	}

}
