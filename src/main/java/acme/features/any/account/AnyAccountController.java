package acme.features.any.account;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;

@Controller
public class AnyAccountController extends AbstractController<Any, UserAccount>{
	
	  @Autowired
	    protected AnyAccountListService    listService;
	  
	  @Autowired
	    protected AnyAccountShowService    showService;
	  
	  @PostConstruct
	    protected void initialise() {
	        super.addCommand("list", this.listService);
	        super.addCommand("show", this.showService);
	    }

}
