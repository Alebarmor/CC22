package acme.features.patron.patronage;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageListMineService implements AbstractListService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	 
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		result = request.getPrincipal().hasRole(Patron.class);
		
		return result;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;
		
		Collection<Patronage> result;
		Principal principal;

		principal = request.getPrincipal();
        result = this.repository.findMinePatronages(principal.getUsername());

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "startPeriod", "endPeriod", "notPublished");
		
		final boolean isPublished = !entity.isNotPublished();
		String strPublished = "";
		
		if (isPublished) {
			if (request.getLocale().equals(Locale.ENGLISH)) {
				strPublished = "Yes";
			} else {
				strPublished = "Sí";
			}
		} else {
			strPublished = "No";
		}
		
		model.setAttribute("isPublished", strPublished);
	}
	
}
