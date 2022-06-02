package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findManyQuantitiesByToolkitId(int id);

	@Query("select q.item from Quantity q where q.id = :id")
	Collection<Item> findManyItemsByQuantityId(int id);
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
    MoneyExchange findMoneyExchageByCurrencyAndAmount(String currency, Double amount);

	@Query("select q from Quantity q where q.id=:quantityId")
	Quantity findOneQuantityById(int quantityId);

	@Query("select i from Item i where i.published = 1 and i not in (select q.item from Quantity q where q.toolkit.id = :id)")//selecciono todos los item en modo published y no este el la toolkit que he agregado
	Collection<Item> findAllIPossibleItems(int id);

	@Query("select i from Item i where i.id = :itemId")
	Item finOneItemById(int itemId);	
}
