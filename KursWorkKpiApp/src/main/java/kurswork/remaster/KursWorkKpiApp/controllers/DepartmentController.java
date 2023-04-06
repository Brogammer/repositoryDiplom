package kurswork.remaster.KursWorkKpiApp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kurswork.remaster.KursWorkKpiApp.dto.DepartmentRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Department;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.services.DepartmentService;
import kurswork.remaster.KursWorkKpiApp.services.PositionService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	PositionService positionService;
	
	
	
	@GetMapping("/DepartmentSelection")
	public String getDepartmentSelection (Model model) {
		
		List<Department> departments = departmentService.findAll()
				.stream()
				.collect(Collectors.toList());
		model.addAttribute("departmentList", departments);
		model.addAttribute("department", new Department());
		model.addAttribute("PostUrl", "/DepartmentSelection");
		
		
		
		return "/DepartmentsAndPositions/DepartmentSelection";
	}
	@GetMapping("/DepartmentSelectionForUpdation")
	public String getDepartmentSelectionForUpdation (Model model) {
		
		List<Department> departments = departmentService.findAll()
				.stream()
				.collect(Collectors.toList());
		model.addAttribute("departmentList", departments);
		model.addAttribute("department", new Department());
		model.addAttribute("PostUrl", "/DepartmentSelectionForUpdation");
		
		
		
		return "/DepartmentsAndPositions/DepartmentSelection";
	}
	
	@PostMapping("/DepartmentSelectionForUpdation")
	public String postDepartmentSelectionForUpdation (@ModelAttribute("department")Department department, Model model, HttpSession httpSession) {
		
		Department selectedDepartment = departmentService.findById(department.getDepartment_id());
		httpSession.setAttribute("selectedDepartment", selectedDepartment);
		
		
		
		return "redirect:/EmployeeUpdation";
	}
	
	@PostMapping("/DepartmentSelection")
	public String postDepartmentSelection (@ModelAttribute("department")Department department, Model model, HttpSession httpSession) {
		
		Department selectedDepartment = departmentService.findById(department.getDepartment_id());
		httpSession.setAttribute("selectedDepartment", selectedDepartment);
		
		
		
		return "redirect:/EmployeeRegistration";
	}
	
	@GetMapping("/DepartmentRegistration")
	public String getDepartmentRegistrationMenu (Model model) {
		
		model.addAttribute("department", new DepartmentRegistrationDTO());
		
		
		model.addAttribute("success", false);
		
		return "/DepartmentsAndPositions/DepartmentRegistration";
	}
	
	@PostMapping("/DepartmentRegistration")
	public String postDepartmentRegistrationMenu (@ModelAttribute("department")DepartmentRegistrationDTO departmentRegistrationDTO, Model model) {
		
		
		departmentService.save(departmentRegistrationDTO);
		model.addAttribute("success", true);
		model.addAttribute("department", new DepartmentRegistrationDTO());
		
		return "/DepartmentsAndPositions/DepartmentRegistration";
	}
	@GetMapping("/DepartmentSelectionCalif")
	public String getDepartmentSelectionCalif(Model model, HttpSession httpSession) {
		List<Department> departments = departmentService.findAll()
				.stream()
				.collect(Collectors.toList());
		
		httpSession.setAttribute("departmentList", departments);
		model.addAttribute("departmentList", departments);
		model.addAttribute("department", new Department());
		model.addAttribute("PostUrl", "/DepartmentSelectionCalif");
		return "/DepartmentsAndPositions/DepartmentSelection";
	}
	@PostMapping("/DepartmentSelectionCalif")
	public String postDepartmentSelectionCalif(@ModelAttribute("department")Department department, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<Department> departments = (List<Department>) httpSession.getAttribute("departmentList");
		Department selectedDepartment = departments.stream()
				.filter(dept->dept.getDepartment_id() == department.getDepartment_id())
				.findAny()
				.orElse(null);
		httpSession.setAttribute("selectedDepartment", selectedDepartment);
		httpSession.removeAttribute("departmentList");
		
		return "redirect:/PositionSelectionCalif";
	}
	
}
