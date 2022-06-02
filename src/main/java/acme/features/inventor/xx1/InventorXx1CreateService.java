package acme.features.inventor.xx1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.items.Item;
import acme.entities.xx1s.Xx1;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import main.spamDetector;

@Service
public class InventorXx1CreateService implements AbstractCreateService<Inventor, Xx1>{
	
	@Autowired
	protected InventorXx1Repository repository;
	 
	@Override
	public boolean authorise(final Request<Xx1> request) {
		assert request != null;
		
		boolean result;
		
		result = request.getPrincipal().hasRole(Inventor.class);
		
		return result;
	}

	@Override
	public void bind(final Request<Xx1> request, final Xx1 entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        request.bind(entity, errors, "code", "xx3", "xx4", "xx51", "xx52", "xx6", "xx7");
        
        final String itemCode = request.getModel().getString("items");
        
        final Item item = this.repository.findOneItemByCode(itemCode);
        entity.setItem(item);

	}
	
	@Override
	public void validate(final Request<Xx1> request, final Xx1 entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        //XXP
        if(!errors.hasErrors("code")) {
            final Date d = entity.getXx2();
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            
            final String[] fecha = entity.getCode().substring(0, 8).split("-");
            final Integer dia = Integer.parseInt(fecha[2]);
            final Integer mes = Integer.parseInt(fecha[1]);
            final Integer anyo = Integer.parseInt(fecha[0]);
            
            final String year = String.valueOf(calendar.get(Calendar.YEAR));
            final char[] digitsYear = year.toCharArray();
            final String ten = digitsYear[2] + "0";
            final String one = digitsYear[0] + "";
            final Integer yearTwoDigits = Integer.parseInt(ten) + Integer.parseInt(one);
            
            final Integer month = calendar.get(Calendar.MONTH) + 1;
            final Integer day = calendar.get(Calendar.DAY_OF_MONTH);

            final Boolean result = (dia.equals(day)) && (mes.equals(month)) && (anyo.equals(yearTwoDigits));
            
            errors.state(request, result, "code", "inventor.xx1.form.error.code-date");
        }
        
        if(!errors.hasErrors("code")) {
        	errors.state(request, this.repository.findXx1ByCode(entity.getCode()) == null, "code", "inventor.xx1.form.error.not-unique");
        }
		
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
        
        if(!errors.hasErrors("items")) {
            final String itemCode = request.getModel().getString("items");
            final Item item = this.repository.findOneItemByCode(itemCode);
        	if (itemCode.equals("No components available")) {
            	errors.state(request, item != null, "items", "inventor.xx1.form.error.item-not-available");
            }
        }
        
        if(!errors.hasErrors("items")) {
            final String itemCode = request.getModel().getString("items");
            final Item item = this.repository.findOneItemByCode(itemCode);
            errors.state(request, !itemCode.equals(""), "items", "inventor.xx1.form.error.item-null");
            if (!itemCode.equals("")) {
                errors.state(request, item != null, "items", "inventor.xx1.form.error.item-does-not-exist");
            }
        }
        
	}

	@Override
	public void unbind(final Request<Xx1> request, final Xx1 entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		String username = request.getPrincipal().getUsername();
		
		List<String> possible = this.repository.findAllPossibleItemCodes(username);
		List<String> taken = this.repository.findAllTakenItemCodes(username);
		List<String> itemList = new ArrayList<>();

		for(String code : possible) {
			if(!taken.contains(code))
				itemList.add(code);
		}

		if(itemList.isEmpty()) {
			itemList.add("No Xx1s available");
		}

		model.setAttribute("items", itemList);
		
		request.unbind(entity, model, "code", "xx3", "xx4", "xx51", "xx52", "xx6", "xx7");
	}
	
	@Override
	public Xx1 instantiate(final Request<Xx1> request) {
		assert request != null;
		
		Xx1 result;
		result = new Xx1();
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setXx2(moment);	
        
		return result;
	}
	
	@Override
	public void create(final Request<Xx1> request, final Xx1 entity) {
		assert request != null;
        assert entity != null;
        
        this.repository.save(entity);
	}
	
}
