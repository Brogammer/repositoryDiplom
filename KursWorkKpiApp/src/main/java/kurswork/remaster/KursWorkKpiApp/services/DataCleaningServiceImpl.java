package kurswork.remaster.KursWorkKpiApp.services;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;

@Service
public class DataCleaningServiceImpl implements DataCleaningService {

	@Autowired
	CalculatedDomactService calculatedDomactService;
	@Autowired
	InsertedVariableService insertedVariableService;

	@SuppressWarnings("deprecation")
	@Override
	public void cleanDataExeptLastFiveYears() {

		Date firstDateOfFive = Date.from(Instant.now());
		;
		firstDateOfFive.setYear(firstDateOfFive.getYear() - 5);

		List<CalculatedDomact> deletedCalculatedDomacts = calculatedDomactService.deleteAllByYear(firstDateOfFive);
		List<InsertedVariable> deletedInsertedVariables = insertedVariableService.deleteAllByYear(firstDateOfFive);
	}

}
