package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitShowTest  extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String code, final String title, final String description, 
								final String assemblyNote, final String retailPrice, final String optionalLink, final String fullname) {
		
		super.clickOnMenu("Anonymous", "List All Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNote", assemblyNote);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.checkInputBoxHasValue("fullname", fullname);
		
	}

	
	@Test
	@Order(30)
	public void hackingTest() {
		//There is no possible hacking
	}
	
}
