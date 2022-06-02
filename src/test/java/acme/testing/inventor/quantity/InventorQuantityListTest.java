package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorQuantityListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String number, final String itemType, final String name,
								final String technology, final String retailPrice) {
		
		super.signIn("inventor1", "inventor1");
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
		
		super.signOut();
	}
	
}
