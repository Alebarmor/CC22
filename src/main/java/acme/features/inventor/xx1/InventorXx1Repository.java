package acme.features.inventor.xx1;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.items.Item;
import acme.entities.xx1s.Xx1;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorXx1Repository extends AbstractRepository {
	
	@Query("select x from Xx1 x where x.item.inventor.userAccount.username = :username")
	Collection<Xx1> findMineXx1(String username);
	
	@Query("select x from Xx1 x where x.id = :id")
	Xx1 findOneXx1(int id);
	
	@Query("select x from Xx1 x where x.code = :code")
	Xx1 findXx1ByCode(String code);
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);
	
	@Query("select i.code from Item i where i.published = 1 and i.itemType = 0 and i.inventor.userAccount.username = :username")
	List<String> findAllPossibleItemCodes(String username);
	
	@Query("select x.item.code from Xx1 x where x.item.inventor.userAccount.username = :username")
	List<String> findAllTakenItemCodes(String username);
}
