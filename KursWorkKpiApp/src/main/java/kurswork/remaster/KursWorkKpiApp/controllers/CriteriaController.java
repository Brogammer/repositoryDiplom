package kurswork.remaster.KursWorkKpiApp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import kurswork.remaster.KursWorkKpiApp.dto.CalificatRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.dto.CriteriaDTO;
import kurswork.remaster.KursWorkKpiApp.dto.CriteriaFormulaDTO;
import kurswork.remaster.KursWorkKpiApp.dto.DomActRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.dto.FormulaDTO;
import kurswork.remaster.KursWorkKpiApp.dto.FormulaVariableDTO;
import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.dto.SearchSettingsDTO;
import kurswork.remaster.KursWorkKpiApp.dto.SubgroupRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.dto.VariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Formula;
import kurswork.remaster.KursWorkKpiApp.model.Position;
import kurswork.remaster.KursWorkKpiApp.model.Subgroup;
import kurswork.remaster.KursWorkKpiApp.services.CriteriaFormulaService;
import kurswork.remaster.KursWorkKpiApp.services.CriteriaService;
import kurswork.remaster.KursWorkKpiApp.services.DomactService;
import kurswork.remaster.KursWorkKpiApp.services.EmployeeService;
import kurswork.remaster.KursWorkKpiApp.services.FormulaService;
import kurswork.remaster.KursWorkKpiApp.services.FormulaVariableService;
import kurswork.remaster.KursWorkKpiApp.services.SubgroupService;
import kurswork.remaster.KursWorkKpiApp.services.VariableService;

@Controller
public class CriteriaController {
	
	@Autowired
	DomactService domactService;
	@Autowired
	SubgroupService subgroupService;
	@Autowired
	CriteriaService criteriaService;
	@Autowired
	FormulaService formulaService;
	@Autowired
	VariableService variableService;
	@Autowired
	FormulaVariableService formulaVariableService;
	@Autowired
	CriteriaFormulaService criteriaFormulaService;
	@Autowired
	EmployeeService employeeService;
	
	
	
	@GetMapping("/DomactRegistration")
	public String getDomactRegistration(Model model) {
		
		model.addAttribute("domact", new DomActRegistrationDTO());
		return "/CriteriaPages/DomactAndSubgroups/DomactRegistration";
	}
	
	@PostMapping("/DomactRegistration")
	public String postDomactRegistration(@ModelAttribute("domact")DomActRegistrationDTO dto,  Model model, HttpSession httpSession) {
		
		httpSession.setAttribute("domactDTO", dto);
		List<SubgroupRegistrationDTO> subgroups = new ArrayList<>();
		httpSession.setAttribute("subgroupDTOList", subgroups);
		
		return "redirect:/SubgroupList";
		
	
	}
	
	@GetMapping("/SubgroupList")
	public String getSubgroupList( Model model, HttpSession httpSession) {
		
		DomActRegistrationDTO domActRegistrationDTO = (DomActRegistrationDTO)httpSession.getAttribute("domactDTO");
		if (domActRegistrationDTO == null) {
			return "redirect:/DomactRegistration";
		}
		
		@SuppressWarnings("unchecked")
		List<SubgroupRegistrationDTO> subgroups = (List<SubgroupRegistrationDTO>) httpSession.getAttribute("subgroupDTOList");
		model.addAttribute("subgroupDTOList", subgroups);
		model.addAttribute("domactDTO", domActRegistrationDTO);
		
		
		return "/CriteriaPages/DomactAndSubgroups/SubgroupList";
		
	
	}
	
	@GetMapping("/CloseSubgroupForm")
	public String closeForm(Model model, HttpSession HttpSession) {		
		return "redirect:/SubgroupList";
	}
	
	@GetMapping("/SubgroupRegistration")
	public String getSubgroupRegMenu (Model model) {
		
		model.addAttribute("subgroup", new SubgroupRegistrationDTO());
		return "/CriteriaPages/DomactAndSubgroups/SubgroupRegistration";
	}
	@PostMapping("/AddSubgroupInDomact")
	public String addSubgroupInList(@ModelAttribute("subgroup") SubgroupRegistrationDTO dto, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<SubgroupRegistrationDTO> subgroups = (List<SubgroupRegistrationDTO>) httpSession.getAttribute("subgroupDTOList");
		subgroups.add(dto);
		
		return "redirect:/SubgroupList";
	}
	@PostMapping("/DeleteSubgroupsFromList/{id}")
	public String deleteSubgroupFromList(@PathVariable("id") int id, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<SubgroupRegistrationDTO> subgroups = (List<SubgroupRegistrationDTO>) httpSession.getAttribute("subgroupDTOList");
		subgroups.remove(id);
		
		
		return "redirect:/SubgroupList";
	}
	
