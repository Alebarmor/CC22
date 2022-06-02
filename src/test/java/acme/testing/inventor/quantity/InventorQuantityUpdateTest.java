package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorQuantityUpdateTest extends TestHarness{
	
	// Lifecycle managment 
	
	// Test cases
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String number, final String itemType, final String description,
								final String name, final String technology, final String retailPrice) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();

		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Items");
		
		super.sortListing(2, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("number", number);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Items");
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, number);
		super.checkColumnHasValue(recordIndex, 1, itemType);
		super.checkColumnHasValue(recordIndex, 2, name);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, retailPrice);
		
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("number", number);
		super.checkInputBoxHasValue("item.name", name);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("item.technology", technology);
		super.checkInputBoxHasValue("item.itemType", itemType);
		super.checkInputBoxHasValue("item.description", description);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String number) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();

		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Items");
		
		super.sortListing(2, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("number", number);
		super.clickOnSubmit("Update");
		
		super.checkNotPanicExists();
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: the framework doesn't provide enough support to implement this test case,
		// so it must be performed manually:
		//		a) update quantity with a role other than "Inventor";
	}
}
