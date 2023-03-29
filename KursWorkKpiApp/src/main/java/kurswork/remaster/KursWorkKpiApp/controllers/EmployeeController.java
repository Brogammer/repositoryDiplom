package kurswork.remaster.KursWorkKpiApp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.qos.logback.core.filter.Filter;
import kurswork.remaster.KursWorkKpiApp.dto.EmployeeRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.model.Department;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Formula;
import kurswork.remaster.KursWorkKpiApp.model.FormulaVariables;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.model.Subgroup;
import kurswork.remaster.KursWorkKpiApp.model.Variable;
import kurswork.remaster.KursWorkKpiApp.services.CriteriaService;
import kurswork.remaster.KursWorkKpiApp.services.EmployeeService;
import kurswork.remaster.KursWorkKpiApp.services.FormulaService;
import kurswork.remaster.KursWorkKpiApp.services.PositionService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	FormulaService formulaService;
	@Autowired
	PositionService positionService;
	@Autowired
	CriteriaService criteriaService;

	@GetMapping("/EmployeeLogin")
	public String getLoginPage() {

		return "/RegistrationAndLogin/LoginForm";
	}

	@GetMapping("/")
	public String getRedirect(HttpSession httpSession) {

		Iterator<String> iterator = httpSession.getAttributeNames().asIterator();
		for (; iterator.hasNext();) {
			String attrName = iterator.next();

			if (attrName.equals("SPRING_SECURITY_CONTEXT") || attrName.contains("CSRF_TOKEN"))
				continue;
			//System.out.println(attrName);

			httpSession.removeAttribute(attrName);
		}

		return "redirect:/Test";
	}

	@GetMapping("/EmployeeRegistration")
	public String getRegistrationMenu(Model model, HttpSession httpSession) {

		Department selectedDepartment = (Department) httpSession.getAttribute("selectedDepartment");
		if (selectedDepartment == null) {
			return "redirect:/DepartmentSelection";
		}

		List<Position> positions = positionService.findAll();

		EmployeeRegistrationDTO emptyDTO = new EmployeeRegistrationDTO();
		emptyDTO.setPosition(new Position());

		model.addAttribute("employee", emptyDTO);

		httpSession.setAttribute("positionList", positions);
		model.addAttribute("positionList", positions);

		return "RegistrationAndLogin/RegistrationForm";
	}

	@PostMapping("/EmployeeRegistration")
	public String postRegistrationData(@ModelAttribute("employee") EmployeeRegistrationDTO dto, Model model,
			HttpSession httpSession) {

		boolean isLoginIncorrect = employeeService.hasAlreadyLoginExisted(dto.getLogin());

		if (isLoginIncorrect) {
			model.addAttribute("employee", dto);
			model.addAttribute("emplLoginIncorrect", isLoginIncorrect);
			return "RegistrationAndLogin/RegistrationForm";
		} else {
			@SuppressWarnings("unchecked")
			List<Position> positions = (List<Position>) httpSession.getAttribute("positionList");
			Position chosedPosition = positionService.findById(dto.getPosition().getPosition_id());
			dto.setPosition(chosedPosition);
			Department selectedDepartment = (Department) httpSession.getAttribute("selectedDepartment");
			dto.setDepartment(selectedDepartment);
			employeeService.save(dto);
			httpSession.removeAttribute("positionList");
			httpSession.removeAttribute("selectedDepartment");

			return "redirect:/EmployeeLogin?success";
		}

	}

	// TESTTESTEST

	@GetMapping("/Test")
	public String getTest(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		model.addAttribute("userName", authentication.getName());
		model.addAttribute("roles", authentication.getAuthorities());

//		Criteria criteria = criteriaService.findCriteriaById(13);
//		System.out.println("Critera name:\n\t" + criteria.getCriteria_name());
//		System.out.println("Critera decription:\n\t" + criteria.getCriteria_descr());
//		Formula formula = criteria.getCriteriaFormulas().stream().findAny().orElse(null).getFormula();
//		Employee employee = employeeService.findByLogin(authentication.getName());
//
//		Set<InsertedVariable> insVars = employee.getInsertedVariables();
//		Date lastDate = insVars.stream().map(insVar -> insVar.getDatetime())
//				.max((date1, date2) -> date1.compareTo(date2)).orElse(null);
//
//		System.out.println("Name:");
//		System.out.println(employee.getLogin());
//
//		System.out.println("Insertion date:" + lastDate.toString());
//		insVars.removeIf(insVar -> insVar.getDatetime().compareTo(lastDate) != 0);
//		List<Criteria> insertedCriterias = insVars.stream()
//				.flatMap(insVar -> insVar.getVariable().getFormulaVariables().stream())
//				.flatMap(formVar -> formVar.getFormula().getCriteriaFormulas().stream())
//				.map(critForm -> critForm.getCriteria()).distinct().collect(Collectors.toList());
//
//		List<Subgroup> insertedSubgroups = insertedCriterias.stream().map(insCriteria -> insCriteria.getSubgroup())
//				.distinct().collect(Collectors.toList());

		return "/RegistrationAndLogin/Test";
	}

	// TESTTESTEST

	@GetMapping("/UserPage")
	public String getTestUserPage() {
		return "/RegistrationAndLogin/TestUserPage";
	}

	@GetMapping("/AdminPage")
	public String getTestAdminPage() {
		return "/RegistrationAndLogin/TestAdminPage";
	}

	@GetMapping("/ManagerPage")
	public String getTestManagerPage() {
		return "/RegistrationAndLogin/TestManagerPage";
	}

}
