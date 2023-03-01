package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import kurswork.remaster.KursWorkKpiApp.dto.PositionRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Position;

public interface PositionService {
	public List<Position> findAll();
	public List<Position> findAllByDepartmentId(int id);
	public Position save(PositionRegistrationDTO dto);
	public Position findById(int id);
}
