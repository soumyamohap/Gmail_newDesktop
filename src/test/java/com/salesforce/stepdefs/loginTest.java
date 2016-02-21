package com.salesforce.stepdefs;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.salesforce.constants.ConstantsDeclaration;
import com.salesforce.utils.WebConnector;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class loginTest {
	WebConnector ob=new WebConnector();
	
	@Given("^I am in sales force login page in \"(.*)\"$")
	public void I_am_in_sales(String browsertype)
	{
		ob.openBrowser(browsertype);
		ob.navigate(ConstantsDeclaration.URL);
		ob.navigate(ConstantsDeclaration.URL);
		ob.click(ConstantsDeclaration.LOCATOR_TYPE_ID,ConstantsDeclaration.LOGIN_LINK);
		
	}
	@When("^I enter the \"(.*)\"$")
	public void I_enter(String inputfield)
	{
		
		ob.inputData(ConstantsDeclaration.LOCATOR_TYPE_ID,inputfield);
		
	}
	@And("^I select \"(.*)\" button$")
	public void I_select(String button)
	{
		ob.click(ConstantsDeclaration.LOCATOR_TYPE_ID,button);
	}

}
