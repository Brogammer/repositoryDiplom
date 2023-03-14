package kurswork.remaster.KursWorkKpiApp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.mariuszgromada.math.mxparser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.FormulaDTO;
import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Formula;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
import kurswork.remaster.KursWorkKpiApp.model.Variable;
import kurswork.remaster.KursWorkKpiApp.repositories.FormulaRepository;

@Service
public class FormulaServiceImpl implements FormulaService {

	@Autowired
	FormulaRepository formulaRepository;

	@Override
	public double evaluateFormula(Formula formula, List<List<InsertedVariableDTO>> insertedVariablesOfEmployye) {
//		Argument n = new Argument("n", 3); // number of variables
//		Function function = new Function("f(...) = sum(i, 1, ([npar] / 2), par(i)/par(i+2))",n);
//		
//		
//		List<Integer> list = List.of(1,2,1, 1,1,1, 1,2,2);
//		
//		String functionStr = "f(";
//		for (int i = 0; i < list.size(); i++) {
//			functionStr += list.get(i) + (i == list.size() -1 ? ")": ",");
//		}
//		Expression expression = new Expression(functionStr + "", function);
//		Argument n_k12 = new Argument("n_k12", 7); // number of variables
//		
//		Expression expression1 = new Expression("iff( n_k12 < 0, 0;"
//												+ " (5 <= n_k12) & (n_k12 < 6.9), 1;"
//												+ " (7 <= n_k12) & (n_k12 < 9), 2;"
//												+ " 9 <= n_k12, 3)",n_k12);
//		
//		
//		
//		System.out.println(expression.calculate());
//		System.out.println("Ball : " + expression1.calculate());

		System.out.println("Formula :\n\t" + formula.getFormula_string());
//		System.out.println("Variables :\n");

		List<Variable> variables = formula.getFormulaVariables().stream()
				.map(criteiaVariable -> criteiaVariable.getVariable())
				.sorted((e1, e2) -> e1.getVariable_id() - e2.getVariable_id()).collect(Collectors.toList());
		
//		variables.stream()
//				.forEach(var -> System.out.println("\t" + var.getVariable_sign() + "\n\t\t" + var.getVariable_descr()));
//		List<List<InsertedVariable>> insertedVariablesOfEmployye = variables.stream()
//				.map(var -> var.getInsertedVariables().stream()
//						.filter(insVar -> insVar.getEmployee().getEmployee_id() == employee.getEmployee_id()
//								&& insVar.getDatetime().compareTo(datetime) == 0)
//						.sorted((e1, e2) -> e1.getInsVar_id() - e2.getInsVar_id()).collect(Collectors.toList()))
//				.collect(Collectors.toList());
//		

		for (int i = 0; i < insertedVariablesOfEmployye.size(); i++) {
			if (insertedVariablesOfEmployye.get(i).size() == 0) {
				System.out.println("hello bitches");
				return 0;
			}
		}
		
//		System.out.println("User " + employee.getName() + " inserted:");

//		insertedVariablesOfEmployye.stream().forEach(variableValuesList -> {
//			System.out.println(variableValuesList.get(0).getVariable().getVariable_sign() + " : ");
//			variableValuesList.stream().forEach(insValue -> System.out.print(insValue.getInserted_value() + "\t"));
//			System.out.println();
//		});

		String formulaStr = formula.getFormula_string();
		String functionStr = "f(";
		Function function;
		if (formulaStr.contains("sum")) {
			List<List<InsertedVariableDTO>> multipleVariables = insertedVariablesOfEmployye.stream()
					.filter(list -> !list.get(0).getVariable().getCount().equals("1")).collect(Collectors.toList());
			List<List<InsertedVariableDTO>> singleVariables = insertedVariablesOfEmployye.stream()
					.filter(list -> list.get(0).getVariable().getCount().equals("1")).collect(Collectors.toList());
			

			for (int i = 0; i < multipleVariables.size(); i++) {
				String variableNameForCount = multipleVariables.get(i).get(0).getVariable().getCount();

				InsertedVariableDTO insertedVarForStep = singleVariables.stream().flatMap(varList -> varList.stream())
						.filter(insVar -> insVar.getVariable().getVariable_sign().equals(variableNameForCount))
						.findAny().orElse(null);
				int step = Integer.parseInt(insertedVarForStep.getInserted_value());

				formulaStr = formulaStr.replaceAll(multipleVariables.get(i).get(0).getVariable().getVariable_sign(),
						"par(i + (" + step + " * " + i + "))");
			}
			formulaStr = formulaStr.replaceAll("n", "[npar]/" + multipleVariables.size());

			List<Double> params = new ArrayList<>();
			multipleVariables.stream().forEach(valuesList -> {
				params.addAll(valuesList.stream().map(valueObj -> Double.parseDouble(valueObj.getInserted_value()))
						.collect(Collectors.toList()));
			});

			for (int i = 0; i < params.size(); i++) {
				functionStr += params.get(i) + (i == params.size() - 1 ? ")" : ",");
			}
			function = new Function(formulaStr);
		} else {
			List<Double> params = insertedVariablesOfEmployye.stream().flatMap(list -> list.stream())
					.map(insVal -> Double.parseDouble(insVal.getInserted_value())).collect(Collectors.toList());

			for (int i = 0; i < params.size(); i++) {
				functionStr += params.get(i) + (i == params.size() - 1 ? ")" : ",");
			}
			function = new Function(formulaStr);

		}

		System.out.println(formulaStr);

		Expression expression = new Expression(functionStr + "", function);
		double result = expression.calculate();
		
		return result;
	}

	@Override
	public Formula save(FormulaDTO formulaDTO) {
		// TODO Auto-generated method stub
		Formula formula = new Formula(0, formulaDTO.getFormula_string(), null, null);
		return formulaRepository.save(formula);
	}

	@Override
	public boolean checkRestrictions(InsertedVariableDTO dto, String insertedValue) {

		String restrictions = dto.getVariable().getRestrictions();

		if (restrictions == null || restrictions.isBlank() || restrictions.isEmpty()
				|| restrictions.toLowerCase().equals("null")) {
			return true;
		}

		String varSign = dto.getVariable().getVariable_sign().trim();
		Double value = 0.0;
		try {
			value = Double.parseDouble(insertedValue.replace(',', '.'));

		} catch (Exception e) {
			return false;
		}

		Function function = new Function("f", restrictions, varSign);

		Expression expression = new Expression("f(" + value + ")", function);
		double result = expression.calculate();

		if (result == 1.0)
			return true;
		else
			return false;
	}

}
