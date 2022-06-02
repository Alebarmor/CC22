package acme.features.inventor.xx1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.xx1s.Xx1;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorXx1DeleteService implements AbstractDeleteService<Inventor, Xx1>{
	
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
        
        request.bind(entity, errors, "xx2", "xx3", "xx4", "xx51", "xx52", "xx52", "xx6", "xx7");
		
	}
	
	@Override
	public void validate(final Request<Xx1> request, final Xx1 entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
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
	public void delete(final Request<Xx1> request, final Xx1 entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

	@Override
	public void unbind(final Request<Xx1> request, final Xx1 entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "xx2", "xx3", "xx4", "xx51", "xx52", "xx6", "xx7");
		
		final String item = entity.getItem().getCode();
		model.setAttribute("item", item);
		
	}
	
}
