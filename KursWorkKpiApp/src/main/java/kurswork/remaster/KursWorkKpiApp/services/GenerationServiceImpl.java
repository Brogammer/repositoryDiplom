package kurswork.remaster.KursWorkKpiApp.services;

import java.util.ArrayList;
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
			Map<Domact, Calification> domactCalificationResult, Map<Domact, CalculatedDomact> mapOfCalculatedDomacts,
			Employee employee) {
		// Создаем новый документ
		XWPFDocument document1 = new XWPFDocument();

		// Создаем раздел

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

		List<String> header = mapOfCalculatedDomacts.entrySet().stream().map(entry -> entry.getKey().getDomact_name())
				.collect(Collectors.toList());
		XWPFTableRow headerRow = secondTable.createRow();
		headerRow.createCell().addParagraph();
		for (String column : header) {
			headerRow.createCell().addParagraph().createRun().setText(column);
		}

		return document1;
	}

}
