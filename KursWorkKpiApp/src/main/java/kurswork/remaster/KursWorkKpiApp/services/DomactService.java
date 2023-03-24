package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DomActRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
@Service
public interface DomactService {
	public Domact findById(int id);
	public List<Domact> findAll();
	public Domact save(DomActRegistrationDTO dto);
}
