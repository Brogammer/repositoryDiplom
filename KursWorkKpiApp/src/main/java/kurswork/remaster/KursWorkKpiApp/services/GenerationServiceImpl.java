package kurswork.remaster.KursWorkKpiApp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.InsertedVariableDTO;
import kurswork.remaster.KursWorkKpiApp.dto.NormaStiintificaDTO;
import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;
import kurswork.remaster.KursWorkKpiApp.model.Calification;
import kurswork.remaster.KursWorkKpiApp.model.Criteria;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.Subgroup;

@Service
public class GenerationServiceImpl implements GenerationService {

	@Override
	public XWPFDocument generateWordFileApachePOI(Map<Domact, List<Subgroup>> mapOfDomactSubgroups,
			Map<Subgroup, List<Criteria>> mapOfSubGroupCriteria, Map<Criteria, Double> mapOfCriteriaResults,
			Map<Criteria, List<InsertedVariableDTO>> mapOfCriteriaVars,
			Map<Domact, CalculatedDomact> mapOfCalculatedDomacts, Employee employee, Double finalResult) {
		// Создаем новый документ
		XWPFDocument document1 = new XWPFDocument();

		// Создаем раздел
		document1.createParagraph().createRun().setText("Cadrul didactic " + employee.getName() + " "
				+ employee.getSurname() + "  Postul: " + employee.getPosition().getName());
		CalculatedDomact randomCalcDom = mapOfCalculatedDomacts.entrySet().stream().findAny().get().getValue();
		document1.createParagraph().createRun()
				.setText("Angajat pe: " + randomCalcDom.getNormaStiintifica() + " norma didactica");
		mapOfDomactSubgroups.entrySet().forEach(entry -> {
			// Создается заголовок
			document1.createParagraph().createRun().setText(entry.getKey().getDomact_name());
			// Создается таблица с заголовком
			XWPFTable table = document1.createTable();

			// Добавляются строки в таблицу
			String[] header = { "Indicatori", "Denumirea criteriului şi indicatorului", "Punctaj auto-evaluare",
					"Punctaj final" };
			XWPFTableRow headerRow = table.getRow(0);
			for (int i = 0; i < header.length; i++) {
				if (i == 0) {
					XWPFTableCell tableCell = headerRow.getCell(i);
					tableCell.getCTTc().addNewTcPr().addNewShd().setFill("9ebdde");
					XWPFRun run = tableCell.addParagraph().createRun();
					run.setText(header[i]);
					run.setBold(true);

				} else {
					XWPFTableCell tableCell = headerRow.addNewTableCell();
					tableCell.getCTTc().addNewTcPr().addNewShd().setFill("9ebdde");
					XWPFRun run = tableCell.addParagraph().createRun();
					run.setText(header[i]);
					run.setBold(true);
				}
			}

			for (Subgroup subgroup : entry.getValue()) {
				XWPFTableRow subgroupRow = table.createRow();

				// Поле subgroup_name
				XWPFTableCell subgroupCell = subgroupRow.getTableCells().get(1);

				XWPFRun subgroupRun = subgroupCell.addParagraph().createRun();
				subgroupRun.setText("Criteriul " + subgroup.getSubgroup_name());
				subgroupRun.setBold(true);

				// Добавляются строки для критериев
				for (Criteria criteria : mapOfSubGroupCriteria.get(subgroup)) {
					XWPFTableRow criteriaRow = table.createRow();
					criteriaRow.getTableCells().get(0).addParagraph().createRun().setText(criteria.getCriteria_name());
					criteriaRow.getTableCells().get(1).addParagraph().createRun()
							.setText(criteria.getCriteria_descr() + " ");
					mapOfCriteriaVars.get(criteria).stream().forEach(insVar -> {
						criteriaRow.getTableCells().get(1).addParagraph().createRun()
								.setText(insVar.getVariable().getVariable_descr() + " ("
										+ insVar.getVariable().getVariable_sign() + "):");
						criteriaRow.getTableCells().get(1).addParagraph().createRun()
								.setText("\tInserted Value :" + insVar.getInserted_value());
						criteriaRow.getTableCells().get(1).addParagraph().createRun()
								.setText("Comment :" + insVar.getComment());

					});
					criteriaRow.getTableCells().get(2).addParagraph().createRun()
							.setText(Double.toString(mapOfCriteriaResults.get(criteria)));
					criteriaRow.getTableCells().get(3).addParagraph().createRun().setText("");
				}
			}
			// System.out.println(entry.getKey().getDomact_name() );

			CalculatedDomact calculatedDomact = mapOfCalculatedDomacts.entrySet().stream()
					.filter(entry1 -> entry1.getKey().getDomact_id() == entry.getKey().getDomact_id())
					.map(entry2 -> entry2.getValue()).findAny().get();

			XWPFTableRow sumOfCriteriaTableRow = table.createRow();
			XWPFRun runSumOfCrText = sumOfCriteriaTableRow.getTableCells().get(1).addParagraph().createRun();
			runSumOfCrText.setBold(true);
			runSumOfCrText.setText(" Suma puncte " + entry.getKey().getDomact_name().substring(0, 1) + ": ");

			XWPFRun runSumOfCrValue = sumOfCriteriaTableRow.getTableCells().get(2).addParagraph().createRun();
			runSumOfCrValue.setBold(true);
			runSumOfCrValue
					.setText("" + calculatedDomact.getCalculated_value() * calculatedDomact.getNormaStiintifica());

			XWPFTableRow TotalValueOfCriteriaTableRow = table.createRow();
			XWPFRun runTotalValueText = TotalValueOfCriteriaTableRow.getTableCells().get(1).addParagraph().createRun();
			runTotalValueText.setBold(true);
			runTotalValueText.setText("Total puncte " + entry.getKey().getDomact_name().substring(0, 1) + ": ");

			XWPFRun runTotalValueValue = TotalValueOfCriteriaTableRow.getTableCells().get(2).addParagraph().createRun();
			runTotalValueValue.setBold(true);
			runTotalValueValue.setText("" + calculatedDomact.getCalculated_value());
			IntStream.range(0, 4).forEach(i -> {
				TotalValueOfCriteriaTableRow.getCell(i).getCTTc().addNewTcPr().addNewShd().setFill("9ebdde");

			});

		});

		document1.createParagraph().createRun().setText("Calificativul final pentru fiecare domeniu de activitate:");
		XWPFTable secondTable = document1.createTable();

		XWPFTableRow headerRow = secondTable.getRow(0);
		XWPFTableRow secondRow = secondTable.createRow();
		XWPFTableRow thirdRow = secondTable.createRow();
		CTVMerge ctvmerge1 = CTVMerge.Factory.newInstance();
		ctvmerge1.setVal(STMerge.RESTART);
		CTVMerge ctvMerge2 = CTVMerge.Factory.newInstance();
		ctvMerge2.setVal(STMerge.CONTINUE);

		headerRow.getCell(0).addParagraph().createRun().setText("Position\\Domeniul de Activitate");
		headerRow.getCell(0).getCTTc().addNewTcPr().setVMerge(ctvmerge1);
		secondRow.getCell(0).getCTTc().addNewTcPr().setVMerge(ctvMerge2);

		thirdRow.getCell(0).addParagraph().createRun().setText(employee.getPosition().getName());
		mapOfCalculatedDomacts.entrySet().forEach(entry -> {

			XWPFTableCell tableCell = headerRow.addNewTableCell();
			tableCell.addParagraph().createRun().setText(entry.getKey().getDomact_name());
			XWPFTableCell tableCellForMerge = headerRow.addNewTableCell();

			int tableCellIndex = headerRow.getTableCells().indexOf(tableCell);
			int tableCellForMergeIndex = headerRow.getTableCells().indexOf(tableCellForMerge);

			CTHMerge cthMerge = CTHMerge.Factory.newInstance();
			cthMerge.setVal(STMerge.RESTART);
			CTHMerge cthMerge2 = CTHMerge.Factory.newInstance();
			cthMerge2.setVal(STMerge.CONTINUE);
			headerRow.getCell(tableCellIndex).getCTTc().addNewTcPr().setHMerge(cthMerge);
			headerRow.getCell(tableCellForMergeIndex).getCTTc().addNewTcPr().setHMerge(cthMerge2);

			secondRow.createCell().addParagraph().createRun().setText("Puncte");
			secondRow.createCell().addParagraph().createRun().setText("calificat");

			thirdRow.createCell().addParagraph().createRun().setText("" + entry.getValue().getCalculated_value());
			thirdRow.createCell().addParagraph().createRun().setText(entry.getValue().getCalculated_calificat());

		});

		document1.createParagraph().createRun().setText(
				"Calificativul: Foarte bine – A (4 puncte); Bine – B (3 puncte); Satisfăcător – C (2 puncte); Nesatisfăcător – D (1 punct).");
		document1.createParagraph().createRun()
				.setText("Punctajul mediu (se apreciază în intervalul 1-4) ___" + finalResult + "___");
		document1.createParagraph().createRun()
				.setText("Data __" + randomCalcDom.getDomactCalcDate().toString().substring(0, 10) + "__");

		return document1;
	}

