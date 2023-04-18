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
	
	@Override
	public List<Position> findAll() {
		return positionRepository.findAll();
	}

	@Override
	public Position findById(int id) {
		return positionRepository.findById(id).orElse(null);
	}

	@Override
	public Position save(PositionRegistrationDTO dto) {
		Position position = new Position(0, dto.getName(), null,  null);
		return positionRepository.save(position);
	}
}
