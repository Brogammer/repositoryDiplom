package kurswork.remaster.KursWorkKpiApp.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.spire.doc.Document;
import com.spire.doc.Table;

import kurswork.remaster.KursWorkKpiApp.model.Criteria;
@Service
public interface GenerationService {
	Table addTable(Document document, Map<Criteria, Double> mapOfResults);
}
