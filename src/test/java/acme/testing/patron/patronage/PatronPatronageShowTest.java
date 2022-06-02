package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageShowTest extends TestHarness {

	@ParameterizedTest	
	@CsvFileSource(resources = "/patron/patronage/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff,
		final String budget, final String username, final String fullName, final String email, final String startPeriod,
		final String endPeriod, final String link, final String isPublished) {
		
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "List my patronages");
		
		super.clickOnListingRecord(recordIndex);
		
		final boolean published = Boolean.parseBoolean(isPublished);  
		if (published) {
			super.checkInputBoxHasValue("status", status);
		}
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("fullName", fullName);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// a) Show a patronage from patron/patronage with a role other than "Patron"
		// b) Show a patronage, from other patron, with a role "Patron", that is not published yet
	}
}