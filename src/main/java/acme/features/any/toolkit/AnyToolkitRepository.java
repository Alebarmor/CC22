package acme.features.any.toolkit;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.draft=0")
	Collection<Toolkit> findAllToolkit();
		
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select sum(q.item.retailPrice.amount * q.number) from Quantity q where q.toolkit.id = :id")
	Double findRetailPriceByToolkitId(int id);
	
	@Query("select q.item.retailPrice.currency from Quantity q where q.toolkit.id = :id group by q.item.retailPrice.currency")
	List<String> findAllCurrenciesByToolkitId(int id);
	
	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findManyQuantitiesByToolkitId(int id);

	@Query("select q.item from Quantity q where q.id = :id and q.item.published=1")
	Collection<Item> findManyPublishedItemsByQuantityId(int id);
	
}
