package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item>{
	
	@Autowired
	protected InventorItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name","itemType", "code", "technology", "description", "retailPrice", "optionalLink");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name","itemType", "code", "technology", "description", "retailPrice", "optionalLink");		
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		
		Item result;
		Inventor inventor;
		
		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		
		result = new Item();
		result.setPublished(false);
		result.setInventor(inventor);
		
		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findOneItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.item.form.error.duplicated");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "inventor.item.form.error.negative-retailPrice");
		}
		
		if(!errors.hasErrors("retailPrice")) {
        	final String acceptedCurrencies = this.repository.findConfiguration().getAcceptedCurrencies();
            final String[] currencies = acceptedCurrencies.split(",");
            boolean isCorrect = false;
            final String rp = entity.getRetailPrice().getCurrency();
            for (final String currency : currencies) {
            	if (rp.equals(currency)) {
            		isCorrect = true;
            	}
			}
        	errors.state(request, isCorrect, "retailPrice", "inventor.item.form.error.incorrect-currency");
        }
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	

}
