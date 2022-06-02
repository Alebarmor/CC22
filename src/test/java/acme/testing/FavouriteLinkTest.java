/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FavouriteLinkTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void favouriteLink() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "15414656X: Salgado Bravo, Laura");		
		super.checkCurrentUrl("https://www.youtube.com");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "49133636R: Barba Moreno, Alejandro");		
		super.checkCurrentUrl("https://www.youtube.com/watch?v=GQt1-1oPrOM");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "15427850W: Sanchez Hossdorf, Alexander");		
		super.checkCurrentUrl("https://www.youtube.com/watch?v=eSZFIXbqmhs&list=PLNQtd15ZC-ZyZKruTodo-4I3_kfoGaDEq&index=2&t=3241s");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "47563095S: Pardo Pastor, Carlos");		
		super.checkCurrentUrl("https://www.youtube.com/watch?v=fJ9rUzIMcZQ");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "29539112M: Silva Leon, Fernando");		
		super.checkCurrentUrl("https://www.powerpyx.com");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "29533279Z: Garcia Lergo, Horacio");		
		super.checkCurrentUrl("https://www.youtube.com/watch?v=K3Qzzggn--s");
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
