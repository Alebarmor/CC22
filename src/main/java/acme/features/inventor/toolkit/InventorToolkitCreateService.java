package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNote", "optionalLink");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNote", "optionalLink", "draft");		
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		Inventor inventor;
		
		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		
		result = new Toolkit();
		result.setDraft(true);
		result.setInventor(inventor);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
		}
		
		if (!errors.hasErrors("title")) {
			errors.state(request, entity.getTitle().length()<101, "title", "inventor.toolkit.form.error.incorrect-title");
		
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, entity.getDescription().length()<256, "description", "inventor.toolkit.form.error.incorrect-description");
		
		}
		
		if (!errors.hasErrors("assemblyNote")) {
		errors.state(request, entity.getAssemblyNote().length()<256, "assemblyNote", "inventor.toolkit.form.error.incorrect-assemblyNote");
		}
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	

}
