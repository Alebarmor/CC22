package acme.testing.authenticated.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedConfigurationShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String defaultCurrency, final String acceptedCurrencies) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Show Configuration");
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();
	}
}
