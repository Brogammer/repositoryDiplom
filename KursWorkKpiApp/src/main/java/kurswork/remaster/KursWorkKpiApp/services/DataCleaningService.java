package kurswork.remaster.KursWorkKpiApp.services;

import org.springframework.stereotype.Service;

@Service
public interface DataCleaningService {
	public void cleanDataExeptLastFiveYears();
}