	@Override
	public XWPFDocument generateRaportAnual(Map<Employee, List<CalculatedDomact>> mapOfEmployeeCalcDomact,
			Map<Employee, Double> mapOfEmployeesFinalResults, Date chosenDate) {

		XWPFDocument document = new XWPFDocument();

		Integer intYear = Integer.parseInt(chosenDate.toString().substring(0, 4));
		String dateRange = (intYear - 1) + "-" + intYear;
		document.createParagraph().createRun().setText("Anul universitar" + dateRange);
		XWPFTable table = document.createTable();

		XWPFTableRow headerRow = table.getRow(0);
		XWPFTableRow secondRow = table.createRow();
		CTVMerge ctvmerge1 = CTVMerge.Factory.newInstance();
		ctvmerge1.setVal(STMerge.RESTART);
		CTVMerge ctvMerge2 = CTVMerge.Factory.newInstance();
		ctvMerge2.setVal(STMerge.CONTINUE);
		headerRow.addNewTableCell();
		secondRow.addNewTableCell();
		headerRow.getCell(0).getParagraphs().get(0).createRun().setText("Numele si prenumele");
		headerRow.getCell(0).getCTTc().addNewTcPr().setVMerge(ctvmerge1);
		secondRow.getCell(0).getCTTc().addNewTcPr().setVMerge(ctvMerge2);
		headerRow.getCell(1).getParagraphs().get(0).createRun().setText("Position\\Domeniul de Activitate");
		headerRow.getCell(1).getCTTc().addNewTcPr().setVMerge(ctvmerge1);
		secondRow.getCell(1).getCTTc().addNewTcPr().setVMerge(ctvMerge2);
		mapOfEmployeeCalcDomact.entrySet().stream().findAny().get().getValue().forEach(calcDom -> {
			XWPFTableCell tableCell = headerRow.addNewTableCell();
			tableCell.getParagraphs().get(0).createRun().setText(calcDom.getDomact().getDomact_name());
			XWPFTableCell tableCellForMerge = headerRow.addNewTableCell();

			int tableCellIndex = headerRow.getTableCells().indexOf(tableCell);
			int tableCellForMergeIndex = headerRow.getTableCells().indexOf(tableCellForMerge);

			CTHMerge cthMerge = CTHMerge.Factory.newInstance();
			cthMerge.setVal(STMerge.RESTART);
			CTHMerge cthMerge2 = CTHMerge.Factory.newInstance();
			cthMerge2.setVal(STMerge.CONTINUE);
			headerRow.getCell(tableCellIndex).getCTTc().addNewTcPr().setHMerge(cthMerge);
			headerRow.getCell(tableCellForMergeIndex).getCTTc().addNewTcPr().setHMerge(cthMerge2);

			secondRow.createCell().getParagraphs().get(0).createRun().setText("Pct.");
			secondRow.createCell().getParagraphs().get(0).createRun().setText("calif.");
		});
		headerRow.addNewTableCell();
		secondRow.addNewTableCell();
		int lastCellIndex = headerRow.getTableCells().size() - 1;
		headerRow.getCell(lastCellIndex).getParagraphs().get(0).createRun().setText("Punctajul mediu");
		secondRow.getCell(lastCellIndex).getParagraphs().get(0).createRun().setText("(1-4)");

		mapOfEmployeeCalcDomact.entrySet().forEach(entry -> {
			XWPFTableRow nextRow = table.createRow();
			nextRow.getCell(0).getParagraphs().get(0).createRun()
					.setText(entry.getKey().getName() + " " + entry.getKey().getSurname());

			nextRow.getCell(1).getParagraphs().get(0).createRun().setText(entry.getKey().getPosition().getName());
			entry.getValue().forEach(calcDom -> {
				int puncteCellIndex = (entry.getValue().indexOf(calcDom) * 2) + 2;
				nextRow.getCell(puncteCellIndex).getParagraphs().get(0).createRun()
						.setText("" + (calcDom.getCalculated_value()));
				nextRow.getCell(puncteCellIndex + 1).getParagraphs().get(0).createRun()
						.setText("" + calcDom.getCalculated_calificat());

			});
			nextRow.getCell(lastCellIndex).getParagraphs().get(0).createRun()
					.setText("" + mapOfEmployeesFinalResults.get(entry.getKey()));

		});

		return document;
	}

