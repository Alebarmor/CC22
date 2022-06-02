package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String defaultCurrency, final String acceptedCurrencies,
		final String strongSpamTerms, final String strongSpamThreshold, 
		final String weakSpamTerms, final String weakSpamThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show configuration");
		super.checkFormExists();
		
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");
		
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

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final String defaultCurrency, final String acceptedCurrencies,
		final String strongSpamTerms, final String strongSpamThreshold,
		final String weakSpamTerms, final String weakSpamThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show configuration");
		super.checkFormExists();
		
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.checkNotPanicExists();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/configuration/update");
		super.checkPanicExists();		
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/configuration/update");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/configuration/update");
		super.checkPanicExists();
		super.signOut();
	}
}
