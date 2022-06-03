package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Status;
import acme.entities.items.ItemType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{

	@Query("select count(i) from Item i where i.itemType = :type")
	int numberOfItem(ItemType type);
	@Query("select count(p) from Patronage p where p.status = :status")
	int numberOfStatusPatronages(Status status);
	
	@Query("select i.technology, i.retailPrice.currency, avg(i.retailPrice.amount), stddev(i.retailPrice.amount), min(i.retailPrice.amount), max(i.retailPrice.amount) from Item i where i.itemType = :type group by i.technology, i.retailPrice.currency")
	List<Object[]> statsRetailPriceOfItem(ItemType type);
	
	@Query("select p.status, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> statsBudgetOfStatusPatronages();
}
	