	@GetMapping("/UpadateSubgroupsFromList/{id}")
	public String getUpdateMenu(@PathVariable("id") int id, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<SubgroupRegistrationDTO> subgroups = (List<SubgroupRegistrationDTO>) httpSession.getAttribute("subgroupDTOList");
		model.addAttribute("subgroup", subgroups.get(id));
		model.addAttribute("id", id);
		
		
		return "/CriteriaPages/DomactAndSubgroups/UpdateSubgroupInList";
	}
	@PostMapping("/UpadateSubgroupsFromList/{id}")
	public String postUpdateMenu(@PathVariable("id") int id,@ModelAttribute("subgroup")SubgroupRegistrationDTO dto, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<SubgroupRegistrationDTO> subgroups = (List<SubgroupRegistrationDTO>) httpSession.getAttribute("subgroupDTOList");
		subgroups.get(id).setSubgroup_name(dto.getSubgroup_name());

		return "redirect:/SubgroupList";
	}
	@PostMapping("/DomactReg")
	public String domactRegistration(Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<SubgroupRegistrationDTO> subgroups = (List<SubgroupRegistrationDTO>) httpSession.getAttribute("subgroupDTOList");
		if(subgroups.size() > 0) {
			
			DomActRegistrationDTO domactDTO = (DomActRegistrationDTO) httpSession.getAttribute("domactDTO");
			Domact domact = domactService.save(domactDTO);
			subgroups.stream().forEach(subgroupDTO->{
				subgroupDTO.setDomact(domact);
				subgroupService.save(subgroupDTO);
			});
			
			httpSession.removeAttribute("domactDTO");
			httpSession.removeAttribute("subgroupDTOList");
			return "redirect:/DomactRegistration";
		} else {
			
			return "redirect:/SubgroupList";
		}
		
	}
	
	@GetMapping("/DomactSelectionCalif")
	public String getDomactSelectionCalif(Model model, HttpSession httpSession) {
		
		Position selectedPosition = (Position) httpSession.getAttribute("selectedPosition");
		if (selectedPosition == null) {
			return "redirect:/PositionSelectionCalif";
		}
		
		List<Domact> domacts = domactService.findAll();
		
		
		model.addAttribute("PostUrl", "/DomactSelectionCalif");
		model.addAttribute("domact", new Domact());
		
		httpSession.setAttribute("domactList", domacts);
		model.addAttribute("domactList", domacts);
		
		return "/CriteriaPages/DomactAndSubgroups/DomactSelection";
		
	}
	@PostMapping("/DomactSelectionCalif")
	public String postDomactSelectionCalif(@ModelAttribute("domact") Domact domact, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<Domact> domacts = (List<Domact>) httpSession.getAttribute("domactList");
		Domact selectedDomact = domacts.stream()
				.filter(domct->domct.getDomact_id() == domact.getDomact_id())
				.findAny()
				.orElse(null);
		httpSession.setAttribute("selectedDomact", selectedDomact);
		httpSession.removeAttribute("domactList");

		List<CalificatRegistrationDTO> dtos = new ArrayList<>();
		httpSession.setAttribute("calificatDTOList", dtos);
		return "redirect:/CalificationList";
		
	}
	
	@GetMapping("/DomactSelectionCriteria")
	public String getDomactSelectionCriteria(Model model, HttpSession httpSession) {
		
		List<Domact> domacts = domactService.findAll();
		httpSession.setAttribute("domactList", domacts);
		model.addAttribute("domactList", domacts);
		model.addAttribute("domact", new Domact());
		model.addAttribute("PostUrl", "/DomactSelectionCriteria");
		
		return "/CriteriaPages/DomactAndSubgroups/DomactSelection";
	}
	
