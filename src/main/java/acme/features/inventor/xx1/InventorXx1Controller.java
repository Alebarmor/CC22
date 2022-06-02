package acme.features.inventor.xx1;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.xx1s.Xx1;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorXx1Controller extends AbstractController<Inventor, Xx1>{
	
	  @Autowired
	  protected InventorXx1ListMineService    listMineService;
	  
	  @Autowired
	  protected InventorXx1ShowService    showService;
	  
	  @Autowired
	  protected InventorXx1CreateService createService;
	  
	  @Autowired
	  protected InventorXx1UpdateService updateService;
	  
	  @Autowired
	  protected InventorXx1DeleteService deleteService;
	  
	  @PostConstruct
	  protected void initialise() {
	        super.addCommand("list-mine", "list", this.listMineService);
	        super.addCommand("show", this.showService);
	        super.addCommand("create", this.createService);
	        super.addCommand("update", this.updateService);
	        super.addCommand("delete", this.deleteService);
	    }

}
