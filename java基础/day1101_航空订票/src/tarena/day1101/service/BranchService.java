package tarena.day1101.service;

import com.tarena.abs.datasource.DataService;
import com.tarena.abs.datasource.entities.Branch;

public class BranchService {
	public Branch[] findByCity(String city) {
		return
				DataService.findAllBrances(city);
	}
}