	@PostMapping("/DomactSelectionCriteria")
	public String postDomactSelectionCriteria(@ModelAttribute("domact")Domact domact,Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<Domact> domacts = (List<Domact>) httpSession.getAttribute("domactList");
		Domact selectedDomact = domacts.stream()
				.filter(elem -> elem.getDomact_id() == domact.getDomact_id())
				.findAny()
				.orElse(null);
		httpSession.setAttribute("selectedDomact", selectedDomact);
		httpSession.removeAttribute("domactList");
		
		return "redirect:/SubgroupSelectionCriteria";
	}
	
	@GetMapping("/SubgroupSelectionCriteria")
	public String getSubgroupSelectionCriteria(Model model, HttpSession httpSession) {
		
		
		Domact selectedDomact = (Domact) httpSession.getAttribute("selectedDomact");
		
		if (selectedDomact == null) {
			return "redirect:/DomactSelectionCriteria";
		}
		
		List<Subgroup> subgroups = subgroupService.findAllByDomact(selectedDomact);
		httpSession.setAttribute("subgroupList", subgroups);
		model.addAttribute("subgroupList", subgroups);
		model.addAttribute("subgroup", new Subgroup());
		model.addAttribute("PostUrl", "/SubgroupSelectionCriteria");
		httpSession.removeAttribute("selectedDomact");
		
		
		return "/CriteriaPages/DomactAndSubgroups/SubgroupSelection";
	}
	
	@PostMapping("/SubgroupSelectionCriteria")
	public String postSubgroupSelectionCriteria(@ModelAttribute("subgroup")Subgroup subgroup,Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<Subgroup> subgroups = (List<Subgroup>) httpSession.getAttribute("subgroupList");
		Subgroup selectedSubgroup = subgroups.stream()
				.filter(elem -> elem.getSubgroup_id() == subgroup.getSubgroup_id())
				.findAny()
				.orElse(null);
		httpSession.setAttribute("selectedSubgroup", selectedSubgroup);
		httpSession.removeAttribute("subgroupList");
		
		return "redirect:/CriteriaRegistration";
	}
	@GetMapping("/CriteriaRegistration")
	public String getCriteriaRegistration(Model model, HttpSession httpSession) {
		Subgroup selectedSubgroup = (Subgroup) httpSession.getAttribute("selectedSubgroup");
		
		if (selectedSubgroup == null) {
			return "redirect:/DomactSelectionCriteria";
		}
		model.addAttribute("criteriaDTO", new CriteriaDTO());
		return "/CriteriaPages/CriteriaRegistration";
	}
	
	@PostMapping("/CriteriaRegistration")
	public String postCriteriaRegistration(@ModelAttribute("criteriaDTO") CriteriaDTO criteriaDTO ,Model model, HttpSession httpSession) {
		Subgroup selectedSubgroup = (Subgroup) httpSession.getAttribute("selectedSubgroup");
		criteriaDTO.setSubgroup(selectedSubgroup);
		httpSession.setAttribute("criteriaDTO", criteriaDTO);
		httpSession.removeAttribute("selectedSubgroup");

		List<VariableDTO> variableDTOs = new ArrayList<>();
		httpSession.setAttribute("variableDTOList", variableDTOs);
		httpSession.setAttribute("formulaDTO", new FormulaDTO());
		return "redirect:/FormulaCreation";
	}
	@GetMapping("/FormulaCreation")
	public String getFormulaCreation(Model model, HttpSession httpSession) {
		
		CriteriaDTO criteriaDTO = (CriteriaDTO) httpSession.getAttribute("criteriaDTO");
		if (criteriaDTO == null) {
			return "redirect:/CriteriaRegistration";
		}
		FormulaDTO formulaDTO = (FormulaDTO) httpSession.getAttribute("formulaDTO");
		model.addAttribute("formulaDTO", formulaDTO);
		
		return "/CriteriaPages/FormulaRegistration/FormulaCreation";
	}
	@PostMapping("/DefineVariables")
	public String getVarRegistration(@ModelAttribute("formulaDTO") FormulaDTO formulaDTO, Model model, HttpSession httpSession) {
		httpSession.setAttribute("formulaDTO", formulaDTO);
		
		
		
		return "redirect:/VariableList";
	}
	
