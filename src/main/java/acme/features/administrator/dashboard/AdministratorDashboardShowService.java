package acme.features.administrator.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.entities.items.ItemType;
import acme.forms.AdministratorDashboard;
import acme.forms.Stats;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	
	//Internal State
	
	@Autowired
	protected AdministratorDashboardRepository repository;
			
	//AbstractShowService<Administrator, AdministratorDashboard>
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		return true; 
	}
	
	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		final AdministratorDashboard result = new AdministratorDashboard();
		
		final int numberOfComponents = this.repository.numberOfItem(ItemType.COMPONENT);
		final int numberOfTools = this.repository.numberOfItem(ItemType.TOOL);
		final int numberOfPropsedPatronages = this.repository.numberOfStatusPatronages(Status.PROPOSED);
		final int numberOfAcceptedPatronages = this.repository.numberOfStatusPatronages(Status.ACCEPTED);
		final int numberOfDeniedPatronages =  this.repository.numberOfStatusPatronages(Status.DENIED);
		
		final Map<Pair<String,String>, Stats> statsRetailPriceOfComponents = new HashMap<>();
		final List<Object[]> listStatsRetailPriceOfComponents = this.repository.statsRetailPriceOfItem(ItemType.COMPONENT);
		
		for (int i=0; i<listStatsRetailPriceOfComponents.size(); i++) {
			final Object[] linea = listStatsRetailPriceOfComponents.get(i);
			final Pair<String, String> pareja = Pair.of((String)(linea[0]), (String)(linea[1]));
			final Stats stat = new Stats();
			stat.setAverage((Double)(linea[2]));
			stat.setDeviation((Double)(linea[3]));
			stat.setMinumun((Double)(linea[4]));
			stat.setMaximun((Double)(linea[5]));
			
			statsRetailPriceOfComponents.put(pareja, stat);
		}
		
		
		final Map<Pair<String,String>, Stats> statsRetailPriceOfTools = new HashMap<>();
		final List<Object[]> listStatsRetailPriceOfTools = this.repository.statsRetailPriceOfItem(ItemType.TOOL);
		
		for (int i=0; i<listStatsRetailPriceOfTools.size(); i++) {
			final Object[] linea = listStatsRetailPriceOfTools.get(i);
			final Pair<String, String> pareja = Pair.of((String)linea[0], (String)linea[1]);
			final Stats stat = new Stats();
			stat.setAverage((Double)(linea[2]));
			stat.setDeviation((Double)(linea[3]));
			stat.setMinumun((Double)(linea[4]));
			stat.setMaximun((Double)(linea[5]));
			
			statsRetailPriceOfTools.put(pareja, stat);
		}
		
		
		final EnumMap<Status,Stats> statsBudgetOfStatusPatronages = new EnumMap<>(Status.class);
		final List<Object[]> listStatsBudgetOfStatusPatronages = this.repository.statsBudgetOfStatusPatronages();
		
		for (int i=0; i<listStatsBudgetOfStatusPatronages.size(); i++) {
			final Object[] arrayList = listStatsBudgetOfStatusPatronages.get(i);
			final Stats stat = new Stats();
			stat.setAverage((Double)(arrayList[1]));
			stat.setDeviation((Double)(arrayList[2]));
			stat.setMinumun((Double)(arrayList[3]));
			stat.setMaximun((Double)(arrayList[4]));
			
			statsBudgetOfStatusPatronages.put((Status)arrayList[0], stat);
		}
		

		result.setNumberOfComponents(numberOfComponents);
		result.setNumberOfTools(numberOfTools);
		result.setStatsRetailPriceOfComponents(statsRetailPriceOfComponents);
		result.setStatsRetailPriceOfTools(statsRetailPriceOfTools);
		
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfPropsedPatronages(numberOfPropsedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStatsBudgetOfStatusPatronages(statsBudgetOfStatusPatronages);
		
		//Xx1
		
		//Tool
		final double ratioOfItemWithXx1 = this.repository.ratioOfItemWithXx1(ItemType.TOOL);
		//Component
		//final double ratioOfItemWithXx1 = this.repository.ratioOfItemWithXx1(ItemType.COMPONENT);
		
		final Map<String, Stats> statsXx6OfXx1 = new HashMap<>();
		final List<Object[]> listStatsXx6OfXx1 = this.repository.statsXx6OfXx1();
		
		for (int i=0; i<listStatsXx6OfXx1.size(); i++) {
			final Object[] arrayList = listStatsXx6OfXx1.get(i);
			final Stats stat = new Stats();
			stat.setAverage((Double)(arrayList[1]));
			stat.setDeviation((Double)(arrayList[2]));
			stat.setMinumun((Double)(arrayList[3]));
			stat.setMaximun((Double)(arrayList[4]));
			
			statsXx6OfXx1.put((String)arrayList[0], stat);
		}
		
		result.setRatioOfItemWithXx1(ratioOfItemWithXx1);
		result.setStatsXx6OfXx1(statsXx6OfXx1);
		
		return result;
	}
	
	@Override
	public void unbind (final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, 	"numberOfComponents", 
										"numberOfTools", 
										"statsRetailPriceOfComponents", 
										"statsRetailPriceOfTools", 
										"numberOfPropsedPatronages", 
										"numberOfAcceptedPatronages", 
										"numberOfDeniedPatronages", 
										"statsBudgetOfStatusPatronages",
										"ratioOfItemWithXx1",
										"statsXx6OfXx1");
	}
}
