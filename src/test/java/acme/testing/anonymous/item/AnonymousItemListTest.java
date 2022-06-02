package acme.testing.anonymous.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnonymousItemListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/item/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String itemType,
								final String code, final String technology, final String retailPrice, final String fullname) {
		
		super.checkNotLinkExists("Account");
		super.clickOnMenu("Anonymous", "List All Item");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, itemType);
		super.checkColumnHasValue(recordIndex, 2, code);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, retailPrice);
		super.checkColumnHasValue(recordIndex, 5, fullname);
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		//There is no possible hacking
	}
	
}
