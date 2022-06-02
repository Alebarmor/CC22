package acme.features.authenticated.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedConfigurationShowService implements AbstractShowService<Authenticated,Configuration>{
	
	@Autowired
	protected AdministratorConfigurationRepository repository;
	
	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		return true;
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;
		Configuration result = null;
		final Optional<Configuration> resOpt = this.repository.findConfiguration().stream().findFirst();
		if(resOpt.isPresent()) {
			result = resOpt.get();
		}
		return result;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "defaultCurrency","acceptedCurrencies");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}
}
