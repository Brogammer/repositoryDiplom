package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CalificatRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Calification;
@Service
public interface CalificationService {
	public Calification save (CalificatRegistrationDTO dto);
}
