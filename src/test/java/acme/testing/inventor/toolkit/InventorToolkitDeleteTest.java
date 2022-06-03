package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String code, final String title, final String description,  
		final String assemblyNote, final String optionalLink) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNote", assemblyNote);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.clickOnSubmit("Delete");
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		
		super.checkNotButtonExists("Delete");
		
		super.signOut();
	}

}
