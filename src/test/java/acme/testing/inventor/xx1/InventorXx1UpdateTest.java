package acme.testing.inventor.xx1;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorXx1UpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/xx1/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String xx3, final String xx4,
		final String xx51, final String xx52, final String xx6, final String xx7) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my xx1");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("xx3", xx3);
		super.fillInputBoxIn("xx4", xx4);
		super.fillInputBoxIn("xx51", xx51);
		super.fillInputBoxIn("xx52", xx52);
		super.fillInputBoxIn("xx6", xx6);
		super.fillInputBoxIn("xx7", xx7);
		
		super.checkSubmitExists("Update");
		super.clickOnSubmit("Update");
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("xx3", xx3);
		super.checkInputBoxHasValue("xx4", xx4);
		super.checkInputBoxHasValue("xx51", xx51);
		super.checkInputBoxHasValue("xx52", xx52);
		super.checkInputBoxHasValue("xx6", xx6);
		super.checkInputBoxHasValue("xx7", xx7);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/xx1/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String xx3, final String xx4,
		final String xx51, final String xx52, final String xx6, final String xx7) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my xx1");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("xx3", xx3);
		super.fillInputBoxIn("xx4", xx4);
		super.fillInputBoxIn("xx51", xx51);
		super.fillInputBoxIn("xx52", xx52);
		super.fillInputBoxIn("xx6", xx6);
		super.fillInputBoxIn("xx7", xx7);
		
		super.checkSubmitExists("Update");
		super.clickOnSubmit("Update");
	
		super.checkNotPanicExists();
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		//		a) update a xx1 with a role other than "Inventor";
	}
}
