package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import kurswork.remaster.KursWorkKpiApp.dto.DomActRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;

public interface DomactService {
	public Domact findById(int id);
	public List<Domact> findAll();
	public Domact save(DomActRegistrationDTO dto);
}
