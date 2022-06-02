package acme.testing.anonymous.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnonymousItemShowTest  extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/item/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String name, final String itemType, final String code,  
								final String technology, final String description, final String retailPrice, 
								final String optionalLink, final String fullname) {
		
		super.clickOnMenu("Anonymous", "List All Item");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.checkInputBoxHasValue("fullname", fullname);
		
	}
	
	@Test
    @Order(30)
    public void hackingTest() {
        // a) Show a item from /any/item/list with a role 
    }

}
