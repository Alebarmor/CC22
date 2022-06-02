package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportShowTest extends TestHarness {

	@ParameterizedTest	
	@CsvFileSource(resources = "/inventor/patronage-report/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String sequenceNumber, final String creationMoment,
		final String memorandum, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronage reports");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
//	@Test
//	@Order(30)
//	public void hackingTest() {
//		super.checkNotLinkExists("Account");
//		super.navigate("/inventor/patronage-report/show?id=82");
//		super.checkPanicExists();
//
//		super.signIn("administrator", "administrator");
//		super.navigate("/inventor/patronage-report/show?id=82");
//		super.checkPanicExists();
//		super.signOut();
//
//		super.signIn("patron1", "patron1");
//		super.navigate("/inventor/patronage-report/show?id=82");
//		super.checkPanicExists();
//		super.signOut();
//		
//		super.signIn("inventor2", "inventor2");
//		super.navigate("/inventor/patronage-report/show?id=82");
//		super.checkPanicExists();
//		super.signOut();
//	}
}