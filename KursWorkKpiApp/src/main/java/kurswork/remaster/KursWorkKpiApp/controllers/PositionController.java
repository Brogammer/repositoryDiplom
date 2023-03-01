package kurswork.remaster.KursWorkKpiApp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kurswork.remaster.KursWorkKpiApp.dto.PositionRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.Department;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.services.DepartmentService;
import kurswork.remaster.KursWorkKpiApp.services.PositionService;

@Controller
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/PositionRegistration")
	public String getPositionRegistration(Model model, HttpSession httpSession) {
		
		Department emptyDepartment = new Department();
		PositionRegistrationDTO emptyDTO = new PositionRegistrationDTO();
		emptyDTO.setDepartment(emptyDepartment);
		model.addAttribute("position", emptyDTO);
		List<Department> departments = departmentService.findAll();
		
		httpSession.setAttribute("departmentList", departments);
		model.addAttribute("departmentList", departments);
		model.addAttribute("success", false);
		return "/DepartmentsAndPositions/PositionRegistration";
	}
	
	@PostMapping("/PositionRegistration")
	public String postPositionRegistration(@ModelAttribute("position")PositionRegistrationDTO positionRegistrationDTO, Model model, HttpSession httpSession) {
		
		Department selectedDepartment = departmentService.findById(positionRegistrationDTO.getDepartment().getDepartment_id());
		positionRegistrationDTO.setDepartment(selectedDepartment);
		positionService.save(positionRegistrationDTO);
		
		
		
		Department emptyDepartment = new Department();
		PositionRegistrationDTO emptyDTO = new PositionRegistrationDTO();
		emptyDTO.setDepartment(emptyDepartment);
		@SuppressWarnings("unchecked")
		List<Department> departments = (List<Department>) httpSession.getAttribute("departmentList");
		model.addAttribute("position", emptyDTO);
		model.addAttribute("departmentList", departments);
		model.addAttribute("success", true);
		return "/DepartmentsAndPositions/PositionRegistration";
	}
	@GetMapping("/PositionSelectionCalif")
	public String getPositionSelection(Model model, HttpSession httpSession) {
		
		Department department = (Department) httpSession.getAttribute("selectedDepartment");
		if (department == null) {
			return "redirect:/DepartmentSelectionCalif";
		}
		
		List<Position> positions = positionService.findAllByDepartmentId(department.getDepartment_id());
		httpSession.setAttribute("positionList", positions);
		model.addAttribute("positionList", positions);
		model.addAttribute("position", new Position());
		model.addAttribute("PostUrl", "/PositionSelectionForCalificat");
		
		httpSession.removeAttribute("selectedDepartment");
		return "/DepartmentsAndPositions/PositionSelection";
	}
	
	@PostMapping("/PositionSelectionForCalificat")
	public String postPositionSelection(@ModelAttribute("position")Position position, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<Position> positions = (List<Position>) httpSession.getAttribute("positionList");
		Position selectedPosition = positions.stream()
				.filter(pos-> pos.getPosition_id() == position.getPosition_id())
				.findAny()
				.orElse(null);
		httpSession.setAttribute("selectedPosition", selectedPosition);
		httpSession.removeAttribute("positionList");
		return "redirect:/DomactSelectionCalif";
	}
	
}
