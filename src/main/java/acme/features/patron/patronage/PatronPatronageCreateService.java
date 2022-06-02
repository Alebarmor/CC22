package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.Status;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;
import main.spamDetector;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
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
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        request.bind(entity, errors, "code", "legalStuff", "budget", "startPeriod", "endPeriod", "link");
        
        final String username = request.getModel().getString("username");
        
        final Inventor inventor = this.repository.findOneInventorByUsername(username);
        entity.setInventor(inventor);
		
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        if(!errors.hasErrors("code")) {
        	errors.state(request, this.repository.findPatronageByCode(entity.getCode()) == null, "code", "patron.patronage.form.error.not-unique");
        }
        
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
        
        if(!errors.hasErrors("username")) {
        	final String username = request.getModel().getString("username");
        	final Inventor inventor = this.repository.findOneInventorByUsername(username);
        	errors.state(request, !username.equals(""), "username", "patron.patronage.form.error.username-null");
        	if (!username.equals("")) {
        		errors.state(request, inventor != null, "username", "patron.patronage.form.error.username-does-not-exist");
        	}
        }
        
        if(!errors.hasErrors("startPeriod")) {
        	final Date startPeriod = entity.getStartPeriod();
        	final Calendar calendar = Calendar.getInstance();
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
        	final Configuration conf = this.repository.findConfiguration();
        	final Double hardThreshold = conf.getStrongSpamThreshold();
        	final Double softThreshold = conf.getWeakSpamThreshold();
        	final List<String> hardWords = new ArrayList<String>(Arrays.asList(conf.getStrongSpamTerms().split(",")));
        	final List<String> softWords = new ArrayList<String>(Arrays.asList(conf.getWeakSpamTerms().split(",")));
        	final String legalStuff = entity.getLegalStuff();
        	final boolean isSpam = spamDetector.isSpam(legalStuff, softWords, softThreshold, hardWords, hardThreshold);
        	errors.state(request,!isSpam, "legalStuff", "patron.patronage.form.error.spam");
        }

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "startPeriod", "endPeriod", "link");
		
	}
	
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		result = new Patronage();
		
		final Patron patron = this.repository.findOnePatronByUsername(request.getPrincipal().getUsername());
		result.setPatron(patron);
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationDate(moment);
		
		result.setNotPublished(true);
		result.setStatus(Status.PROPOSED);
		
		return result;
	}
	
	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
        assert entity != null;
        this.repository.save(entity);
	}
	
}