	@GetMapping("/VariableList")
	public String getVariableList(Model model, HttpSession httpSession) {
		
		FormulaDTO formulaDTO = (FormulaDTO) httpSession.getAttribute("formulaDTO");
		if (formulaDTO == null) {
			return "redirect:/FormulaCreation";
		}
		
		@SuppressWarnings("unchecked")
		List<VariableDTO> variableDTOs = (List<VariableDTO>) httpSession.getAttribute("variableDTOList");
		model.addAttribute("variableDTOList", variableDTOs);
		model.addAttribute("formulaDTO", formulaDTO);
		return "/CriteriaPages/VariablePages/VariableList";
		
	}
	@GetMapping("/BackToFormulaRegistration")
	public String backToFormulaRegistration() {
		return "redirect:/FormulaCreation";
	}
	
	
	@GetMapping("/VariableRegistration")
	public String getVariableRegistration( Model model, HttpSession httpSession) {
		FormulaDTO formulaDTO = (FormulaDTO) httpSession.getAttribute("formulaDTO");
		if (formulaDTO == null) {
			return "redirect:/FormulaCreation";
		}
		
		
		
		
		model.addAttribute("variableDTO", new VariableDTO());
		return "/CriteriaPages/VariablePages/VariableRegistration";
	}
	
	@PostMapping("/VariableRegistration")
	public String postVariableRegistration(@ModelAttribute("variableDTO") VariableDTO dto, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<VariableDTO> variableDTOs = (List<VariableDTO>) httpSession.getAttribute("variableDTOList");
		variableDTOs.add(dto);
		return "redirect:/VariableList";
	}
	@GetMapping("/CloseVariableForm")
	public String CloseVariableForm() {
		return "redirect:/VariableList";
	}
	@GetMapping("/DeleteVariableFromList/{id}")
	public String deleteFromListVariable(@PathVariable("id")int id,Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<VariableDTO> variableDTOs = (List<VariableDTO>) httpSession.getAttribute("variableDTOList");
		variableDTOs.remove(id);
		
		
		return "redirect:/VariableList";
	}
	@GetMapping("/UpadateVariablesFromList/{id}")
	public String getUpdateVariableFormList(@PathVariable("id")int id, Model model, HttpSession httpSession) {
		
		FormulaDTO formulaDTO = (FormulaDTO) httpSession.getAttribute("formulaDTO");
		if (formulaDTO == null) {
			return "redirect:/FormulaCreation";
		}
		
		
		@SuppressWarnings("unchecked")
		List<VariableDTO> variableDTOs = (List<VariableDTO>) httpSession.getAttribute("variableDTOList");
		model.addAttribute("variableDTO", variableDTOs.get(id));
		model.addAttribute("idElem", id);
		return "/CriteriaPages/VariablePages/VariableUpdation";
		
	}
	@PostMapping("/UpadateVariablesFromList/{id}")
	public String postUpdateVariableFormList(@ModelAttribute("variableDTO")VariableDTO variableDTO, @PathVariable("id")int id, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<VariableDTO> variableDTOs = (List<VariableDTO>) httpSession.getAttribute("variableDTOList");
		variableDTOs.get(id).setVariable_sign(variableDTO.getVariable_sign());
		variableDTOs.get(id).setVariable_descr(variableDTO.getVariable_descr());
		variableDTOs.get(id).setRestrictions(variableDTO.getRestrictions());
		
		
		return "redirect:/VariableList";
		
	}
	@PostMapping("/CreateCriteria")
	public String createCriteria( Model model, HttpSession httpSession) {
		
		
		FormulaDTO formulaDTO = (FormulaDTO) httpSession.getAttribute("formulaDTO");
		CriteriaDTO criteriaDTO = (CriteriaDTO) httpSession.getAttribute("criteriaDTO");
		@SuppressWarnings("unchecked")
		List<VariableDTO> variableDTOs = (List<VariableDTO>) httpSession.getAttribute("variableDTOList");
		Criteria criteria = criteriaService.save(criteriaDTO);
		Formula formula = formulaService.save(formulaDTO);
		CriteriaFormulaDTO criteriaFormulaDTO = new CriteriaFormulaDTO(formula, criteria);
		criteriaFormulaService.save(criteriaFormulaDTO);
		variableDTOs.stream()
		.map(varDto->variableService.save(varDto))
		.map(variable->new FormulaVariableDTO(variable, formula))
		.forEach(fvDTO->formulaVariableService.save(fvDTO));
		httpSession.removeAttribute("formulaDTO");
		httpSession.removeAttribute("criteriaDTO");
		httpSession.removeAttribute("variableDTOList");
		return "redirect:/CriteriaRegistration";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/CriteriaSelectionForInsert")
	public String getCriteriaInsertion(Model model, HttpSession httpSession) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Employee employee = employeeService.findByLogin(authentication.getName());
		List<Domact> domacts;
		
		domacts = (List<Domact>) httpSession.getAttribute("domacts");
		
		if (domacts == null) {
			domacts = employee.getPosition().getDPCs().stream()
					.map(dpc->dpc.getDomact())
					.distinct()
					.sorted((dmct1,dmct2)->dmct1.getDomact_id() - dmct2.getDomact_id())
					.collect(Collectors.toList());
			httpSession.setAttribute("domacts", domacts);
		}
		
		boolean isEmptyLists = domacts.stream().flatMap(domact->domact.getSubgroups().stream()).count() == 0 ||
				domacts.stream().flatMap(domact->domact.getSubgroups().stream().flatMap(subgroup->subgroup.getCriterias().stream())).count() == 0;
		
		if (isEmptyLists) {
			model.addAttribute("domacts", new ArrayList<>());
		} else {
			model.addAttribute("domacts", domacts);
		}
		
		model.addAttribute("SelectedUrlPath", "/VariablesListOfCriteria");
		model.addAttribute("PostUrlPath", "/CriteriaSelectionForInsert");
		
		SearchSettingsDTO searchSettingsDTO = (SearchSettingsDTO) httpSession.getAttribute("searchDTO");
		
		if (searchSettingsDTO == null) {
			searchSettingsDTO = new SearchSettingsDTO();
		}
			
		model.addAttribute("searchDTO", searchSettingsDTO);
		
		return "/CriteriaPages/CriteriaSelection";
	}
	@PostMapping("/CriteriaSelectionForInsert")
	public String postCriteriaInsertion(@ModelAttribute("searchDTO") SearchSettingsDTO searchSettingsDTO, Model model, HttpSession httpSession) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Employee employee = employeeService.findByLogin(authentication.getName());
		
		
		
