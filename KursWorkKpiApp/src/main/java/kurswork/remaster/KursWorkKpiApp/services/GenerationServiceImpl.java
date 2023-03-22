package kurswork.remaster.KursWorkKpiApp.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.spire.doc.Document;
import com.spire.doc.Table;
import com.spire.doc.TableRow;

import kurswork.remaster.KursWorkKpiApp.model.Criteria;
@Service
public class GenerationServiceImpl implements GenerationService {

	@Override
	public Table addTable(Document document, Map<Criteria, Double> mapOfResults) {
		
		Table table = document.addSection().addTable();
		if (mapOfResults == null) {
			System.out.println("Nulltext");
			return table;
		}
		
		String[] header = new String[] { "N", "Name", "Result" };
		table.resetCells(mapOfResults.size() + 1, 3);
		TableRow row = table.getRows().get(0);
		for (int i = 0; i < header.length; i++)
			row.getCells().get(i).addParagraph().appendText(header[i]);
		List<Entry<Criteria, Double>> entryList = new ArrayList<>(mapOfResults.entrySet());
		entryList.forEach(entry -> {
			table.getRows().get(entryList.indexOf(entry) + 1).getCells().get(0).addParagraph()
					.appendText(entry.getKey().getCriteria_name());
			table.getRows().get(entryList.indexOf(entry) + 1).getCells().get(1).addParagraph()
					.appendText(entry.getKey().getCriteria_descr());
			table.getRows().get(entryList.indexOf(entry) + 1).getCells().get(2).addParagraph()
					.appendText("" + entry.getValue());
		});
		return table;
	}

}
