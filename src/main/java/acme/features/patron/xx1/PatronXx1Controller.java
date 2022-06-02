package acme.features.patron.xx1;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.xx1s.Xx1;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronXx1Controller extends AbstractController<Patron, Xx1>{
	
	  @Autowired
	  protected PatronXx1ListMineService    listMineService;
	  
	  @Autowired
	  protected PatronXx1ShowService    showService;
	  
	  @Autowired
	  protected PatronXx1CreateService createService;
	  
	  @Autowired
	  protected PatronXx1UpdateService updateService;
	  
	  @Autowired
	  protected PatronXx1DeleteService deleteService;
	  
	  @PostConstruct
	  protected void initialise() {
	        super.addCommand("list-mine", "list", this.listMineService);
	        super.addCommand("show", this.showService);
	        super.addCommand("create", this.createService);
	        super.addCommand("update", this.updateService);
	        super.addCommand("delete", this.deleteService);
	    }

}
