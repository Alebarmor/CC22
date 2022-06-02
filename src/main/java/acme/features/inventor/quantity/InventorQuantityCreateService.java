package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.ItemType;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService  implements AbstractCreateService <Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		 boolean result;
		 int masterId;
		 Toolkit toolkit;
		 
		 masterId = request.getModel().getInteger("masterId");
		 toolkit = this.repository.findOneToolkitById(masterId);
		 result = toolkit != null && toolkit.isDraft() && request.isPrincipal(toolkit.getInventor());
		 
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        if(this.repository.findAllIPossibleItems(entity.getToolkit().getId()).isEmpty()) {
            request.bind(entity, errors, "number");
        } else {
            entity.setItem(this.repository.finOneItemById(Integer.valueOf( request.getModel().getAttribute("itemId").toString()))); // Le pasamos el id del selector del formulario NEcesito pasar de object a integer
            request.bind(entity, errors, "number", "itemId");
        }
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        
        model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
        model.setAttribute("items", this.repository.findAllIPossibleItems(entity.getToolkit().getId()));
        
        request.unbind(entity, model, "number");
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		Quantity quantity;
		int toolkitId;
		Toolkit toolkit;
		
		quantity = new Quantity();

        toolkitId = request.getModel().getInteger("masterId");
        toolkit = this.repository.findOneToolkitById(toolkitId);

        quantity.setToolkit(toolkit);
        
        return quantity;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
        assert errors != null;

        if(entity.getItem() == null) {
            errors.state(request, entity.getItem() != null, "itemId", "inventor.quantity.form.error.noItem");
        } else {
            final Item selectedItem = this.repository.finOneItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString()));
            if(selectedItem.getItemType().equals(ItemType.TOOL)) {
                errors.state(request,entity.getNumber() == 1, "number", "inventor.quantity.form.error.toolkit-has-tool");
            }
        }
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
        assert entity != null;

        this.repository.save(entity);	
	}
}
