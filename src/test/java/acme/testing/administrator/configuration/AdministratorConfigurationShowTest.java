package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String defaultCurrency, final String acceptedCurrencies, final String strongSpamTerms,  
		final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();		
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();
		super.signOut();
	}
}
