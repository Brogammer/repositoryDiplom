package kurswork.remaster.KursWorkKpiApp.services;

import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;
import kurswork.remaster.KursWorkKpiApp.model.Calification;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Subgroup;

@Service
public interface GenerationService {

	public XWPFDocument generateWordFileApachePOI(Map<Domact, List<Subgroup>> mapOfDomactSubgroups,
			Map<Subgroup, List<Criteria>> mapOfSubGroupCriteria, Map<Criteria, Double> mapOfCriteriaResults,
			Map<Criteria, List<InsertedVariableDTO>> mapOfCriteriaVars,
			Map<Domact, CalculatedDomact> mapOfCalculatedDomacts, Employee employee, Double finalResult);

	public XWPFDocument generateRaportAnual(Map<Employee, List<CalculatedDomact>> mapOfEmployeeCalcDomact,
			Map<Employee, Double> mapOfEmployeesFinalResults, java.util.Date chosenDate);

	public XWPFDocument generateLastFiveYearsRaport(
			Map<Domact, Map<java.util.Date, List<CalculatedDomact>>> sortedMapOfLastFiveYearsCalcDom,
			Map<java.util.Date, String> mapOfYearsRange, Map<Domact, Double> mapOfTotalPuncte, Employee employee);
}
