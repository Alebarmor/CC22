package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.features.any.toolkit.AnyToolkitItemListService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item>{

	//Internal state 
	
	@Autowired
	protected AnyItemListService		listService;
	
	@Autowired
	protected AnyItemShowService		showService;
	
	@Autowired
	protected AnyToolkitItemListService	anyToolkitItemListService;

	
	//Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		//Esto es para mostrar los items publicados de un toolkit
		super.addCommand("list-toolkit", "list", this.anyToolkitItemListService);
	}
	
	
}
