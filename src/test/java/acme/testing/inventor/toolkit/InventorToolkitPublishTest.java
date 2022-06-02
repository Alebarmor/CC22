package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness{
	
	// Lifecycle managment 
	
	// Test cases
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 2, "true");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.clickOnSubmit("Publish draft");
		super.checkNotErrorsExist();
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 2, "false");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkNotButtonExists("Publish draft");

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: the framework doesn't provide enough support to implement this test case,
		// so it must be performed manually:
		//		a) publish a draft toolkit with a role other than "Inventor";
		//		b) publish a non draft toolkit that was registered by the principal;
		//		c) publish a non draft toolkit that wasn't registered by the principal;
		//		d) publish a draft toolkit that wasn't registered by the principal.
	}
}
