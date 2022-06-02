package acme.testing.any.account;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyAccountShowTest extends TestHarness {

	@ParameterizedTest	
	@CsvFileSource(resources = "/any/account/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String username, final String name, final String email,
		final String roles) {
		
		super.clickOnMenu("Anonymous", "List user accounts");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("fullName", name);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("roleList", roles);
	}
}