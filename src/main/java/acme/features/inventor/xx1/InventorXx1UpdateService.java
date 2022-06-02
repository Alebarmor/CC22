package acme.features.inventor.xx1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.xx1s.Xx1;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import main.spamDetector;

@Service
public class InventorXx1UpdateService implements AbstractUpdateService<Inventor, Xx1> {
	
	// Internal State 
	
	@Autowired
	protected InventorXx1Repository repository;
		
	//AbstractUpdateService<Inventor, Xx1> interface
	
	@Override
	public boolean authorise(final Request<Xx1> request) {
		assert request != null;
		final java.util.Collection<Xx1> mines = this.repository.findMineXx1(request.getPrincipal().getUsername());
		final Xx1 xx1 = this.repository.findOneXx1(request.getModel().getInteger("id"));
		
		final boolean result;
		result = request.getPrincipal().hasRole(Inventor.class)&&mines.contains(xx1);
		return result;
	}
	
	@Override
	public void bind(final Request<Xx1> request, final Xx1 entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "xx2", "xx3", "xx4", "xx51", "xx52", "xx52", "xx6", "xx7");

	}
	
	@Override
	public void unbind(final Request<Xx1> request, final Xx1 entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code",  "xx2", "xx3", "xx4", "xx51", "xx52", "xx52", "xx6", "xx7");
		final String item = entity.getItem().getCode();
		model.setAttribute("item", item);
		
	}
	
	@Override
	public Xx1 findOne(final Request<Xx1> request) {
		assert request != null;

		Xx1 result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneXx1(id);

		return result;
	}
	
	@Override
	public void validate(final Request<Xx1> request, final Xx1 entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("xx3")) {
        	final Configuration configuration = this.repository.findConfiguration();
        	final String[] sp = configuration.getWeakSpamTerms().split(",");
        	final List<String> softSpam = new ArrayList<String>(Arrays.asList(sp));
        	final Double softThreshold = configuration.getWeakSpamThreshold();
        	final String[] hp = configuration.getStrongSpamTerms().split(",");
        	final List<String> hardSpam = new ArrayList<String>(Arrays.asList(hp));
        	final Double hardThreshold = configuration.getStrongSpamThreshold();
        	errors.state(request, !spamDetector.isSpam(entity.getXx3(), softSpam, softThreshold, hardSpam, hardThreshold), "xx3", "inventor.xx1.form.error.spam");
        }
		
		if(!errors.hasErrors("xx4")) {
        	final Configuration configuration = this.repository.findConfiguration();
        	final String[] sp = configuration.getWeakSpamTerms().split(",");
        	final List<String> softSpam = new ArrayList<String>(Arrays.asList(sp));
        	final Double softThreshold = configuration.getWeakSpamThreshold();
        	final String[] hp = configuration.getStrongSpamTerms().split(",");
        	final List<String> hardSpam = new ArrayList<String>(Arrays.asList(hp));
        	final Double hardThreshold = configuration.getStrongSpamThreshold();
        	errors.state(request, !spamDetector.isSpam(entity.getXx4(), softSpam, softThreshold, hardSpam, hardThreshold), "xx4", "inventor.xx1.form.error.spam");
        }
		
		
		if(!errors.hasErrors("xx51")) {
        	final Date startPeriod = entity.getXx51();
        	final Calendar calendar = Calendar.getInstance();
        	calendar.setTime(entity.getXx2()); // Aquí no tendremos en cuenta la fecha de actualización, sino de creación
        	calendar.add(Calendar.MONTH, 1);
        	calendar.add(Calendar.SECOND, -1); // Un mes menos un segundo
        	errors.state(request, startPeriod.after(calendar.getTime()), "xx51", "inventor.xx1.form.error.start-period-not-enough");
        }
		
		if(!errors.hasErrors("xx52") && entity.getXx51()!=null) {
        	final Date startPeriod = entity.getXx51();
        	final Date endPeriod = entity.getXx52();
        	final Date moment = new Date(startPeriod.getTime() + 604799999); 
        	errors.state(request, endPeriod.after(moment), "xx52", "inventor.xx1.form.error.end-period-one-week-before-start-period");
        }
		
		if(!errors.hasErrors("xx6")) {
            final String acceptedCurrencies = this.repository.findConfiguration().getAcceptedCurrencies();
            final String[] currencies = acceptedCurrencies.split(",");
            boolean isCorrect = false;
            final String c = entity.getXx6().getCurrency();
            for (final String currency : currencies) {
                if (c.equals(currency)) {
                    isCorrect = true;
                }
            }
            errors.state(request, isCorrect, "xx6", "inventor.xx1.form.error.incorrect-currency");
        }
        
        if(!errors.hasErrors("xx6")) {
            errors.state(request, entity.getXx6().getAmount() >= 0.0, "xx6", "inventor.xx1.form.error.negative-budget");
        }
		
	}
		
	@Override
	public void update(final Request<Xx1> request, final Xx1 entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
