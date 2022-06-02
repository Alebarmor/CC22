package acme.features.administrator.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration> {

	@Autowired
		protected AdministratorConfigurationRepository repository;
	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "defaultCurrency","acceptedCurrencies","strongSpamTerms","strongSpamThreshold","weakSpamTerms","weakSpamThreshold");
		
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "defaultCurrency","acceptedCurrencies","strongSpamTerms","strongSpamThreshold","weakSpamTerms","weakSpamThreshold");
		
		
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
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("defaultCurrency")) {
			errors.state(request, entity.getAcceptedCurrencies().contains(entity.getDefaultCurrency()), "defaultCurrency", "administrator.configuration.default-is-not-accepted");
		}
		
		if(!errors.hasErrors("acceptedCurrencies")) {
			final String acceptedCurrencies = entity.getAcceptedCurrencies();
			final String[] currencies = acceptedCurrencies.split(",");
			boolean isCorrect = true;
			
			for (int i = 0; i < currencies.length; i++){
				isCorrect = currencies[i].matches("^[A-Z]{3}$");
				
			    if (!isCorrect) {
			    	break;
			    }
			}
			errors.state(request, isCorrect, "acceptedCurrencies", "administrator.configuration.accepted-not-correct");
		}
	}

	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
		
	}
}
