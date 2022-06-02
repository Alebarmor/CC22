package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{
	
	//Internal State
	
	@Autowired
	protected InventorToolkitRepository repository;
		
	//AbstractShowService<Inventor, Toolkit> interface
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		 boolean result;
		 int toolkitId;
		 Toolkit toolkit;
		 
		 toolkitId = request.getModel().getInteger("id");
		 toolkit = this.repository.findOneToolkitById(toolkitId);
		 result = toolkit.getInventor().getId()==request.getPrincipal().getActiveRoleId();
		
		return result; 
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
		result.setRetailPrice(this.totalPriceOfToolkit(id));
		
		return result;
	}
	

	@Override
	public void unbind (final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String retailPrice = entity.getRetailPrice().toString().replace("<<", "").replace(">>", "");
		
		
		model.setAttribute("retailPrice", retailPrice);
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNote", "optionalLink", "draft");
	}

	protected MoneyExchange change(final Money money) {
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		MoneyExchange change = new MoneyExchange();
		final Configuration configuration = this.repository.findConfiguration();
		
		if(!money.getCurrency().equals(configuration.getDefaultCurrency())) { //money usd y lo otro eur
			change = this.repository.findMoneyExchageByCurrencyAndAmount(money.getCurrency(),money.getAmount());//comprobar si esta en la cache
			if(change == null) {//no el precio es 0 necesito esto para que no pete
				change = moneyExchange.computeMoneyExchange(money, configuration.getDefaultCurrency());
				this.repository.save(change); // y la guardo en bbdd
			}
		}else {//Si tengo euro euro no necesito conversion
			change.setSource(money);
			change.setTarget(money);
			change.setCurrencyTarget(configuration.getDefaultCurrency());
			change.setDate(new Date(System.currentTimeMillis()));		
		}
		return change;
	}
	
	private Money totalPriceOfToolkit(final int toolkitId) {
        final Money result = new Money();
        result.setAmount(0.0);
        result.setCurrency("EUR");

        final Collection<Quantity> quantities = this.repository.findManyQuantitiesByToolkitId(toolkitId);

        for(final Quantity quantity: quantities) {
            final double changeAmount;
            final Money itemMoney = quantity.getItem().getRetailPrice();
            final int number = quantity.getNumber();

            changeAmount = this.change(itemMoney).getTarget().getAmount();

            final Double newAmount = (double) Math.round((result.getAmount() + changeAmount*number)*100)/100;
            result.setAmount(newAmount);
        }

        return result;
    }
}