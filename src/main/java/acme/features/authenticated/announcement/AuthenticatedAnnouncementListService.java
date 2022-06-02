package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAnnouncementListService implements AbstractListService<Authenticated, Announcement>{
	
	@Autowired
	protected AuthenticatedAnnouncementRepository ac;


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}


	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;
		Collection<Announcement> result;
		final Calendar c = Calendar.getInstance();
		Date deadline;
		c.add(Calendar.MONTH, -1);
		deadline = c.getTime();
		result = this.ac.findRecentAnnouncements(deadline);
		return result;
	}


	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "creation", "title", "status");
	}
}
