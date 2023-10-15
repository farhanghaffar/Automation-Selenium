package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Admin;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.properties.OR_Setup;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Admin;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_On_All_Navigation;
import com.innroad.inncenter.webelements.Elements_SetUp_Properties;
import com.relevantcodes.extentreports.ExtentTest;

public class Properties {

	public static Logger propertylogger = Logger.getLogger("Properties");
	public ArrayList<String> LongStay(WebDriver driver, String PropertyName ,String NgigtValue,ArrayList<String> test_steps) throws InterruptedException {


		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.Propeties);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Propeties), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Propeties), driver);
		all_Navigation.Propeties.click();
		test_steps.add("Click on properties");

		WebElement property = driver.findElement(By.xpath("//a[text()='"+PropertyName+"']"));
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Propeties, driver);


		Wait.explicit_wait_visibilityof_webelement(property, driver);
		property.click();
		test_steps.add("open existing property");

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		test_steps.add("Click on options");
		Wait.wait2Second();

		Utility.ScrollToElement(all_Navigation.ChkLongerthan,driver);

		if (!all_Navigation.ChkLongerthan.isSelected()) {
			all_Navigation.ChkLongerthan.click();
		}
		else{
			test_steps.add("Reservation longer then checkbox already checked");
		}

		all_Navigation.Enter_txttaxexempt.clear();
		all_Navigation.Enter_txttaxexempt.sendKeys(NgigtValue);
		test_steps.add("Enter night value : "+NgigtValue);

		all_Navigation.Properties_Save.click();
		test_steps.add("Click on save property button");

		return test_steps;
	}

	public void SaveButton(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		all_Navigation.Properties_Save.click();
		// test_steps.add("Click Save");
		Utility.app_logs.info("Clicked on Save button");
		Wait.wait2Second();
	}

	public void PublishButton(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.wait5Second();
		all_Navigation.Property_Publish_Button.click();
		// test_steps.add("Click Publish");
		Utility.app_logs.info("Clicked on Publish");
		Wait.wait2Second();
	}

	public void SearchProperty(WebDriver driver, String PropertyName) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		String path = "//a[.=" + "'" + PropertyName + "'" + "]";
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(path));
		} catch (Exception e) {
			String lastPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[last()]";
			Utility.ScrollToElement(driver.findElement(By.xpath(lastPagePath)), driver);
			driver.findElement(By.xpath(lastPagePath)).click();
			Utility.app_logs.info("Move to last Page ");
			element = driver.findElement(By.xpath(path));
		}
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		String Property_Name = element.getText();
		assertEquals(Property_Name, PropertyName);
		element.click();

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.ChkLongerthan, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", all_Navigation.ChkLongerthan);

		// verify check box is checked?
		if (all_Navigation.ChkLongerthan.isSelected()) {

			// do nothing

		} else {
			all_Navigation.ChkLongerthan.click();
			all_Navigation.Enter_txttaxexempt.sendKeys("2");///take value from excel

		}
	}

	public void SearchProperty(WebDriver driver, String PropertyName,String TaxEmptext) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		String path = "//a[.=" + "'" + PropertyName + "'" + "]";
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(path));
		} catch (Exception e) {
			String lastPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[last()]";
			Utility.ScrollToElement(driver.findElement(By.xpath(lastPagePath)), driver);
			driver.findElement(By.xpath(lastPagePath)).click();
			Utility.app_logs.info("Move to last Page ");
			element = driver.findElement(By.xpath(path));
		}
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		Utility.ScrollToElement(element, driver);
		String Property_Name = element.getText();
		assertEquals(Property_Name, PropertyName);
		element.click();

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.ChkLongerthan, driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", all_Navigation.ChkLongerthan);

		// verify check box is checked?
		if (all_Navigation.ChkLongerthan.isSelected()) {

			// do nothing

		} else {
			all_Navigation.ChkLongerthan.click();
			all_Navigation.Enter_txttaxexempt.sendKeys(TaxEmptext);
			Utility.app_logs.info("Enter Tax Exempt Text Value : " + TaxEmptext);
		}
	}

	public ArrayList<String> SearchProperty_Click(WebDriver driver, String PropName, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.CreatedProperty_Pages);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.CreatedProperty_Pages)), driver);		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(OR.CreatedProperty_Pages)));
		int Size = driver.findElements(By.xpath(OR.CreatedProperty_Pages)).size();
		if (Size > 2) {
			System.out.println("Total Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.PropertyName_List);
			int count = driver.findElements(By.xpath(OR.PropertyName_List)).size();
			System.out.println(count);

			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				WebElement Property = driver.findElement(By.xpath("(" + OR.PropertyName_List + ")[" + rowNumber + "]"));

				System.out.println("Property name is " + Property.getText());
				if (Property.getText().contains(PropName)) {
					found = true;
					Property.click();
					Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
					test_steps.add("Click " + PropName);
					Utility.app_logs.info("Click " + PropName);
					break;
				}
			}
			if (found) {
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				System.out.println("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.PropertyName_List);
			}
		}
		if (found) {
			// test_steps.add(PropName + " Property Exist");
			System.out.println(PropName + " Property Exist");
		} else {
			assertTrue(false, "Property not found");
		}
		return test_steps;

	}

	public ArrayList<String> SendCCOnAllEmails(WebDriver driver, String Email, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.SendCC_CustomEmailAddress_CheckBox, driver);
		test_steps.add("Click Options Tab");
		Utility.app_logs.info("Click Options Tab");
		if (!all_Navigation.SendCC_CustomEmailAddress_CheckBox.isSelected()) {
			all_Navigation.SendCC_CustomEmailAddress_CheckBox.click();
			System.out.println("Click Custom Email Address");
		}
		Wait.wait2Second();
		assertTrue(all_Navigation.SendCC_CustomEmailAddress_CheckBox.isSelected(),
				"Send CC Custom Email Address is not Selected");
		all_Navigation.SendCC_CustomEmailAddress_Email.sendKeys(Email);
		Wait.wait2Second();
		test_steps.add("Enter Email : " + Email);
		Utility.app_logs.info("Enter Email : " + Email);
		return test_steps;

	}

	public ArrayList<String> SendEmailUsingInnroadsEmail(WebDriver driver, String Email, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.SendCC_CustomEmailAddress_CheckBox, driver);
		test_steps.add("Click Options Tab");
		Utility.app_logs.info("Click Options Tab");
		assertTrue(false, "Element Missing"); // code incomplete
		return test_steps;

	}

	public ArrayList<String> SendEmailUsingPropertysEmail(WebDriver driver, String Email, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.SendCC_CustomEmailAddress_CheckBox, driver);
		test_steps.add("Click Options Tab");
		Utility.app_logs.info("Click Options Tab");
		assertTrue(false, "Element Missing"); // code incomplete
		return test_steps;

	}

	public ArrayList<String> SendManualEmailFromUserEmail(WebDriver driver, String Email, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.SendCC_CustomEmailAddress_CheckBox, driver);
		test_steps.add("Click Options Tab");
		Utility.app_logs.info("Click Options Tab");
		assertTrue(false, "Element Missing"); // code incomplete
		return test_steps;

	}

	public ArrayList<String> SendManualEmailFromCustomEmail(WebDriver driver, String Email,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.SendCC_CustomEmailAddress_CheckBox, driver);
		test_steps.add("Click Options Tab");
		Utility.app_logs.info("Click Options Tab");

		assertTrue(false, "Element Missing"); // code incomplete
		return test_steps;
	}

	public ArrayList<String> create_property(WebDriver driver, String PropertyName, String LegalyName, String Town,
			String Description, String Contact, String PhoneNumber, String Email, String Address, String City,
			String State, String PostalCode, ArrayList<String> test_steps) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);

		all_Navigation.Propeties.click();

		test_steps.add("Navigate Properties");
		Utility.app_logs.info("Navigate Properties");
		all_Navigation.NewProperty_Button.click();
		Wait.wait2Second();
		test_steps.add("Click New Property");
		Utility.app_logs.info("Click New Property");
		all_Navigation.Enter_PropertyName.sendKeys(PropertyName);
		all_Navigation.Enter_LegalyName.sendKeys(LegalyName);
		new Select(all_Navigation.Select_Town).selectByVisibleText(Town);
		all_Navigation.Enter_Description.sendKeys(Description);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", all_Navigation.Enter_Contact);
		all_Navigation.Enter_Contact.sendKeys(Contact);
		all_Navigation.Enter_Phone.sendKeys(PhoneNumber);
		all_Navigation.Enter_Email.sendKeys(Email);
		all_Navigation.Enter_Property_Address.sendKeys(Address);
		all_Navigation.Enter_Property_City.sendKeys(City);
		new Select(all_Navigation.Select_Property_State).selectByVisibleText(State);
		all_Navigation.Enter_Property_PostalCode.sendKeys(PostalCode);
		all_Navigation.Use_MailingAddress_Checkbox.click();
		return test_steps;

	}

	public void SearchProperty_DeleteProperty(WebDriver driver, String PropName) throws InterruptedException {

		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.CreatedProperty_Pages)), driver);
		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(OR.CreatedProperty_Pages)));
		int Size = driver.findElements(By.xpath(OR.CreatedProperty_Pages)).size();
		if (Size > 2) {
			System.out.println("Total Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.PropertyName_List);
			int count = driver.findElements(By.xpath(OR.PropertyName_List)).size();
			System.out.println(count);

			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				WebElement Property = driver.findElement(By.xpath("(" + OR.PropertyName_List + ")[" + rowNumber + "]"));

				System.out.println("Property name is " + Property.getText());
				if (Property.getText().contains(PropName)) {
					found = true;
					String path = "//tr//td/a[text()='" + PropName + "']/..//following-sibling::td/span/input";
					WebElement delete_element = driver.findElement(By.xpath(path));
					Wait.explicit_wait_visibilityof_webelement(delete_element, driver);
					Utility.ScrollToElement(delete_element, driver);
					Wait.wait1Second();
					delete_element.click();
					all_Navigation.Property_Delete_Button.click();
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='1']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					System.out.println("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.PropertyName_List);
				}
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				System.out.println("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.PropertyName_List);
			}
		}
		if (found) {
			// test_steps.add(PropName + " Property Exist");
			System.out.println(PropName + " Property Exist");
		} else {
			assertTrue(false, "Property not found");
		}

	}

	public ArrayList<String> VerifyProperty(WebDriver driver, String PropName, String Town, String Published,
			String Status, ArrayList<String> test_steps) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Select_Status, driver);
		new Select(all_Navigation.Select_Status).selectByVisibleText(Status);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.GoButton, driver);
		all_Navigation.GoButton.click();
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(OR.CreatedProperty_Pages)), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(OR.CreatedProperty_Pages)));
		int Size = driver.findElements(By.xpath(OR.CreatedProperty_Pages)).size();
		if (Size > 2) {
			System.out.println("Total Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.PropertyName_List);
			int count = driver.findElements(By.xpath(OR.PropertyName_List)).size();
			System.out.println(count);
			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				WebElement Property = driver.findElement(By.xpath("(" + OR.PropertyName_List + ")[" + rowNumber + "]"));

				System.out.println("Property name is " + Property.getText());
				if (Property.getText().contains(PropName)) {
					found = true;
					String townPath = "//tr//td/a[text()='" + PropName + "']/..//following-sibling::td[1]";
					String StatusPath = "//tr//td/a[text()='" + PropName + "']/..//following-sibling::td[3]";
					String PublishedPath = "//tr//td/a[text()='" + PropName + "']/..//following-sibling::td[4]";
					assertEquals(driver.findElement(By.xpath(townPath)).getText(), Town, "Failed: Town Missmatched");
					assertEquals(driver.findElement(By.xpath(StatusPath)).getText(), Status,
							"Failed: Status Missmatched");
					assertEquals(driver.findElement(By.xpath(PublishedPath)).getText(), Published,
							"Failed: Published Missmatched");
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='1']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					System.out.println("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.PropertyName_List);
				}
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				System.out.println("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.PropertyName_List);
			}
		}
		if (found) {
			// test_steps.add(PropName + " Property Exist");
			System.out.println(PropName + " Property Exist");
		} else {
			assertTrue(false, "Property not found");
		}
		return test_steps;

	}

	public void DeleteProperty(WebDriver driver, String PropertyName) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);

		String path = "//tr//td/a[text()='" + PropertyName + "']/..//following-sibling::td/span/input";
		WebElement delete_element = null;
		try {
			delete_element = driver.findElement(By.xpath(path));
		} catch (Exception e) {
			String lastPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[last()]";
			Utility.ScrollToElement(driver.findElement(By.xpath(lastPagePath)), driver);
			driver.findElement(By.xpath(lastPagePath)).click();
			Utility.app_logs.info("Move to last Page ");
			delete_element = driver.findElement(By.xpath(path));
		}
		Wait.explicit_wait_visibilityof_webelement(delete_element, driver);
		Utility.ScrollToElement(delete_element, driver);
		Wait.wait1Second();
		delete_element.click();
		all_Navigation.Property_Delete_Button.click();
	}

	public void Search_Property(WebDriver driver, String PropertyName) throws InterruptedException {

		Wait.wait2Second();
		String path = "//a[.=" + "'" + PropertyName + "'" + "]";
		WebElement element = driver.findElement(By.xpath(path));
		String Property_Name = element.getText();
		assertEquals(Property_Name, PropertyName);
		element.click();
		Wait.wait2Second();

	}

	public void EditProperty(WebDriver driver, String LegalPropertyName, String Town) throws InterruptedException {
		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Enter_LegalyName, driver);
		all_Navigation.Enter_PropertyName.clear();
		all_Navigation.Enter_PropertyName.sendKeys(LegalPropertyName);
		new Select(all_Navigation.Select_Town).selectByVisibleText(Town);
		// Assert.assertEquals(new
		// Select(all_Navigation.Select_Town).getFirstSelectedOption().getText(),
		// Town,
		// "Failed : Town not Selected ");

	}

	public void SendEmailServices(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		all_Navigation.Property_Options.click();
		if (!(all_Navigation.EmailCheckbox).isSelected()) {
			all_Navigation.EmailCheckbox.click();
		}
		all_Navigation.EmailDisplayNameField.clear();
		all_Navigation.EmailDisplayNameField.sendKeys("BEST SERVICE HOTEL AUTOMATION SYS");

		all_Navigation.Property_Publish_Button.click();
		Wait.wait2Second();
		Properties properties = new Properties();
		properties.Search_Property(driver, "QA Property");
		all_Navigation.Property_Options.click();
		assertEquals(all_Navigation.EmailDisplayNameField.getAttribute("value"), "BEST SERVICE HOTEL AUTOMATION SYS",
				"Display Email doens't save");

	}
	public ArrayList<String> CheckOutProcess_Checked(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.PropertyName_List, driver);
		Utility.ScrollToElement(all_Navigation.PropertyName_List, driver);
		all_Navigation.PropertyName_List.click();
		test_steps.add("Click on property name");
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		Utility.ScrollToElement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		test_steps.add("Click on 'Options' ");
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.AllowNonZeroBalanceCheckbox, driver);
		Utility.ScrollToElement(all_Navigation.AllowNonZeroBalanceCheckbox, driver);

		if(all_Navigation.AllowNonZeroBalanceCheckbox.isSelected())
		{

			System.out.println("The Checkbox of AllowNonZeroBalance is checked");
			test_steps.add("The Checkbox of AllowNonZeroBalance is checked");
		}
		else
		{
			all_Navigation.AllowNonZeroBalanceCheckbox.click();
			System.out.println("The Checkbox of AllowNonZeroBalance is checked");
			test_steps.add("The Checkbox of AllowNonZeroBalance is checked");
		}

		all_Navigation.Property_Publish_Button.click();
		test_steps.add("Click on publish button");
		return test_steps;

	}
	public ArrayList<String> CheckOutProcess_Unchecked(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.PropertyName_List, driver);
		Utility.ScrollToElement(all_Navigation.PropertyName_List, driver);
		all_Navigation.PropertyName_List.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.Property_Options, driver);
		Utility.ScrollToElement(all_Navigation.Property_Options, driver);
		all_Navigation.Property_Options.click();
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.AllowNonZeroBalanceCheckbox, driver);
		Utility.ScrollToElement(all_Navigation.AllowNonZeroBalanceCheckbox, driver);

		if(all_Navigation.AllowNonZeroBalanceCheckbox.isSelected())
		{
			all_Navigation.AllowNonZeroBalanceCheckbox.click();
			System.out.println("The Checkbox of AllowNonZeroBalance is unchecked");
		}
		else
		{

			System.out.println("The Checkbox of AllowNonZeroBalance is unchecked");
		}

		all_Navigation.Property_Publish_Button.click();
		return test_steps;

	}

	public void LogOut(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.explicit_wait_visibilityof_webelement(all_Navigation.LogOutButton, driver);
		Utility.ScrollToElement(all_Navigation.LogOutButton, driver);
		all_Navigation.LogOutButton.click();

		Wait.explicit_wait_visibilityof_webelement(all_Navigation.LogOut, driver);
		Utility.ScrollToElement(all_Navigation.LogOut, driver);
		all_Navigation.LogOut.click();

	}

	public void PropertyName(WebDriver driver) {
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR.propertyName);
		properties.propertyName.click();
		Wait.WaitForElement(driver, OR.Property_Grid);

	}

	public void PropertyOptions(WebDriver driver, ArrayList<String> test_steps) {
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR.Property_Options);
		properties.Property_Options.click();
		Wait.WaitForElement(driver, OR.Property_CreditCard);
		test_steps.add("Click on Property Options");

	}

	public void PropertyOptions_requireCreditCard(WebDriver driver, ExtentTest test, boolean isRequireCC,
			ArrayList<String> test_steps) {
		Elements_SetUp_Properties properties = new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR.Property_CreditCard);
		if (isRequireCC) {
			if (properties.Property_CreditCard.isSelected()) {

				if (properties.OptionGuaranteed.isSelected()) {

					System.out.println(" Property Option Require Credit card for Guaranteed was already selected ");
					test_steps.add(" Property Option Require Credit card for Guaranteed was already selected ");
				}

				else {

					properties.OptionGuaranteed.click();
					System.out.println(" Selected Property Option>> Require Credit card for Guaranteed Reservation ");
					// properties.OptionSave.click();
					test_steps.add("Selected Property Option>> Require Credit card for Guaranteed Reservation ");

				}
			}
		} else {
			if (properties.Property_CreditCard.isSelected()) {

				properties.Property_CreditCard.click();
				propertylogger.info(" Property Option Require Credit card for Guaranteed State: "
						+ properties.Property_CreditCard.isSelected());
				test_steps.add(" Property Option Require Credit card for Guaranteed State: "
						+ properties.Property_CreditCard.isSelected());

			}
		}
	}

	public void PropertyOptions_requireCreditCardAlways(WebDriver driver, ExtentTest test, ArrayList<String> test_steps) {
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver); 
		Wait.WaitForElement(driver, OR.Property_CreditCard);
		if(properties.Property_CreditCard.isSelected()) {

			if(properties.OptionCreditCardAlways.isSelected()) {

				System.out.println(" Property Option Require Credit card Always was already selected ");
				test_steps.add(" Property Option Require Credit card Always was already selected  ");
			}

			else {

				properties.OptionCreditCardAlways.click();
				System.out.println(" Selected Property Option>> Require Credit card Always ");
				//properties.OptionSave.click();
				test_steps.add("Selected Property Option>> Require Credit card Always ");

			}
		}
	}

	public boolean Click_GenerateGuestStatementCheckBox(WebDriver driver,ArrayList<String> test_steps,String IsGuesStatement) throws InterruptedException {
		boolean isGuestStatementCheckBox=false;
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.Setup_PropertiesGuestStatementOption);
		Utility.ScrollToElement(properties.Setup_PropertiesGuestStatementOption, driver);
		if(IsGuesStatement.contentEquals("Yes")) {
			if(!properties.Setup_PropertiesGuestStatementOption.isSelected()) {
				properties.Setup_PropertiesGuestStatementOption.click();
				test_steps.add("Default to Generate Guest Statement on Check Out selected");
				propertylogger.info("Default to Generate Guest Statement on Check Out selected");
				isGuestStatementCheckBox=true;
			}else {
				test_steps.add("Default to Generate Guest Statement on Check Out selected");
				propertylogger.info("Default to Generate Guest Statement on Check Out selected");
				isGuestStatementCheckBox=true;
			}
		}else {
			if(properties.Setup_PropertiesGuestStatementOption.isSelected()) {
				properties.Setup_PropertiesGuestStatementOption.click();
				test_steps.add("Default to Generate Guest Statement on Check Out unselected");
				propertylogger.info("Default to Generate Guest Statement on Check Out unselected");
				isGuestStatementCheckBox=false;
			}else {
				test_steps.add("Default to Generate Guest Statement on Check Out unselected");
				propertylogger.info("Default to Generate Guest Statement on Check Out unselected");
				isGuestStatementCheckBox=false;
			}
		}

		return isGuestStatementCheckBox;
	}

	public boolean clickGenerateGuestRegistrationCheckBoxOnCheckIn(WebDriver driver,ArrayList<String> test_steps,String IsGuesStatement) throws InterruptedException {
		boolean isGuestRegistrationCheckBox=false;
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.Setup_PropertiesGuestRegistrationOption);
		Utility.ScrollToElement(properties.Setup_PropertiesGuestRegistrationOption, driver);
		if(IsGuesStatement.contentEquals("Yes")) {
			if(!properties.Setup_PropertiesGuestRegistrationOption.isSelected()) {
				Utility.ScrollToElement(properties.Setup_PropertiesGuestRegistrationOption, driver);
				properties.Setup_PropertiesGuestRegistrationOption.click();
				test_steps.add("Default to Generate Guest Registration on Check In selected");
				propertylogger.info("Default to Generate Guest Registration on Check In selected");
				isGuestRegistrationCheckBox=true;
			}else {
				test_steps.add("Default to Generate Guest Registration on Check In selected");
				propertylogger.info("Default to Generate Guest Registration on Check In selected");
				isGuestRegistrationCheckBox=true;
			}
		}else if (IsGuesStatement.contentEquals("No")) {
			if(properties.Setup_PropertiesGuestRegistrationOption.isSelected()) {
				Utility.ScrollToElement(properties.Setup_PropertiesGuestRegistrationOption, driver);
				properties.Setup_PropertiesGuestRegistrationOption.click();
				test_steps.add("Default to Generate Guest Registration on Check In unselected");
				propertylogger.info("Default to Generate Guest Registration on Check In unselected");
				isGuestRegistrationCheckBox=false;
			}else {
				test_steps.add("Default to Generate Guest Registration on Check In unselected");
				propertylogger.info("Default to Generate Guest Registration on Check In unselected");
				isGuestRegistrationCheckBox=false;
			}
		}

		return isGuestRegistrationCheckBox;
	}

	public String getProperty(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException
	{
		/*Elements_Admin admin= new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.PropertyName);
		String propertyName= admin.PropertyName.getText();
		test_steps.add("Property Name : <b>" +propertyName + "</b>");
		propertylogger.info("Property Name : " +propertyName);
		return propertyName;
		 */
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String getText=null;
		boolean propertyName=Utility.isElementDisplayed(driver, By.xpath(OR_Reservation.selectedProperty));
		if(propertyName) {
			Wait.WaitForElement(driver, OR_Reservation.selectedProperty);	
			Utility.ScrollToElement(res.selectedProperty, driver);
			getText = res.selectedProperty.getAttribute("title").trim();
		}else {
			Wait.WaitForElement(driver, OR_Reservation.selectedPropertys);	
			Utility.ScrollToElement(res.selectedPropertys, driver);
			getText = res.selectedPropertys.getAttribute("title").trim();
		}

		propertylogger.info("Selected property name : " + getText);
		test_steps.add("Selected property name : " + getText);		
		return getText;

	}

	public String getRateV2Property(WebDriver driver,ArrayList<String> test_steps)
	{
		Elements_Admin admin= new Elements_Admin(driver);
		Wait.WaitForElement(driver, OR_Admin.rateV2PropertyName);
		String propertyName= admin.rateV2PropertyName.getText();
		test_steps.add("Property Name : <b>" +propertyName + "</b>");
		propertylogger.info("Property Name : " +propertyName);
		return propertyName;

	}

	public void uncheck_GuaranteedCheckBoxProperties(WebDriver driver, ArrayList<String> test_steps, String IsGuesProfile) {
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.Setup_PropertiesGuaranteedOption);
		if (IsGuesProfile.contentEquals("Yes")) {
			if (!properties.Setup_PropertiesGuaranteedOption.isSelected()) {
				properties.Setup_PropertiesGuaranteedOption.click();
				test_steps.add("If deposit is recorded automatically set reservation to Guaranteed selected");
				propertylogger.info("If deposit is recorded automatically set reservation to Guaranteed selected");
			} else {
				test_steps.add("If deposit is recorded automatically set reservation to Guaranteed selected");
				propertylogger.info("If deposit is recorded automatically set reservation to Guaranteed selected");
			}
		} else {
			if (properties.Setup_PropertiesGuaranteedOption.isSelected()) {
				properties.Setup_PropertiesGuaranteedOption.click();
				test_steps.add("If deposit is recorded automatically set reservation to Guaranteed unselected");
				propertylogger.info("If deposit is recorded automatically set reservation to Guaranteed unselected");
			} else {
				test_steps.add("If deposit is recorded automatically set reservation to Guaranteed unselected");
				propertylogger.info("If deposit is recorded automatically set reservation to Guaranteed unselected");
			}
		}

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: depositRequiredForSaveGaurenteedReservationCheckbox ' Description: Checks/Unchecks
	 * Check box Based on boolean ' Input parameters: WebDriver, boolean,
	 * ArrayList, String ' Return value: String ' Created By: Aqsa Manzoor '
	 * Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> depositRequiredForSaveGaurenteedReservationCheckbox(WebDriver driver, boolean depositRequiredChecked,
			ArrayList<String> test_steps) {
		Elements_SetUp_Properties properties = new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION);
		System.out.println(properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected());
		if (depositRequiredChecked) {
			if (!properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected()) {
				properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.click();
			}
			assertEquals(properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected(), true);

		} else {
			if (properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected()) {
				properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.click();
			}
			assertEquals(properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected(), false);
		}
		test_steps.add("Deposit Required For Save Gauranteed Reservation Checkbox Checked: "
				+ properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected());
		propertylogger.info("Deposit Required For Save Gauranteed Reservation Checkbox Checked: "
				+ properties.PROPERTY_DEPOSITREQUIRED_SAVEGAURANTEEDRESERVATION.isSelected());
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: depositRecordedAutomaticallySetGaurenteedReservationCheckbox ' Description: Checks/Unchecks
	 * Check box Based on boolean ' Input parameters: WebDriver, boolean,
	 * ArrayList, String ' Return value: String ' Created By: Aqsa Manzoor '
	 * Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> depositRecordedAutomaticallySetGaurenteedReservationCheckbox(WebDriver driver,
			boolean depositAutomaticallyRequiredChecked, ArrayList<String> test_steps) {
		Elements_SetUp_Properties properties = new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION);
		System.out.println(properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected());
		if (depositAutomaticallyRequiredChecked) {
			if (!properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected()) {
				properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.click();

				assertEquals(properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected(),
						true);
			}
		} else {
			if (properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected()) {
				properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.click();

				assertEquals(properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected(),
						false);
			}

		}
		test_steps.add("Deposit Recorded Automatically Set Reservation To Gauranteed Checkbox Checked: "
				+ properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected());
		propertylogger.info("Deposit Recorded Automatically Set Reservation To Gauranteed Checkbox Checked: "
				+ properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected());
		return test_steps;

	}

	public void allowNonZeroBalanceDuringCheckout(WebDriver driver, ArrayList<String> test_steps, String IsNonZero) throws InterruptedException {
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.NonZeroBalanceWhileCheckOut);
		Utility.ScrollToElement(properties.NonZeroBalanceWhileCheckOut, driver);
		if (IsNonZero.contentEquals("Yes")) {
			if (!properties.NonZeroBalanceWhileCheckOut.isSelected()) {
				Wait.wait15Second();
				properties.NonZeroBalanceWhileCheckOut.click();
				Wait.wait5Second();
				test_steps.add("Allow non-zero balance at the time of check-out Selected");
				propertylogger.info("Allow non-zero balance at the time of check-out Selected");
			} else {
				test_steps.add("Allow non-zero balance at the time of check-out Selected");
				propertylogger.info("Allow non-zero balance at the time of check-out Selected");
			}
		} else {
			if (properties.NonZeroBalanceWhileCheckOut.isSelected()) {
				Wait.wait15Second();
				properties.NonZeroBalanceWhileCheckOut.click();
				Wait.wait5Second();
				test_steps.add("Allow non-zero balance at the time of check-out UnSelected");
				propertylogger.info("Allow non-zero balance at the time of check-out UnSelected");
			} else {
				test_steps.add("Allow non-zero balance at the time of check-out UnSelected");
				propertylogger.info("Allow non-zero balance at the time of check-out UnSelected");
			}
		}

	}

	public void clickSaveButton(WebDriver driver) throws InterruptedException {

		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		all_Navigation.Properties_Save.click();
		// test_steps.add("Click Save");
		Utility.app_logs.info("Clicked on Save button");
		Wait.wait2Second();
	}
	public void openProperty(WebDriver driver){
		String propLink="//table[@id='MainContent_dgPropertyList']/tbody/tr/td/a";
		Wait.WaitForElement(driver, propLink);
		driver.findElement(By.xpath(propLink)).click();
	}

	public String getPropLegalName(WebDriver driver){
		String name="//input[@id='MainContent_txtLegalName']";
		Wait.WaitForElement(driver, name);
		return driver.findElement(By.xpath(name)).getAttribute("value");
	}

	public String getPropPhone(WebDriver driver){
		String propPhone="//input[@id='MainContent_txtMailing_phoneNumber']";
		Wait.WaitForElement(driver, propPhone);
		return driver.findElement(By.xpath(propPhone)).getAttribute("value");
	}

	public String getPropEmail(WebDriver driver){
		String propEmail="//input[@id='MainContent_txtMailing_email']";
		Wait.WaitForElement(driver, propEmail);
		return driver.findElement(By.xpath(propEmail)).getAttribute("value");
	}

	public String get_PropAddress(WebDriver driver){
		String address="//input[@id='MainContent_txtMailing_address1']";

		String city="//input[@id='MainContent_txtMailing_city']";

		//String country="//select[@id='MainContent_drpMailing_countryID']";

		String state="//select[@id='MainContent_drpMailing_territoryID']";

		String pin ="//input[@id='MainContent_txtMailing_postalCode']";


		Wait.WaitForElement(driver, address);
		address=driver.findElement(By.xpath(address)).getAttribute("value");

		Wait.WaitForElement(driver, city);
		city=driver.findElement(By.xpath(city)).getAttribute("value");

		Wait.WaitForElement(driver, pin);
		pin=driver.findElement(By.xpath(pin)).getAttribute("value");

		Wait.WaitForElement(driver, state);
		state=new Select(driver.findElement(By.xpath(state))).getFirstSelectedOption().getText();


		address=address+"/"+city+"/"+state+"/"+pin;

		System.out.println(address);
		return address;
	}

	public ArrayList<String> getPropertiesList(WebDriver driver){
		String strProperties="//table[@id='MainContent_dgPropertyList']//td/a";
		ArrayList<String> propertiesList = new ArrayList<>();
		List<WebElement> prop = driver.findElements(By.xpath(strProperties));

		for (int i = 0; i < prop.size(); i++) {
			propertiesList.add(prop.get(i).getText());
		}

		return propertiesList;


	}


	public void LongStay(WebDriver driver, String NgigtValue,ArrayList<String> test_steps, boolean isChecked, boolean allNight, boolean afterLimit) throws InterruptedException {


		Elements_On_All_Navigation all_Navigation = new Elements_On_All_Navigation(driver);
		Wait.WaitForElement(driver, OR.ChkLongerthan);		
		Utility.ScrollToElement(all_Navigation.ChkLongerthan,driver);
		Wait.wait10Second();

		if(isChecked) {
			if (!all_Navigation.ChkLongerthan.isSelected()) {
				Utility.ScrollToElement(all_Navigation.ChkLongerthan,driver);
				all_Navigation.ChkLongerthan.click();
				propertylogger.info("Reservation longer then checkbox checked");
				test_steps.add("Reservation longer then checkbox checked");
			}
			else{
				test_steps.add("Reservation longer then checkbox already checked");
				propertylogger.info("Reservation longer then checkbox already checked");
			}
		}else {
			if (all_Navigation.ChkLongerthan.isSelected()) {
				Utility.ScrollToElement(all_Navigation.ChkLongerthan,driver);
				all_Navigation.ChkLongerthan.click();
				test_steps.add("Reservation longer then checkbox unchecked");
				propertylogger.info("Reservation longer then checkbox unchecked");
			}
			else{
				test_steps.add("Reservation longer then checkbox already unchecked");
				propertylogger.info("Reservation longer then checkbox already unchecked");
			}
		}

		if(isChecked) {
			Utility.ScrollToElement(all_Navigation.Enter_txttaxexempt,driver);
			all_Navigation.Enter_txttaxexempt.clear();
			all_Navigation.Enter_txttaxexempt.sendKeys(NgigtValue);
			test_steps.add("Enter night value : "+NgigtValue);
			propertylogger.info("Enter night value : "+NgigtValue);
		}

		if(allNight) {
			if (!all_Navigation.AllNight.isSelected()) {
				Utility.ScrollToElement(all_Navigation.AllNight,driver);
				all_Navigation.AllNight.click();
				test_steps.add("All Night checkbox checked");
				propertylogger.info("All Night checkbox checked");
			}
			else{
				test_steps.add("All Night checkbox already checked");
				propertylogger.info("All Night checkbox already checked");
			}
		}

		if(afterLimit) {
			if (!all_Navigation.AfterlimitMeet.isSelected()) {
				Utility.ScrollToElement(all_Navigation.AfterlimitMeet,driver);
				all_Navigation.AfterlimitMeet.click();
				test_steps.add("After Limit is met checkbox checked");
				propertylogger.info("After Limit is met checkbox checked");
			}
			else{
				test_steps.add("After Limit is met checkbox already checked");
				propertylogger.info("After Limit is met checkbox already checked");
			}
		}
		Wait.wait5Second();
		all_Navigation.Properties_Save.click();
		test_steps.add("Click on save property button");

	}


	public String getPropertyName(WebDriver driver, int index) {
		String prop = "(//table[contains(@id,'dgPropertyList')]//tr[@class='dgItem'])["+index+"]/td/a";
		Wait.waitForElementToBeClickable(By.xpath(prop), driver);
		String propertyName = driver.findElement(By.xpath(prop)).getText();
		return propertyName;
	}


	public int getNumberOfProperties(WebDriver driver) {
		String prop = "//table[contains(@id,'dgPropertyList')]//tr[@class='dgItem']/td/a";
		Wait.waitForElementToBeClickable(By.xpath(prop), driver);
		int size = driver.findElements(By.xpath(prop)).size();
		return size;
	}

	public void selectPropertyWithSuperUser(WebDriver driver, String propertyName) {
		Elements_SetUp_Properties prop = new Elements_SetUp_Properties(driver);

		Wait.waitForElementToBeClickable(By.xpath(OR.clickPropertySelect), driver);
		prop.clickPropertySelect.click();
		Wait.waitForElementToBeClickable(By.xpath(OR.editPropertySelect), driver);
		prop.editPropertySelect.click();
		Wait.waitUntilPresenceOfElementLocated(OR.inputPropertySelect, driver);
		prop.inputPropertySelect.sendKeys(propertyName);
		Wait.waitForElementToBeClickable(By.xpath(OR.clickPropertyName), driver);
		prop.clickPropertyName.click();

		String property = "//*[@class='propertySelectName' and text()='Sky-Palace Inn & Suites New Richmond']";
		Wait.WaitForElement(driver, property);
	}

	public void setOrRemoveTaxExempt(WebDriver driver, ArrayList<String> test_steps, boolean set, 
			String taxExemptInput, String taxExemptOption) throws Exception {
		Elements_SetUp_Properties properties = new Elements_SetUp_Properties(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Setup.taxExemptCheckBox), driver);
		Utility.ScrollToElement(properties.taxExemptCheckBox, driver);
		boolean checked = checkBoxEnabledOrNot(driver, properties.taxExemptCheckBox);
		if (set) {
			if (!checked) {
				Utility.clickThroughJavaScript(driver, properties.taxExemptCheckBox);
				//				properties.taxExemptCheckBox.click();
				test_steps.add("Enabled tax exempt check box");
				propertylogger.info("Enabled tax exempt check box");
			} else {
				test_steps.add("Tax exempt check box is already enabled");
				propertylogger.info("Tax exempt check box is already enabled");	
			}
			properties.taxExemptTextField.clear();
			properties.taxExemptTextField.sendKeys(taxExemptInput);
			test_steps.add("Entered Tax exempt as : <b>"+taxExemptInput+"</b>");
			propertylogger.info("Entered Tax exempt as : "+taxExemptInput);	
			if (taxExemptOption.equalsIgnoreCase("All Nights")) {
				boolean allNightsChecked = checkBoxEnabledOrNot(driver, properties.taxExemptAllNights);
				if (allNightsChecked) {
					test_steps.add("All Nights option for Tax exempt is already selected");
					propertylogger.info("All Nights option for Tax exempt is already selected");										
				} else {
					properties.taxExemptAllNights.click();
					test_steps.add("Selected All Nights option for Tax exempt");
					propertylogger.info("Selected All Nights option for Tax exempt");										
				}				
			} else if (taxExemptOption.equalsIgnoreCase("After Limit is met")) {
				boolean allNightsChecked = checkBoxEnabledOrNot(driver, properties.taxExemptAfterLimitIsMet);
				if (allNightsChecked) {
					test_steps.add("After Limit is met option for Tax exempt is already selected");
					propertylogger.info("After Limit is met option for Tax exempt is already selected");										
				} else {
					properties.taxExemptAfterLimitIsMet.click();
					test_steps.add("Selected After Limit is met option for Tax exempt");
					propertylogger.info("Selected After Limit is met option for Tax exempt");										
				}				
			}			
		} else {
			if (!checked) {
				test_steps.add("Tax exempt check box is already disabled");
				propertylogger.info("Tax exempt check box is already disabled");	
			} else {
				properties.taxExemptCheckBox.click();
				test_steps.add("Disabled tax exempt check box");
				propertylogger.info("Disabled tax exempt check box");
			}
		}


	}

	public boolean checkBoxEnabledOrNot(WebDriver driver, WebElement element) {
		boolean checked = false;
		try {
			if (element.getAttribute("checked").equalsIgnoreCase("checked") || element.getAttribute("checked").equalsIgnoreCase("true")) {
				System.out.println(element.getAttribute("checked"));
				checked = true;
			} else {
				System.out.println(element.getAttribute("checked"));
				checked = false;
			}
		} catch (Exception e) {
			System.out.println(element.getAttribute("checked"));
			checked = false;
		}
		return checked;

	}

	public void selectPropertyWithSuperUser(WebDriver driver, String propertyName, ArrayList<String> testSteps) {
		Elements_SetUp_Properties prop = new Elements_SetUp_Properties(driver);

		Wait.waitForElementByXpath(driver, OR.clickPropertySelect);
		prop.clickPropertySelect.click();
		testSteps.add("Click on select property icon");

		Wait.waitForElementByXpath(driver, OR.editPropertySelect);
		prop.editPropertySelect.click();
		testSteps.add("Click on edit property icon");

		Wait.waitUntilPresenceOfElementLocated(OR.inputPropertySelect, driver);
		prop.inputPropertySelect.sendKeys(propertyName);
		testSteps.add("Enter property name to be selected : " + propertyName);

		Wait.waitForElementByXpath(driver, OR.clickPropertyName);
		prop.clickPropertyName.click();
		testSteps.add("Click on property name");

	//	String property = "//*[@class='propertySelectName' and text()='"+ propertyName +"']";
	//	Wait.WaitForElement(driver, property);

		String property = "//*[@class='propertySelectName' and text()='"+ propertyName +"']";
//		Wait.WaitForElement(driver, property);

	}
	public String getTimeZone(WebDriver driver) {
		Elements_SetUp_Properties elements_SetUp_Properties = new Elements_SetUp_Properties(driver);
		Wait.WaitForElement_ID(driver, OR_Setup.selectTimeZone);
		Wait.waitForElementToBeClickable(By.id(OR_Setup.selectTimeZone), driver);
		Select select = new Select(elements_SetUp_Properties.selectTimeZone);
		WebElement element = select.getFirstSelectedOption();
		return element.getText();
	}
	public void changeProperty(WebDriver driver,String property) {
		String selectedProperty="//span[@title='"+property+"']";
		if(driver.findElements(By.xpath(selectedProperty)).size()>0) {
			propertylogger.info("Property is already selected");
		}else {
		Wait.waitForElementToBeClickable(By.xpath(OR.changeProperty), driver);
		driver.findElement(By.xpath(OR.changeProperty)).click();
		String propertyName="//div[contains(text(),'"+property+"')]";
		Wait.waitForElementToBeVisibile(By.xpath(propertyName), driver);
		driver.findElement(By.xpath(propertyName)).click();	
		Wait.waitForElementToBeVisibile(By.xpath(selectedProperty), driver);
		}
	}

	public void clickRequireCreditCardForGuaranteeCheckbox(WebDriver driver, ArrayList<String> testSteps, boolean isCheck){
		Elements_SetUp_Properties element= new Elements_SetUp_Properties(driver);
		Wait.waitForElementByXpath(driver, OR_Setup.depositIsRecordedAutomatically);
		if(isCheck) {
			if(element.requireCreditCardForGuarantee.isSelected()) {
				testSteps.add("'Require credit card for guarantee' checkbox is already checked");
			}else {
				element.requireCreditCardForGuarantee.click();
				testSteps.add("'Require credit card for guarantee' checkbox is checked");
			}
			
		}
		else {
			if(element.requireCreditCardForGuarantee.isSelected()) {
				element.requireCreditCardForGuarantee.click();
				testSteps.add("'Require credit card for guarantee' checkbox is unchecked");
			}else {
				testSteps.add("'Require credit card for guarantee' checkbox is already unchecked");
			}
			
		}
		
	}
	

	public void clickForGuaranteedReservationRadioButton(WebDriver driver, ArrayList<String> testSteps, boolean isCheck){
		Elements_SetUp_Properties element= new Elements_SetUp_Properties(driver);
		Wait.waitForElementByXpath(driver, OR_Setup.forGuaranteedReservation);
		if(isCheck) {
			if(element.forGuaranteedReservation.isSelected()) {
				testSteps.add("'For Guaranteed Reservations' radiobutton is already checked");
			}else {
				element.forGuaranteedReservation.click();
				testSteps.add("'For Guaranteed Reservations' radiobutton is checked");
			}
			
		}
		else {
			if(element.forGuaranteedReservation.isSelected()) {
				element.forGuaranteedReservation.click();
				testSteps.add("'For Guaranteed Reservations' radiobutton is unchecked");
			}else {
				testSteps.add("'For Guaranteed Reservations' radiobutton is already unchecked");
			}
			
		}
		
	}

	public void clickAlwaysRadioButton(WebDriver driver, ArrayList<String> testSteps, boolean isCheck){
		Elements_SetUp_Properties element= new Elements_SetUp_Properties(driver);
		Wait.waitForElementByXpath(driver, OR_Setup.always);
		if(isCheck) {
			if(element.always.isSelected()) {
				testSteps.add("'Always' radiobutton is already checked");
			}else {
				element.always.click();
				testSteps.add("'Always' radiobutton is checked");
			}
			
		}
		else {
			if(element.always.isSelected()) {
				element.always.click();
				testSteps.add("'Always' radiobutton is unchecked");
			}else {
				testSteps.add("'Always' radiobutton is already unchecked");
			}
			
		}
		
	}

	public void clickDepositIsRecordedAutomaticallyCheckbox(WebDriver driver, ArrayList<String> testSteps, boolean isCheck){
		Elements_SetUp_Properties element= new Elements_SetUp_Properties(driver);
		Wait.waitForElementByXpath(driver, OR_Setup.depositIsRecordedAutomatically);
		if(isCheck) {
			if(element.depositIsRecordedAutomatically.isSelected()) {
				testSteps.add("'If deposit is recorded automatically set reservation to Guaranteed' checkbox is already checked");
			}else {
				element.depositIsRecordedAutomatically.click();
				testSteps.add("'If deposit is recorded automatically set reservation to Guaranteed' checkbox is checked");
			}
			
		}
		else {
			if(element.depositIsRecordedAutomatically.isSelected()) {
				element.depositIsRecordedAutomatically.click();
				testSteps.add("'If deposit is recorded automatically set reservation to Guaranteed' checkbox is unchecked");
			}else {
				testSteps.add("'If deposit is recorded automatically set reservation to Guaranteed' checkbox is already unchecked");
			}
			
		}
		
	}

	public void clickRequireDepositForGuaranteeCheckbox(WebDriver driver, ArrayList<String> testSteps, boolean isCheck){
		Elements_SetUp_Properties element= new Elements_SetUp_Properties(driver);
		Wait.waitForElementByXpath(driver, OR_Setup.requireDepositForGuarantee);
		
		if(isCheck) {
			if(element.requireDepositForGuarantee.isSelected()) {
				testSteps.add("'Deposit is required to save Guaranteed reservation' checkbox is already checked");
			}else {
				element.requireDepositForGuarantee.click();
				testSteps.add("'Deposit is required to save Guaranteed reservation' checkbox is checked");
			}
			
		}
		else {
			if(element.requireDepositForGuarantee.isSelected()) {
				element.requireDepositForGuarantee.click();
				testSteps.add("'Deposit is required to save Guaranteed reservation' checkbox is unchecked");
			}else {
				testSteps.add("'Deposit is required to save Guaranteed reservation' checkbox is already unchecked");
			}
			
		}
		
	}
	
	public void uncheckOfGuaranteeCheckBox(WebDriver driver,
			ArrayList<String> test_steps) throws Exception {
		Elements_SetUp_Properties properties = new Elements_SetUp_Properties(driver);
		
		Wait.waitForElementByXpath(driver,OR_Setup.depositIsRecordedAutomatically);
		Wait.wait2Second();
		boolean depositIsRecordedAutomatically=properties.depositIsRecordedAutomatically.isSelected();
		if(depositIsRecordedAutomatically)
		{
			properties.depositIsRecordedAutomatically.click();
			test_steps.add("depositIsRecordedAutomatically checkbox is unchecked");
			propertylogger.info("depositIsRecordedAutomatically checkbox is unchecked ");
					
		}
		
		Wait.waitForElementByXpath(driver,OR_Setup.requireDepositForGuarantee);
		
		Wait.wait2Second();
		boolean requireDepositForGuarantee=properties.requireDepositForGuarantee.isSelected();
		if(requireDepositForGuarantee)
		{
			properties.requireDepositForGuarantee.click();
			test_steps.add("requireDepositForGuarantee checkbox is unchecked");
			propertylogger.info("requireDepositForGuarantee checkbox is unchecked ");
		}
		
		Wait.waitForElementByXpath(driver,OR_Setup.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION);
		Wait.wait2Second();
		boolean DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION=properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.isSelected();
		if(DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION)
		{
			properties.PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION.click();
			test_steps.add("PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION checkbox is unchecked");
			propertylogger.info("PROPERTY_DEPOSITRECORDEDAUTOMATICALLY_SETGAURANTEEDRESERVATION checkbox is unchecked ");
		}
		
		

	}

	
	
	public boolean clickGenerateGuestRegistrationCheckBoxOnCheckOut(WebDriver driver,ArrayList<String> test_steps,String IsGuesStatement) throws InterruptedException {
		boolean isGuestRegistrationCheckBox=false;
		Elements_SetUp_Properties properties=new Elements_SetUp_Properties(driver);
		Wait.WaitForElement(driver, OR_Setup.Setup_PropertiesGuestRegistrationOptionCheckout);
		Utility.ScrollToElement(properties.Setup_PropertiesGuestRegistrationOptionCheckout, driver);
		if(IsGuesStatement.contentEquals("Yes")) {
			if(!properties.Setup_PropertiesGuestRegistrationOptionCheckout.isSelected()) {
				Utility.ScrollToElement(properties.Setup_PropertiesGuestRegistrationOptionCheckout, driver);
				properties.Setup_PropertiesGuestRegistrationOptionCheckout.click();
				test_steps.add("Default to Generate Guest Statement  on Check Out selected");
				propertylogger.info("Default to Generate Guest Statement  on Check Out selected");
				isGuestRegistrationCheckBox=true;
			}else {
				test_steps.add("Default to Generate Guest Statement  on Check Out selected");
				propertylogger.info("Default to Generate Guest Statement  on Check Out selected");
				isGuestRegistrationCheckBox=true;
			}
		}else if (IsGuesStatement.contentEquals("No")) {
			if(properties.Setup_PropertiesGuestRegistrationOptionCheckout.isSelected()) {
				Utility.ScrollToElement(properties.Setup_PropertiesGuestRegistrationOptionCheckout, driver);
				properties.Setup_PropertiesGuestRegistrationOptionCheckout.click();
				test_steps.add("Default to Generate Guest Statement  on Check Out unselected");
				propertylogger.info("Default to Generate Guest Statement  on Check Out unselected");
				isGuestRegistrationCheckBox=false;
			}else {
				test_steps.add("Default to Generate Guest Statement  on Check Out unselected");
				propertylogger.info("Default to Generate Guest Statement  on Check Out unselected");
				isGuestRegistrationCheckBox=false;
			}
		}

		return isGuestRegistrationCheckBox;
	}
	

	
//End class here
	
	
	public void verifyPropertiesOpened(WebDriver driver,ArrayList<String> test_steps) {
		String options = "//input[@id='MainContent_btnPropertyAdvance']";
		Wait.WaitForElement(driver,options);
		WebElement element = driver.findElement(By.xpath(options));	
		assertTrue(element.isDisplayed(), "Failed to verify property opened");
		test_steps.add("Property opened successfully");
		propertylogger.info("Property opened successfully");
		
	}
	
	 public String getRoomNumber(WebDriver driver, String propertyName) {
		 String path = "(//a[text()='"+propertyName+"']//..//following-sibling::td)[2]";
		 System.out.println(path);
		 Wait.WaitForElement(driver, path);
		 Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		 Wait.waitForElementToBeClickable(By.xpath(path), driver);
		 return driver.findElement(By.xpath(path)).getText().trim();
	 }

}


