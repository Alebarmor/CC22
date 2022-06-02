package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementShowTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String title, final String status, final String body,  
								final String link) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("link", link);
		
		
		super.signOut();
	}
	
	 @Test
	    @Order(30)
	    public void hackingTest() {
	        // a) Show a announcement  with no role
	    }
	   
}
