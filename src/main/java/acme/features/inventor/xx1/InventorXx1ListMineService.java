package acme.features.inventor.xx1;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.xx1s.Xx1;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorXx1ListMineService implements AbstractListService<Inventor, Xx1>{
	
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
	public Collection<Xx1> findMany(final Request<Xx1> request) {
		assert request != null;
		
		Collection<Xx1> result;
		Principal principal;

		principal = request.getPrincipal(); 
        result = this.repository.findMineXx1(principal.getUsername());
		return result;
	}

	@Override
	public void unbind(final Request<Xx1> request, final Xx1 entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "xx3");
		
		final String item = entity.getItem().getCode();
		model.setAttribute("item", item);
		
	}
	
}