		List<Domact> domacts;
		if (searchSettingsDTO.getString() == null || searchSettingsDTO.getString().isEmpty() || searchSettingsDTO.getString().isBlank()) {
			searchSettingsDTO.setType(5);
		}
		
		String[] prompts = searchSettingsDTO.getString().split(",");
		
		
		switch (searchSettingsDTO.getType()) {
		case 1: //find in domact name
			
			domacts = employee.getPosition().getDPCs().stream()
			.map(dpc->dpc.getDomact())
			.distinct()
			.filter(domact->Stream.of(prompts).anyMatch(prompt->domact.getDomact_name().trim().toLowerCase().contains(prompt.trim().toLowerCase())))
			.sorted((dmct1,dmct2)->dmct1.getDomact_id() - dmct2.getDomact_id())
			.collect(Collectors.toList());
			
			
			break;
		case 2: // find in subgroup name
			
			domacts = employee.getPosition().getDPCs().stream()
			.map(dpc->dpc.getDomact())
			.distinct()
			.sorted((dmct1,dmct2)->dmct1.getDomact_id() - dmct2.getDomact_id())
			.collect(Collectors.toList());
			
			
			domacts.stream().forEach(domact->{
				
				
				
				domact.setSubgroups(
						domact.getSubgroups().stream()
						.filter(subgr->Stream.of(prompts).anyMatch(prompt->subgr.getSubgroup_name().trim().toLowerCase().contains(prompt.trim().toLowerCase())))
						.sorted((subgr1,subgr2)->subgr1.getSubgroup_id() - subgr2.getSubgroup_id())
						.collect(Collectors.toSet())
						);
			});
			
			
			break;
		case 3: //find in criteria name
			
			domacts = employee.getPosition().getDPCs().stream()
			.map(dpc->dpc.getDomact())
			.distinct()
			.sorted((dmct1,dmct2)->dmct1.getDomact_id() - dmct2.getDomact_id())
			.collect(Collectors.toList());
			
			
			domacts.stream().forEach(domact->{
				
				domact.getSubgroups().stream().forEach(subgroup->{
					
					subgroup.setCriterias(
							subgroup.getCriterias().stream()
							.filter(criteria->Stream.of(prompts).anyMatch(prompt->criteria.getCriteria_name().trim().toLowerCase().contains(prompt.trim().toLowerCase())))
							.sorted((cr1,cr2)->cr1.getCriteria_id() - cr2.getCriteria_id())
							.collect(Collectors.toSet())
							);
					
				});
				
				
			});
			
			break;
		case 4: //find in criteria description
			

			domacts = employee.getPosition().getDPCs().stream()
			.map(dpc->dpc.getDomact())
			.distinct()
			.sorted((dmct1,dmct2)->dmct1.getDomact_id() - dmct2.getDomact_id())
			.collect(Collectors.toList());
			
			
			domacts.stream().forEach(domact->{
				
				domact.getSubgroups().stream().forEach(subgroup->{
					
					subgroup.setCriterias(
							subgroup.getCriterias().stream()
							.filter(criteria->Stream.of(prompts).anyMatch(prompt->criteria.getCriteria_descr().trim().toLowerCase().contains(prompt.trim().toLowerCase())))
							.sorted((cr1,cr2)->cr1.getCriteria_id() - cr2.getCriteria_id())
							.collect(Collectors.toSet())
							);
					
				});
				
				
			});
		default:
			domacts = employee.getPosition().getDPCs().stream()
			.map(dpc->dpc.getDomact())
			.distinct()
			.sorted((dmct1,dmct2)->dmct1.getDomact_id() - dmct2.getDomact_id())
			.collect(Collectors.toList());
			break;
		}
		httpSession.setAttribute("domacts", domacts);
		httpSession.setAttribute("searchDTO", searchSettingsDTO);
		
		
		
		
		
