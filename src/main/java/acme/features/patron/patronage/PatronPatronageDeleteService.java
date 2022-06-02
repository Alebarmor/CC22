package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;
import main.spamDetector;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	 
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronage(id);
		
		result = request.getPrincipal().hasRole(Patron.class) && patronage.isNotPublished() && patronage.getPatron().getUserAccount().getUsername().equals(request.getPrincipal().getUsername());
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        request.bind(entity, errors, "legalStuff", "budget", "startPeriod", "endPeriod", "link");
		
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        if(!errors.hasErrors("budget")) {
        	final String acceptedCurrencies = this.repository.findConfiguration().getAcceptedCurrencies();
            final String[] currencies = acceptedCurrencies.split(",");
            boolean isCorrect = false;
            final String c = entity.getBudget().getCurrency();
            for (final String currency : currencies) {
            	if (c.equals(currency)) {
            		isCorrect = true;
            	}
			}
        	errors.state(request, isCorrect, "budget", "patron.patronage.form.error.incorrect-currency");
        }
        
        if(!errors.hasErrors("budget")) {
        	errors.state(request, entity.getBudget().getAmount() >= 0.0, "budget", "patron.patronage.form.error.negative-budget");
        }
        
        if(!errors.hasErrors("startPeriod")) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Calendar calendar = Calendar.getInstance();
        	calendar.setTime(entity.getCreationDate()); // Aquí no tendremos en cuenta la fecha de actualización, sino de creación
        	calendar.add(Calendar.MONTH, 1);
        	calendar.add(Calendar.SECOND, -1); // Un mes menos un segundo
        	errors.state(request, startPeriod.after(calendar.getTime()), "startPeriod", "patron.patronage.form.error.start-period-not-enough");
        }
        
        if(!errors.hasErrors("endPeriod") && entity.getStartPeriod()!=null) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Date endPeriod = entity.getEndPeriod();
        	final Date moment = new Date(startPeriod.getTime() + 604799999); // Una semana menos un milisegundo
        	errors.state(request, endPeriod.after(moment), "endPeriod", "patron.patronage.form.error.end-period-one-week-before-start-period");
        }
        
        if(!errors.hasErrors("legalStuff")) {
        	final Configuration configuration = this.repository.findConfiguration();
        	final String[] sp = configuration.getWeakSpamTerms().split(",");
        	final List<String> softSpam = new ArrayList<String>(Arrays.asList(sp));
        	final Double softThreshold = configuration.getWeakSpamThreshold();
        	final String[] hp = configuration.getStrongSpamTerms().split(",");
        	final List<String> hardSpam = new ArrayList<String>(Arrays.asList(hp));
        	final Double hardThreshold = configuration.getStrongSpamThreshold();
        	errors.state(request, !spamDetector.isSpam(entity.getLegalStuff(), softSpam, softThreshold, hardSpam, hardThreshold), "memorandum", "patron.patronage.form.error.spam");
        }
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "startPeriod", "endPeriod", "link", "notPublished");
		
		final String username = entity.getInventor().getUserAccount().getUsername();
		model.setAttribute("username", username);
		
		final String fullName = entity.getInventor().getUserAccount().getIdentity().getFullName();
		model.setAttribute("fullName", fullName);
		
		final String email = entity.getInventor().getUserAccount().getIdentity().getEmail();
		model.setAttribute("email", email);
		
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
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
	
}
