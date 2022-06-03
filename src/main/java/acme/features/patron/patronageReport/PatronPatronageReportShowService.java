package acme.features.patron.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport>{
	
	@Autowired
	protected PatronPatronageReportRepository repository;
	 
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final PatronageReport patronageReport = this.repository.findOnePatronageReport(id);
		
		result = request.getPrincipal().hasRole(Patron.class) && patronageReport.getPatronage().getPatron().getUserAccount().getUsername().equals(request.getPrincipal().getUsername());

		
		return result;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		
		PatronageReport result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReport(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "sequenceNumber","creationMoment","memorandum","link");
		model.setAttribute("readonly", true);
		
	}
	
}
