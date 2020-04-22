package tarena.day1101.service;

import com.tarena.abs.datasource.DataService;
import com.tarena.abs.datasource.entities.FlightPlan;

public class FlightService {

	public FlightPlan[] findByCity(String from, String to) {
		return
				DataService.findAllFlight(from, to);
	}
	
}
