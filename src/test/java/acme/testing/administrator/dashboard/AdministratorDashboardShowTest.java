package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class AdministratorDashboardShowTest  extends TestHarness{
	
	//@ParameterizedTest
	@Test
//	@CsvFileSource(resources = "/administrator/item/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest() {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		super.checkNotPanicExists();
		
		super.signOut();
	}

}
