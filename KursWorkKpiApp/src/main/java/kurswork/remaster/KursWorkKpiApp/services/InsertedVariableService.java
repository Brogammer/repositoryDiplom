package kurswork.remaster.KursWorkKpiApp.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
@Service
public interface InsertedVariableService {
	public InsertedVariable save (InsertedVariableDTO dto);
	public List<InsertedVariable> findByDate(Date date, Employee employee);
	public List<InsertedVariable>  deleteByDate(Date date, Employee employee);
}
