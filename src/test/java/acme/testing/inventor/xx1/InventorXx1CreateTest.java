package acme.testing.inventor.xx1;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorXx1CreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/xx1/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTest (final int recordIndex, final String code, 
		final String items, final String xx3, final String xx4, final String xx51, final String xx52,
		final String xx6, final String xx7) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my xx1");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(moment);
		
        final String year = String.valueOf(calendar.get(Calendar.YEAR));
        final char[] digitsYear = year.toCharArray();
        final String ten = digitsYear[2] + "0";
        final String one = digitsYear[0] + "";
        final Integer yearTwoDigits = Integer.parseInt(ten) + Integer.parseInt(one);
        
        final Integer month = calendar.get(Calendar.MONTH) + 1;
        final Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        
        String monthS = String.valueOf(month);
        if (month <= 9) {
        	monthS = "0" + monthS;
        }
        
        String dayS = String.valueOf(day);
        if (day <= 9) {
        	dayS = "0" + dayS;
        }
        
        final String codeToday = yearTwoDigits + "-" + monthS + "-" + dayS + ":";
        
        super.fillInputBoxIn("code", codeToday + code);
		super.fillInputBoxIn("items", items);
		super.fillInputBoxIn("xx3", xx3);
		super.fillInputBoxIn("xx4", xx4);
		super.fillInputBoxIn("xx51", xx51);
		super.fillInputBoxIn("xx52", xx52);
		super.fillInputBoxIn("xx6", xx6);
		super.fillInputBoxIn("xx7", xx7);
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", codeToday + code);
		super.checkInputBoxHasValue("item", items);
		super.checkInputBoxHasValue("xx3", xx3);
		super.checkInputBoxHasValue("xx4", xx4);
		super.checkInputBoxHasValue("xx51", xx51);
		super.checkInputBoxHasValue("xx52", xx52);
		super.checkInputBoxHasValue("xx6", xx6);
		super.checkInputBoxHasValue("xx7", xx7);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/xx1/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final String code, 
		final String items, final String xx3, final String xx4, final String xx51, final String xx52,
		final String xx6, final String xx7) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my xx1");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(moment);
		
        final String year = String.valueOf(calendar.get(Calendar.YEAR));
        final char[] digitsYear = year.toCharArray();
        final String ten = digitsYear[2] + "0";
        final String one = digitsYear[0] + "";
        final Integer yearTwoDigits = Integer.parseInt(ten) + Integer.parseInt(one);
        
        final Integer month = calendar.get(Calendar.MONTH) + 1;
        final Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        
        String monthS = String.valueOf(month);
        if (month <= 9) {
        	monthS = "0" + monthS;
        }
        
        String dayS = String.valueOf(day);
        if (day <= 9) {
        	dayS = "0" + dayS;
        }
        
        final String codeToday = yearTwoDigits + "-" + monthS + "-" + dayS + ":";
		
		super.fillInputBoxIn("code", codeToday + code);
		super.fillInputBoxIn("items", items);
		super.fillInputBoxIn("xx3", xx3);
		super.fillInputBoxIn("xx4", xx4);
		super.fillInputBoxIn("xx51", xx51);
		super.fillInputBoxIn("xx52", xx52);
		super.fillInputBoxIn("xx6", xx6);
		super.fillInputBoxIn("xx7", xx7);
		super.clickOnSubmit("Create");
	
		super.checkNotPanicExists();
		super.checkErrorsExist();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/xx1/create-negative-date.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void negativeTestDate(final String code, 
		final String items, final String xx3, final String xx4, final String xx51, final String xx52,
		final String xx6, final String xx7) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my xx1");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		
        final String codeToday = "99-02-01:";
		
		super.fillInputBoxIn("code", codeToday + code);
		super.fillInputBoxIn("items", items);
		super.fillInputBoxIn("xx3", xx3);
		super.fillInputBoxIn("xx4", xx4);
		super.fillInputBoxIn("xx51", xx51);
		super.fillInputBoxIn("xx52", xx52);
		super.fillInputBoxIn("xx6", xx6);
		super.fillInputBoxIn("xx7", xx7);
		super.clickOnSubmit("Create");
	
		super.checkNotPanicExists();
		super.checkErrorsExist();

		super.signOut();
	}
	
    @Test
    @Order(30)
    public void hackingTest() {
        super.checkNotLinkExists("Account");
        super.navigate("/inventor/xx1/create");
        super.checkPanicExists();

        super.signIn("administrator", "administrator");
        super.navigate("/inventor/xx1/create");
        super.checkPanicExists();
        super.signOut();

        super.signIn("patron1", "patron1");
        super.navigate("/inventor/xx1/create");
        super.checkPanicExists();
        super.signOut();
    }
}