package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {

	@ParameterizedTest	
	@CsvFileSource(resources = "/inventor/patronage-report/create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String patronageCode,
		final String memorandum, final String link, final String sequenceNumber) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronage reports");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", patronageCode);
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		super.checkListingExists();
		
		super.clickOnMenu("Inventor", "List my patronage reports");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, sequenceNumber);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest	
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final String patronageCode,
		final String memorandum, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronage reports");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", patronageCode);
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest	
	@CsvFileSource(resources = "/inventor/patronage-report/create-confirmation-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(25)
	public void negativeTestConfirmation(final String patronageCode,
		final String memorandum, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronage reports");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", patronageCode);
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "false");
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.checkNotPanicExists();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
	}
}