package acme.features.patron.patronDashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.forms.PatronDashboard;
import acme.forms.Stats;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{
	
	@Autowired
	protected PatronPatronDashboardRepository repository;
	 
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		boolean result;
		result = request.getPrincipal().hasRole(Patron.class);
		
		return result;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		final PatronDashboard result = new PatronDashboard();
		
		final int totalNumberOfProposedPatronages = this.repository.numberOfStatusPatronages("PROPOSED");
		final int totalNumberOfAcceptedPatronages = this.repository.numberOfStatusPatronages("ACCEPTED");
		final int totalNumberOfDeniedPatronages = this.repository.numberOfStatusPatronages("DENIED");
		
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
		
		final Map<Pair<Status,String>, Stats> statsBudgetOfStatusPatronages = new HashMap<>();
		final List<Object[]> listStatsBudgetOfStatusPatronages = this.repository.statsBudgetOfStatusPatronages();
		
		for (int i=0; i<listStatsBudgetOfStatusPatronages.size(); i++) {
			final Object[] linea = listStatsBudgetOfStatusPatronages.get(i);
			final Pair<Status, String> pareja = Pair.of((Status)(linea[0]), (String)(linea[1]));
			final Stats stat = new Stats();
			stat.setAverage((Double)(linea[2]));
			stat.setDeviation((Double)(linea[3]));
			stat.setMinumun((Double)(linea[4]));
			stat.setMaximun((Double)(linea[5]));
			
			statsBudgetOfStatusPatronages.put(pareja, stat);
		}
		
		result.setStatsBudgetOfStatusPatronages(statsBudgetOfStatusPatronages);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfProposedPatronages",
			"totalNumberOfAcceptedPatronages",
			"totalNumberOfDeniedPatronages", "statsBudgetOfStatusPatronages");
		
	}
	
}
