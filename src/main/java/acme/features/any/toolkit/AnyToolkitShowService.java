package acme.features.any.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{
	
	//Internal State
	
	@Autowired
	protected AnyToolkitRepository repository;
		
	//AbstractShowService<Any, Item> interface
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int toolkitId;
		Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(toolkitId);
		result = !toolkit.isDraft();
		
		return result; 
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
		
		return result;
	}
	
	@Override
	public void unbind (final Request<Toolkit> request, final Toolkit entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int id;
		id = request.getModel().getInteger("id");
		
		final List<String> currencyList = this.repository.findAllCurrenciesByToolkitId(id);
		
		String retailPrice;
		
		if(currencyList.size()==1) {
			final Double amount = this.repository.findRetailPriceByToolkitId(id);
			retailPrice = currencyList.get(0) + " " + String.format("%.2f", amount);
		}else {
			retailPrice = "null";
		}
		
		model.setAttribute("retailPrice", retailPrice);
		
		final String fullname = entity.getInventor().getUserAccount().getIdentity().getFullName();
		model.setAttribute("fullname", fullname);
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNote", "optionalLink");
	}

}