	@Override
	public XWPFDocument generateLastFiveYearsRaport(
			Map<Domact, Map<Date, List<CalculatedDomact>>> sortedMapOfLastFiveYearsCalcDom,
			Map<Date, String> mapOfYearsRange, Map<Domact, Double> mapOfTotalPuncte, Employee employee) {
		// TODO Auto-generated method stub

		XWPFDocument document = new XWPFDocument();
		document.createParagraph().createRun().setText("Cadrul didactic " + employee.getName() + " "
				+ employee.getSurname() + "  Postul: " + employee.getPosition().getName());

		XWPFTable table = document.createTable();
		XWPFTableRow headerRow = table.getRow(0);
		headerRow.getCell(0).getParagraphs().get(0).createRun().setText("Anul Universitar");
		headerRow.addNewTableCell().getParagraphs().get(0).createRun().setText("Domeniile de activitate");
		headerRow.addNewTableCell().getParagraphs().get(0).createRun().setText("Punctaj final");
		headerRow.addNewTableCell().getParagraphs().get(0).createRun().setText("Calificativul");

		sortedMapOfLastFiveYearsCalcDom.entrySet().forEach(entry -> {
			XWPFTableRow tableRow = table.createRow();
			CTHMerge cthMerge = CTHMerge.Factory.newInstance();
			cthMerge.setVal(STMerge.RESTART);
			CTHMerge cthMerge2 = CTHMerge.Factory.newInstance();
			cthMerge2.setVal(STMerge.CONTINUE);
			tableRow.getCell(0).getParagraphs().get(0).createRun().setText(entry.getKey().getDomact_name());
			tableRow.getCell(0).getCTTc().addNewTcPr().setHMerge(cthMerge);
			tableRow.getCell(1).getCTTc().addNewTcPr().setHMerge(cthMerge2);
			tableRow.getCell(2).getCTTc().addNewTcPr().setHMerge(cthMerge2);
			tableRow.getCell(3).getCTTc().addNewTcPr().setHMerge(cthMerge2);

			entry.getValue().entrySet().forEach(entry1 -> {
				XWPFTableRow yearsRow = table.createRow();
				yearsRow.getCell(0).getParagraphs().get(0).createRun().setText(mapOfYearsRange.get(entry1.getKey()));

				entry1.getValue().forEach(calcDom -> {
					yearsRow.getCell(2).getParagraphs().get(0).createRun().setText("" + calcDom.getCalculated_value());
					yearsRow.getCell(3).getParagraphs().get(0).createRun()
							.setText("" + calcDom.getCalculated_calificat());
				});

			});
			XWPFTableRow lastRow = table.createRow();
			lastRow.getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("9ebdde");

			XWPFRun xwpfRun = lastRow.getCell(2).getParagraphs().get(0).createRun();
			xwpfRun.setText("" + mapOfTotalPuncte.get(entry.getKey()));
			xwpfRun.setBold(true);
			XWPFRun xwpfRun1 = lastRow.getCell(1).getParagraphs().get(0).createRun();
			xwpfRun1.setText("Total Puncte " + entry.getKey().getDomact_name().substring(0, 1) + ":");
			xwpfRun1.setBold(true);
			XWPFTableRow splitRow = table.createRow();

			IntStream.range(0, 4).forEach(i -> {
				CTTcBorders borders = splitRow.getCell(i).getCTTc().addNewTcPr().addNewTcBorders();
				lastRow.getCell(i).getCTTc().addNewTcPr().addNewShd().setFill("9ebdde");
				headerRow.getCell(i).getCTTc().addNewTcPr().addNewShd().setFill("9ebdde");

				borders.addNewRight().setVal(STBorder.NIL);
				borders.addNewLeft().setVal(STBorder.NIL);
				borders.addNewBottom().setVal(STBorder.NIL);
				borders.addNewTop().setVal(STBorder.NIL);
			});

		});

		return document;
	}

}
