package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.DomactPositionCalifDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.DomactPositionCalif;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.repositories.DomactPosCalifRepository;

@Service
public class DomactPosCalifServiceImpl implements DomactPosCalifService {

	@Autowired
	DomactPosCalifRepository domactPosCalifRepository;

	@Override
	public DomactPositionCalif save(DomactPositionCalifDTO dto) {
		// TODO Auto-generated method stub
		DomactPositionCalif domactPositionCalif = new DomactPositionCalif(0, dto.getCalification(), dto.getDomact(),
				dto.getPosition());
		return domactPosCalifRepository.save(domactPositionCalif);
	}

	@Override
	public List<DomactPositionCalif> findByPosition(Position position) {
		List<DomactPositionCalif> resultList = domactPosCalifRepository.findAll().stream()
				.filter(dpc -> dpc.getPosition().getPosition_id() == position.getPosition_id())
				.collect(Collectors.toList());
		return resultList;
	}

}
