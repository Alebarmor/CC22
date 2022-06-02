package acme.features.inventor.xx1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.xx1s.Xx1;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorXx1ShowService implements AbstractShowService<Inventor, Xx1>{
	
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
	public Xx1 findOne(final Request<Xx1> request) {
		assert request != null;
		
		Xx1 result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneXx1(id);
		
		return result;
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
