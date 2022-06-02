package acme.features.inventor.quantity;

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
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity> {

	@Autowired
	protected InventorQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		boolean result;
		int toolkitId;
		int quantityId;
		Quantity quantity;
		Toolkit toolkit;

		quantityId = request.getModel().getInteger("id");
		quantity = this.repository.findOneQuantityById(quantityId);
		toolkitId = quantity.getToolkit().getId();
		toolkit = this.repository.findOneToolkitById(toolkitId);

		result = toolkit.getInventor().getId()==request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final MoneyExchange change = this.change(entity.getItem().getRetailPrice());
		model.setAttribute("change", change.getTarget());
		model.setAttribute("toolkitDraft", entity.getToolkit().isDraft());

		request.unbind(entity, model, "number", "item.name","item.retailPrice", "item.technology", "item.itemType", "item.description");
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
		}else {//Si tengo euro euro no necesito change
			change.setSource(money);
			change.setTarget(money);
			change.setCurrencyTarget(configuration.getDefaultCurrency());
			change.setDate(new Date(System.currentTimeMillis()));		
		}
		return change;
	}
}
