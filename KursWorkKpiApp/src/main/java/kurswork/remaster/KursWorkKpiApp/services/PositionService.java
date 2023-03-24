package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.PositionRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Position;
@Service
public interface PositionService {
	public List<Position> findAll();
	public Position save(PositionRegistrationDTO dto);
	public Position findById(int id);
}
