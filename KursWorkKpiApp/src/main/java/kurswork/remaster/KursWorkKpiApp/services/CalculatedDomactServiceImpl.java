package kurswork.remaster.KursWorkKpiApp.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.CalculatedDomactDTO;
import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.repositories.CalculatedDomactRepository;

@Service
public class CalculatedDomactServiceImpl implements CalculatedDomactService {

	@Autowired
	CalculatedDomactRepository calculatedDomactRepository;

	@Override
	public CalculatedDomact save(CalculatedDomactDTO dto) {
		// TODO Auto-generated method stub
		CalculatedDomact calculatedDomact = findCalcDomactByEmployeeDomactDate(dto.getEmployee(),
				dto.getDomactCalcDate(), dto.getDomact());

		if (calculatedDomact == null)
			calculatedDomact = new CalculatedDomact(0, dto.getDomactCalcDate(), dto.getCalculated_value(),
					dto.getCalculated_calificat(), dto.getNormaStiintifica(), dto.getEmployee(), dto.getDomact());
		else {
			calculatedDomact.setCalculated_calificat(dto.getCalculated_calificat());
			calculatedDomact.setCalculated_value(dto.getCalculated_value());
			calculatedDomact.setNormaStiintifica(dto.getNormaStiintifica());
		}

		return calculatedDomactRepository.save(calculatedDomact);
	}

	private CalculatedDomact findCalcDomactByEmployeeDomactDate(Employee employee, Date date, Domact domact) {

		List<CalculatedDomact> calculatedDomacts = calculatedDomactRepository.findAll().stream().distinct()
				.collect(Collectors.toList());
		CalculatedDomact calculatedDomact = calculatedDomacts.stream()
				.filter(calcDom -> calcDom.getDomact().getDomact_id() == domact.getDomact_id()
						&& calcDom.getEmployee().getEmployee_id() == employee.getEmployee_id()
						&& calcDom.getDomactCalcDate().getYear() == date.getYear()
						&& calcDom.getDomactCalcDate().getMonth() == date.getMonth()
						&& calcDom.getDomactCalcDate().getDay() == date.getDay())
				.findAny().orElse(null);

		return calculatedDomact;

	}

	@Override
	public List<CalculatedDomact> findAll() {

		return calculatedDomactRepository.findAll().stream().distinct().collect(Collectors.toList());
	}

}
