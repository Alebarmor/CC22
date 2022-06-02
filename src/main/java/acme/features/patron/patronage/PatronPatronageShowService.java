package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	 
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronage(id);
		
		result = request.getPrincipal().hasRole(Patron.class) && (patronage.getPatron().getUserAccount().getUsername().equals(request.getPrincipal().getUsername()) || !patronage.isNotPublished());
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronage(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startPeriod", "endPeriod", "link", "notPublished");
		
		final String username = entity.getInventor().getUserAccount().getUsername();
		model.setAttribute("username", username);
		
		final String fullName = entity.getInventor().getUserAccount().getIdentity().getFullName();
		model.setAttribute("fullName", fullName);
		
		final String email = entity.getInventor().getUserAccount().getIdentity().getEmail();
		model.setAttribute("email", email);
		
		if(!entity.isNotPublished()) {
			model.setAttribute("readonly", true);
		}
		
	}
	
}
