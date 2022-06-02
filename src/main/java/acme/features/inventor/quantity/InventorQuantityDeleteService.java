package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorQuantityDeleteService implements AbstractDeleteService<Inventor, Quantity> {

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
        toolkitId= quantity.getToolkit().getId();
        toolkit = this.repository.findOneToolkitById(toolkitId);
        result = toolkit.getInventor().getId() == request.getPrincipal().getActiveRoleId();

        return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "number");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        
        request.unbind(entity, model, "number","item.name","item.retailPrice", "item.technology", "item.itemType", "item.description");
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
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
        assert entity != null;


        this.repository.delete(entity);	
	}
}
