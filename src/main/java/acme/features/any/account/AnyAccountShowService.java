package acme.features.any.account;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Anonymous;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractShowService;

@Service
public class AnyAccountShowService implements AbstractShowService<Any, UserAccount>{
	
	//Internal State
	
	@Autowired
	protected AnyAccountRepository repository;
		
	//AbstractShowService<Inventor, Item> interface
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		boolean result;
		int userAccountId;
		UserAccount userAccount; 
		
		userAccountId = request.getModel().getInteger("id");
		userAccount = this.repository.findOneUserAccount(userAccountId);
		result = !userAccount.hasRole(Administrator.class)&&!userAccount.hasRole(Anonymous.class);
			
		return result; 
	}
	
	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		
		UserAccount result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccount(id);
		result.getRoles().forEach(r -> {
		});
		
		return result;
	}
	
	@Override
	public void unbind (final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "username");
		
		final String fullName = entity.getIdentity().getFullName();
        model.setAttribute("fullName", fullName);
        
        final String email = entity.getIdentity().getEmail();
        model.setAttribute("email", email);
        
        StringBuilder buffer;
        Collection<UserRole> roles;

		roles = entity.getRoles();
		buffer = new StringBuilder();
		for (final UserRole role : roles) {
			buffer.append(role.getAuthorityName());
			buffer.append(" ");
		}

		model.setAttribute("roleList", buffer.toString());
	}

}
