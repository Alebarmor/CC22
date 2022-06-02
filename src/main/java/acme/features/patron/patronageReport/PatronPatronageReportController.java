package acme.features.patron.patronageReport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageReportController extends AbstractController<Patron, PatronageReport>{
	
	  @Autowired
	  protected PatronPatronageReportListMineService    listMineService;
	  
	  @Autowired
	  protected PatronPatronageReportShowService    showService;
	  
	  @PostConstruct
	  protected void initialise() {
	        super.addCommand("list-mine", "list", this.listMineService);
	        super.addCommand("show", "show", this.showService);
	    }

}
