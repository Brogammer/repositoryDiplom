package kurswork.remaster.KursWorkKpiApp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
import kurswork.remaster.KursWorkKpiApp.model.Variable;
import kurswork.remaster.KursWorkKpiApp.repositories.InsertedVariableRepository;

@Service
public class InsertedVariableServiceImpl implements InsertedVariableService {

	@Autowired
	InsertedVariableRepository insertedVariableRepository;

	@Override
	public InsertedVariable save(InsertedVariableDTO dto) {

		InsertedVariable insertedVariable; // findInsVarByEmployeeVariableDate(dto.getEmployee(), dto.getDatetime(),
//				dto.getVariable());

//		if (insertedVariable == null)
		insertedVariable = new InsertedVariable(0, dto.getDatetime(), dto.getInserted_value(), dto.getEmployee(),
				dto.getVariable(), dto.getComment(), dto.getIndex());
//		else {
//			insertedVariable.setComment(dto.getComment());
//			insertedVariable.setInserted_value(dto.getInserted_value());
//		}
		return insertedVariableRepository.save(insertedVariable);
	}

//	private InsertedVariable findInsVarByEmployeeVariableDate(Employee employee, Date date, Variable variable, String index) {
//
//		List<InsertedVariable> insertedVariables = insertedVariableRepository.findAll();
//		InsertedVariable insertedVariable = insertedVariables.stream()
//				.filter(insVar -> insVar.getVariable().getVariable_id() == variable.getVariable_id()
//						&& insVar.getEmployee().getEmployee_id() == employee.getEmployee_id()
//						&& insVar.getDatetime().getYear() == date.getYear()
//						&& insVar.getDatetime().getMonth() == date.getMonth()
//						&& insVar.getDatetime().getDay() == date.getDay())
//				.findAny().orElse(null);
//
//		return insertedVariable;
//
//	}

	@Override
	public List<InsertedVariable> findByDate(Date date, Employee employee) {
		// TODO Auto-generated method stub
		List<InsertedVariable> insertedVariables = insertedVariableRepository.findAll().stream().distinct()
				.filter(insVar -> insVar.getEmployee().getEmployee_id() == employee.getEmployee_id()
						&& date.getDay() == insVar.getDatetime().getDay()
						&& date.getMonth() == insVar.getDatetime().getMonth()
						&& date.getYear() == insVar.getDatetime().getYear())
				.collect(Collectors.toList());
		return insertedVariables;
	}

	@Override
	public List<InsertedVariable> deleteByDate(Date date, Employee employee) {
		// TODO Auto-generated method stub
		List<InsertedVariable> insertedVariables = findByDate(date, employee);
		if (insertedVariables != null && insertedVariables.size() > 0) {
			insertedVariables.forEach(insVar -> insertedVariableRepository.deleteById(insVar.getInsVar_id()));
			return insertedVariables;
		}
		return null;
	}

	@Override
	public List<InsertedVariable> findAll() {

		return insertedVariableRepository.findAll();
	}

	@Override
	public List<InsertedVariable> deleteAllByYear(Date date) {
		List<InsertedVariable> deletedInsertedVariables = findAll().stream()
				.filter(insVar -> insVar.getDatetime().getYear() < date.getYear()).collect(Collectors.toList());
		deletedInsertedVariables.forEach(insVar -> insertedVariableRepository.deleteById(insVar.getInsVar_id()));
		return deletedInsertedVariables;
	}
}
