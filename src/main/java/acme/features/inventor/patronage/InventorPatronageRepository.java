package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {
	
	@Query("select p from Patronage p where p.inventor.userAccount.username = :username and p.notPublished = false")
	Collection<Patronage> findMinePatronages(String username);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronage(int id);

}
