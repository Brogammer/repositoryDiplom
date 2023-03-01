package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CalificatRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Calification;
import kurswork.remaster.KursWorkKpiApp.repositories.CalificationRepository;
@Service
public class CalificationServiceImpl implements CalificationService {

	@Autowired
	CalificationRepository calificationRepository;
	@Override
	public Calification save(CalificatRegistrationDTO dto) {
		// TODO Auto-generated method stub
		Calification calification = new Calification(0, dto.getLeft_bound(), dto.getRight_bound(),
				dto.getCalificat_coef(), dto.getCalificat_symbol(), null);
		
		return calificationRepository.save(calification);
	}

}
