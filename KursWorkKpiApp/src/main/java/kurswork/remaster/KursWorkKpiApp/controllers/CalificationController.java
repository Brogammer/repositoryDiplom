package kurswork.remaster.KursWorkKpiApp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kurswork.remaster.KursWorkKpiApp.dto.CalificatRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.dto.DomactPositionCalifDTO;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.services.CalificationService;
import kurswork.remaster.KursWorkKpiApp.services.DomactPosCalifService;

@Controller
public class CalificationController {
	
	@Autowired
	DomactPosCalifService domactPosCalifService;
	@Autowired
	CalificationService calificationService;
	
	
	@GetMapping("/CalificationList")
	public String getCalificationList (Model model, HttpSession httpSession) {
		Position position = (Position) httpSession.getAttribute("selectedPosition");
		Domact domact = (Domact) httpSession.getAttribute("selectedDomact");
		if (position == null || domact == null) {
			return "redirect:/PositionSelectionCalif";
		}
		
		model.addAttribute("domact", domact);
		model.addAttribute("position", position);
		@SuppressWarnings("unchecked")
		List<CalificatRegistrationDTO> dtos = (List<CalificatRegistrationDTO>) httpSession.getAttribute("calificatDTOList");
		model.addAttribute("calificatDTOList", dtos);
		
		
		return "/Califications/CalificationList";
	}
	
	@GetMapping("/CalificationRegistration")
	public String getCalificationRegistration (Model model, HttpSession httpSession) {
		Position position = (Position) httpSession.getAttribute("selectedPosition");
		Domact domact = (Domact) httpSession.getAttribute("selectedDomact");
		if (position == null || domact == null) {
			return "redirect:/CalificationList";
		}
		model.addAttribute("calificationDTO", new CalificatRegistrationDTO());
		model.addAttribute("wrongBounds", false);
		model.addAttribute("BoundError", false);
		
		return "/Califications/CalificationRegistration";
	}
	
	private boolean AreSeomeWrongBounds(List<CalificatRegistrationDTO> dtos, CalificatRegistrationDTO dto) {
		long wrongBoundsCount = dtos.stream().filter(e->((e.getLeft_bound() <= dto.getRight_bound() && e.getRight_bound() >= dto.getRight_bound())
				|| (e.getLeft_bound() <= dto.getLeft_bound() && e.getRight_bound() >= dto.getLeft_bound()))
				|| ((dto.getLeft_bound() <= e.getRight_bound() && dto.getRight_bound() >= e.getRight_bound())
						|| (dto.getLeft_bound() <= e.getLeft_bound() && dto.getRight_bound() >= e.getLeft_bound()))
				).count();
		
		if(wrongBoundsCount > 0) 
			return true;
		else
			return false;
		
	}
	
	@PostMapping("/CalificationRegistration")
	public String postCalificationRegistration (@ModelAttribute("calificationDTO") CalificatRegistrationDTO dto, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<CalificatRegistrationDTO> dtos = (List<CalificatRegistrationDTO>) httpSession.getAttribute("calificatDTOList");
		if (dto.getLeft_bound() >= dto.getRight_bound()) {
			model.addAttribute("calificationDTO", dto);
			model.addAttribute("wrongBounds", true);
			model.addAttribute("BoundError", false);
			return "/Califications/CalificationRegistration";
		}
		if (AreSeomeWrongBounds(dtos,dto)) {
			model.addAttribute("calificationDTO", dto);
			model.addAttribute("wrongBounds", false);
			model.addAttribute("BoundError", true);
			return "/Califications/CalificationRegistration";
		}
		dtos.add(dto);
		return "redirect:/CalificationList";
	}
	
	@GetMapping("/CloseCoefAddition")
	public String closeCoefAddition() {
		return "redirect:/CalificationList";
	}
	@PostMapping("/DeleteCalificatFromList/{id}")
	public String deleteCoefFromlist(@PathVariable("id") int id, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<CalificatRegistrationDTO> dtos = (List<CalificatRegistrationDTO>) httpSession.getAttribute("calificatDTOList");
		dtos.remove(id);
		return "redirect:/CalificationList";
	}
	@GetMapping("/CalificationUpdation/{id}")
	public String getCalificationUpdation(@PathVariable("id") int id, Model model, HttpSession httpSession) {
		
		Position position = (Position) httpSession.getAttribute("selectedPosition");
		Domact domact = (Domact) httpSession.getAttribute("selectedDomact");
		if (position == null || domact == null) {
			return "redirect:/CalificationList";
		}
		@SuppressWarnings("unchecked")
		List<CalificatRegistrationDTO> dtos = (List<CalificatRegistrationDTO>) httpSession.getAttribute("calificatDTOList");
		
		model.addAttribute("calificationDTO", dtos.get(id));
		model.addAttribute("wrongBounds", false);
		model.addAttribute("BoundError", false);
		model.addAttribute("califId", id);
		
		
		return "/Califications/CalificationUpdation";
		
	}
	
	@PostMapping("/CalificationUpdation/{id}")
	public String postCalificationUpdation (@PathVariable("id")int id, @ModelAttribute("calificationDTO") CalificatRegistrationDTO dto, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<CalificatRegistrationDTO> dtos = (List<CalificatRegistrationDTO>) httpSession.getAttribute("calificatDTOList");
		List<CalificatRegistrationDTO> dtosWithoutChangedElem = new ArrayList<>();
		for (int i = 0; i < dtos.size(); i++) {
			if (i != id)
				dtosWithoutChangedElem.add(dtos.get(i));
		}
		if (dto.getLeft_bound() >= dto.getRight_bound()) {
			model.addAttribute("calificationDTO", dto);
			model.addAttribute("wrongBounds", true);
			model.addAttribute("BoundError", false);
			model.addAttribute("califId", id);
			return "/Califications/CalificationUpdation";
		}
		if (AreSeomeWrongBounds(dtosWithoutChangedElem,dto)) {
			model.addAttribute("calificationDTO", dto);
			model.addAttribute("wrongBounds", false);
			model.addAttribute("BoundError", true);
			model.addAttribute("califId", id);
			return "/Califications/CalificationUpdation";
		}
		CalificatRegistrationDTO DTOtoUpdate = dtos.get(id);
		DTOtoUpdate.setCalificat_coef(dto.getCalificat_coef());
		DTOtoUpdate.setCalificat_symbol(dto.getCalificat_symbol());
		DTOtoUpdate.setLeft_bound(dto.getLeft_bound());
		DTOtoUpdate.setRight_bound(dto.getRight_bound());
		
		return "redirect:/CalificationList";
	}
	
	@PostMapping("/CalifListReg")
	public String califRegistration (Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<CalificatRegistrationDTO> califDtos = (List<CalificatRegistrationDTO>) httpSession.getAttribute("calificatDTOList");
		Position position = (Position) httpSession.getAttribute("selectedPosition");
		Domact domact = (Domact) httpSession.getAttribute("selectedDomact");
		califDtos.stream()
		.map(califDto->calificationService.save(califDto))
		.map(calif->new DomactPositionCalifDTO(calif, domact, position))
		.forEach(DPC->domactPosCalifService.save(DPC));
		
		httpSession.removeAttribute("calificatDTOList");
		httpSession.removeAttribute("selectedPosition");
		httpSession.removeAttribute("selectedDomact");
		
		return "redirect:/PositionSelectionCalif";
		
	}
}
