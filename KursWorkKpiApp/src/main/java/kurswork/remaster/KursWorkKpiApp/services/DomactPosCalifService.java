package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DomactPositionCalifDTO;
import kurswork.remaster.KursWorkKpiApp.model.DomactPositionCalif;
@Service
public interface DomactPosCalifService {
	public DomactPositionCalif save (DomactPositionCalifDTO dto);
}
