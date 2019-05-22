package com.testAutomation.StepDef;

import java.util.Properties;

import com.testAutomation.Utility.PropertiesFileReader;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class HomePageStepDef {

	PropertiesFileReader prop = new PropertiesFileReader();
	
	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
	    Properties properties =new Properties();
	    properties.getProperty("browser.baseURL");
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String arg1) throws Throwable {
	    
	}

	@When("^choose to buy the first item$")
	public void choose_to_buy_the_first_item() throws Throwable {
	    	}

	@When("^moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart() throws Throwable {
	    
	}

	@When("^enter personal details on checkout page$")
	public void enter_personal_details_on_checkout_page() throws Throwable {
	   
	}

	@When("^select same delivery address$")
	public void select_same_delivery_address() throws Throwable {
	    
	}

	@When("^place the order$")
	public void place_the_order() throws Throwable {
	    
	}
	
}