		return "redirect:/CriteriaSelectionForInsert";
	}
	
	
	@GetMapping("/VariablesListOfCriteria/{id}")
	public String getVariableInsertionList(@PathVariable("id") int id, Model model, HttpSession httpSession) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Employee employee = employeeService.findByLogin(authentication.getName());
		
		
		List<Domact> domacts = domactService.findAll();
		
		
		List<Criteria> criterias = domacts.stream()
		.flatMap(domact->domact.getSubgroups().stream())
		.flatMap(subgroup->subgroup.getCriterias().stream())
		.collect(Collectors.toList());
		
		Criteria selectedCriteria = (Criteria) httpSession.getAttribute("selectedCriteria_"+id);
		
		if (selectedCriteria == null)
		{
			selectedCriteria = criterias.stream()
					.filter(criteria->criteria.getCriteria_id() == id)
					.findAny()
					.orElse(null);
			httpSession.setAttribute("selectedCriteria_" + id, selectedCriteria);
			
		}
		
		
		model.addAttribute("criteria", selectedCriteria);
		
		
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+id);
		if (insertedVariableDTOs ==null) {
			
			insertedVariableDTOs =  selectedCriteria.getCriteriaFormulas().stream()
					.flatMap(crFormula->crFormula.getFormula().getFormulaVariables().stream())
					.map(formVar->new InsertedVariableDTO(null, null, employee, formVar.getVariable()))
					.collect(Collectors.toList());
		
			httpSession.setAttribute("insertedVariablesDTO_"+id, insertedVariableDTOs);
		}
		model.addAttribute("insertVariableDTOList", insertedVariableDTOs);
		
		if (insertedVariableDTOs.stream()
				.filter(insVarDTO->!insVarDTO.getVariable().getCount().equals("1")).count() > 0) {
			model.addAttribute("submitButtonName", "NEXT" );

			model.addAttribute("PathUrl", "/NextVariablesListOfCriteria"); //"/VariablesListOfCriteria"
		}
			
		else {
			model.addAttribute("submitButtonName", "NEXT" );
			model.addAttribute("PathUrl", "/VariablesListOfCriteria");
		
		}

		model.addAttribute("VariableListType", "1");
		
		model.addAttribute("backUrl", "/CloseVariableList");
		model.addAttribute("EnterURL", "InsertVariable");
		
		httpSession.removeAttribute("multipliedVariableDTO_" + id);
		return "/CriteriaPages/InsertCriteriaVariablesList";
	}
	
	@GetMapping("/InsertVariable/{crID}/{id}")
	public String getVariableInsertionForm(@PathVariable("crID")int crID,@PathVariable("id")int id, Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+crID);
		
		if (insertedVariableDTOs == null) 
			return "redirect:/CriteriaSelectionForInsert";
		
		InsertedVariableDTO dto = insertedVariableDTOs.get(id);
		
		model.addAttribute("insertedVariableDTO", dto);
		model.addAttribute("idElem", id);
		model.addAttribute("crID", crID);
		model.addAttribute("postURL", "/InsertVariable/" + crID + "/"+ id +"");
		model.addAttribute("backURL", "/VariablesListOfCriteria/"+crID);
		
		return "/CriteriaPages/InsertedVariableRegistration";
	}
	
	@GetMapping("/CloseVariableList")
	public String closeVariableList (HttpSession httpSession){
		
		
		return "redirect:/CriteriaSelectionForInsert";
	}
	
	@PostMapping("/InsertVariable/{crID}/{id}")
	public String postVariableInsertionForm(@PathVariable("crID")int crID,@PathVariable("id")int id,
			@ModelAttribute("insertedVariableDTO") InsertedVariableDTO insertedVariableDTO, Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+crID);
		
		if (insertedVariableDTOs == null) 
			return "redirect:/CriteriaSelectionForInsert";
		
		
		
		insertedVariableDTOs.get(id).setInserted_value(insertedVariableDTO.getInserted_value());
		
		
		return "redirect:/VariablesListOfCriteria/" + crID;
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/NextVariablesListOfCriteria/{id}")
	public String getnextVarInsForm(@PathVariable("id")int id, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+id);
		
		Criteria selectedCriteria = (Criteria) httpSession.getAttribute("selectedCriteria_"+id);
		if (insertedVariableDTOs == null || selectedCriteria == null) 
			return "redirect:/CriteriaSelectionForInsert";
		
		
		List<InsertedVariableDTO> insertedMultipleVariableDTOs = insertedVariableDTOs.stream()
				.filter(insVarDTO->!insVarDTO.getVariable().getCount().equals("1"))
				.distinct()
				.collect(Collectors.toList());
		
		List<InsertedVariableDTO> multipliedMultVariableDTOs;
		
		if (httpSession.getAttribute("multipliedVariableDTO_" + id) == null) {
			multipliedMultVariableDTOs = new ArrayList<>();
			
			insertedMultipleVariableDTOs.stream()
			.forEach(insVar->{
				IntStream.range(0, Integer.parseInt(
						insertedVariableDTOs.stream()
						.filter(singleInsVar->singleInsVar.getVariable().getCount().equals("1")
								&& singleInsVar.getVariable().getVariable_sign().equals(insVar.getVariable().getCount()))
						.findAny()
						.orElse(null).getInserted_value()
						)).forEach(i->{
							multipliedMultVariableDTOs.add(new InsertedVariableDTO(insVar.getDatetime(), insVar.getInserted_value(), 
									insVar.getEmployee(), insVar.getVariable()));
						});
			});
			httpSession.setAttribute("multipliedVariableDTO_" + id, multipliedMultVariableDTOs);
		} else {
			multipliedMultVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("multipliedVariableDTO_" + id);
		}
		
		
		
		
		
		List<String> singleVarsNamesList = insertedVariableDTOs.stream()
				.map(insVarDTO->insVarDTO.getVariable().getCount())
				.filter(count->!count.equals("1"))
				.distinct()
				.collect(Collectors.toList());
		String singleVarNamesListString = "";
		for (int i = 0; i < singleVarsNamesList.size(); i++) {
			singleVarNamesListString += singleVarsNamesList.get(i);
		}
		
		model.addAttribute("criteria", selectedCriteria);
		model.addAttribute("insertVariableDTOList", multipliedMultVariableDTOs);
		
		model.addAttribute("submitButtonName", "NEXT" );
		model.addAttribute("PathUrl", "/VariablesListOfCriteria");
		model.addAttribute("VariableListType", singleVarNamesListString);
		model.addAttribute("backUrl", "/VariablesListOfCriteria/"+id);
		model.addAttribute("EnterURL", "InsertMultVariable");
		
		return "/CriteriaPages/InsertCriteriaVariablesList";
	}
	
	@PostMapping("/NextVariablesListOfCriteria/{id}")
	public String nextVarInsForm(@PathVariable("id")int id, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+id);
		boolean isThereUninsertedVariables = insertedVariableDTOs.stream()
				.filter(insVar->(insVar.getInserted_value()==null || insVar.getInserted_value().isBlank() || insVar.getInserted_value().isEmpty())
						&& insVar.getVariable().getCount().equals("1"))
				.count() > 0;
		if (isThereUninsertedVariables) {
			return "redirect:/VariablesListOfCriteria/" +id;
		}
		
		
		return "redirect:/NextVariablesListOfCriteria/"+id;
	}
	
	@GetMapping("/DeleteCriteriaValues/{id}")
	public String deleteVarValuesOfCriteria(@PathVariable("id")int crID, HttpSession httpSession) {
		httpSession.removeAttribute("insertedVariablesDTO_"+crID);
		httpSession.removeAttribute("selectedCriteria_"+crID);
		return "redirect:/CriteriaSelectionForInsert";
	}
	
	@GetMapping("/InsertMultVariable/{crID}/{id}")
	public String getMVariableInsertionForm(@PathVariable("crID")int crID,@PathVariable("id")int id, Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("multipliedVariableDTO_"+crID);
		
		if (insertedVariableDTOs == null) 
			return "redirect:/NextVariablesListOfCriteria/"+crID;
		
		InsertedVariableDTO dto = insertedVariableDTOs.get(id);
		
		model.addAttribute("insertedVariableDTO", dto);
		model.addAttribute("idElem", id);
		model.addAttribute("crID", crID);
		model.addAttribute("postURL", "/InsertMultVariable/" + crID + "/"+ id +"");
		model.addAttribute("backURL", "/NextVariablesListOfCriteria/"+crID);
		
		
		return "/CriteriaPages/InsertedVariableRegistration";
	}
	@PostMapping("/InsertMultVariable/{crID}/{id}")
	public String postMVariableInsertionForm(@PathVariable("crID")int crID,@PathVariable("id")int id,
			@ModelAttribute("insertedVariableDTO") InsertedVariableDTO insertedVariableDTO, Model model, HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+crID);
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> multipliedMultVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("multipliedVariableDTO_" + crID);
		
		if (insertedVariableDTOs == null) 
			return "redirect:/CriteriaSelectionForInsert";
		if (multipliedMultVariableDTOs == null)
			return "redirect:/CriteriaSelectionForInsert";
		
		
		multipliedMultVariableDTOs.get(id).setInserted_value(insertedVariableDTO.getInserted_value());
		
		return "redirect:/NextVariablesListOfCriteria/" + crID;
	}
	
	@PostMapping("/VariablesListOfCriteria/{crId}")
	public String postVariablesListOfCriteria(@PathVariable("crId") int crID, Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<InsertedVariableDTO> multipliedMultVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("multipliedVariableDTO_" + crID);
		if (multipliedMultVariableDTOs != null) {
			@SuppressWarnings("unchecked")
			List<InsertedVariableDTO> insertedVariableDTOs = (List<InsertedVariableDTO>) httpSession.getAttribute("insertedVariablesDTO_"+crID);
			insertedVariableDTOs.removeIf(elem->!elem.getVariable().getCount().equals("1"));
			insertedVariableDTOs.addAll(multipliedMultVariableDTOs);
			httpSession.removeAttribute("multipliedVariableDTO_" + crID);
		}

		
		
		
		return "redirect:/CriteriaSelectionForInsert";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("submitVariableInserting")
	public String submitVariableInserting(Model model, HttpSession httpSession) {
		List<Criteria> criterias = criteriaService.findAll();
		
		criterias.stream().forEach(criteria-> {
			if (httpSession.getAttribute("insertedVariablesDTO_" +  criteria.getCriteria_id()) != null) {
				System.out.println(criteria.getCriteria_name() +":");
				((List<InsertedVariableDTO>)httpSession.getAttribute("insertedVariablesDTO_" +  criteria.getCriteria_id())).forEach(insVar->{
					System.out.println("\t"+insVar.getVariable().getVariable_sign() + " = " + insVar.getInserted_value());
				});
			}
			
		});
		
		
		return "redirect:/CriteriaSelectionForInsert";
		
	}
}

