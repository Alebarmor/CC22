package acme.features.administrator.announcement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAnnouncementRepository extends AbstractRepository {
	
	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(int id);
}
