package kurswork.remaster.KursWorkKpiApp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kurswork.remaster.KursWorkKpiApp.dto.EmployeeRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.model.Department;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Formula;
import kurswork.remaster.KursWorkKpiApp.model.FormulaVariables;
import kurswork.remaster.KursWorkKpiApp.model.Position;
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
	public String getRedirect() {
		return "redirect:/Test";
	}
	
	@GetMapping("/EmployeeRegistration")
	public String getRegistrationMenu (Model model, HttpSession httpSession) {
		
		Department selectedDepartment = (Department) httpSession.getAttribute("selectedDepartment");
		if (selectedDepartment == null) {
			return "redirect:/DepartmentSelection";
		}
		
		
		List<Position> positions = positionService.findAllByDepartmentId(selectedDepartment.getDepartment_id());
		
		
		EmployeeRegistrationDTO emptyDTO = new EmployeeRegistrationDTO();
		emptyDTO.setPosition(new Position());
		
		model.addAttribute("employee", emptyDTO);
		
		httpSession.setAttribute("positionList", positions);
		model.addAttribute("positionList", positions);
		
		return "RegistrationAndLogin/RegistrationForm";
	}
	
	@PostMapping("/EmployeeRegistration")
	public String postRegistrationData (@ModelAttribute("employee") EmployeeRegistrationDTO dto, Model model, HttpSession httpSession ) {
		
		boolean isLoginIncorrect = employeeService.hasAlreadyLoginExisted(dto.getLogin());
		
		
		if (isLoginIncorrect) {
			model.addAttribute("employee", dto);
			model.addAttribute("emplLoginIncorrect", isLoginIncorrect);			
			return "RegistrationAndLogin/RegistrationForm";
		}
		else {
			@SuppressWarnings("unchecked")
			List<Position> positions = (List<Position>) httpSession.getAttribute("positionList");
			Position chosedPosition = positionService.findById(dto.getPosition().getPosition_id());
			dto.setPosition(chosedPosition);
			
			employeeService.save(dto);
			httpSession.removeAttribute("positionList");
			httpSession.removeAttribute("selectedDepartment");
			
			return "redirect:/EmployeeLogin?success";
		}
		
	}	
	
	
	//TESTTESTEST
	
	@GetMapping("/Test")
	public String getTest(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("roles", authentication.getAuthorities());
		
		Criteria criteria = criteriaService.findCriteriaById(13);
		System.out.println("Critera name:\n\t" + criteria.getCriteria_name());
		System.out.println("Critera decription:\n\t" + criteria.getCriteria_descr());
		Formula formula = criteria.getCriteriaFormulas()
				.stream()
				.findAny()
				.orElse(null)
				.getFormula();
		Employee employee = employeeService.findByLogin(authentication.getName());
		double result = formulaService.evaluateFormula(formula, employee);
		System.out.println(result);
		
		
		return "/RegistrationAndLogin/Test";
	}
	
	//TESTTESTEST
	
	
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
