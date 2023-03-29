package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DomactPositionCalifDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.DomactPositionCalif;
import kurswork.remaster.KursWorkKpiApp.model.Position;
@Service
public interface DomactPosCalifService {
	public DomactPositionCalif save (DomactPositionCalifDTO dto);
	public List<DomactPositionCalif> findByPosition(Position position);
}
