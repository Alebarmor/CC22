
package acme.features.patron.patronDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Patronage p where p.status = ':status'")
	int numberOfStatusPatronages(String status);

	@Query("select p.status, p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p group by p.status, p.budget.currency")
	List<Object[]> statsBudgetOfStatusPatronages();

}
