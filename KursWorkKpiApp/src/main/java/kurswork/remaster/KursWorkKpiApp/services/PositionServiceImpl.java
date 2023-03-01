package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.PositionRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.repositories.PositionRepository;

@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	PositionRepository positionRepository;
	@Autowired
	DepartmentService departmentService;
	
	@Override
	public List<Position> findAll() {
		// TODO Auto-generated method stub
		return positionRepository.findAll();
	}

	@Override
	public Position findById(int id) {
		// TODO Auto-generated method stub
		return positionRepository.findById(id).orElse(null);
	}

	@Override
	public List<Position> findAllByDepartmentId(int id) {
		// TODO Auto-generated method stub
		
		return departmentService.findById(id)
				.getPositions()
				.stream()
				.collect(Collectors.toList());
	}

	@Override
	public Position save(PositionRegistrationDTO dto) {
		// TODO Auto-generated method stub
		Position position = new Position(0, dto.getName(), null, dto.getDepartment(), null);
		return positionRepository.save(position);
	}

}
