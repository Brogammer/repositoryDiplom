package kurswork.remaster.KursWorkKpiApp.services;

import kurswork.remaster.KursWorkKpiApp.dto.CalificatRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Calification;

public interface CalificationService {
	public Calification save (CalificatRegistrationDTO dto);
}
