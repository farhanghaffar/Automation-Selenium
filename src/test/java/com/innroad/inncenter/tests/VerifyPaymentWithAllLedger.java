package com.innroad.inncenter.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.innroad.inncenter.pageobjects.Account;
import com.innroad.inncenter.pageobjects.CPReservationPage;
import com.innroad.inncenter.pageobjects.Folio;
import com.innroad.inncenter.pageobjects.Groups;
import com.innroad.inncenter.pageobjects.GuestHistory;
import com.innroad.inncenter.pageobjects.LedgerAccount;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.pageobjects.Navigation;
import com.innroad.inncenter.pageobjects.NewRoomClassesV2;
import com.innroad.inncenter.pageobjects.OverView;
import com.innroad.inncenter.pageobjects.Policies;
import com.innroad.inncenter.pageobjects.Properties;
import com.innroad.inncenter.pageobjects.Rate;
import com.innroad.inncenter.pageobjects.ReservationHomePage;
import com.innroad.inncenter.pageobjects.ReservationSearch;
import com.innroad.inncenter.pageobjects.RoomClass;
import com.innroad.inncenter.pageobjects.RoomMaintenance;
import com.innroad.inncenter.pageobjects.RoomStatus;
import com.innroad.inncenter.pageobjects.Rules;
import com.innroad.inncenter.pageobjects.Season;
import com.innroad.inncenter.pageobjects.Tapechart;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.APIException;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.RetryFailedTestCases;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_CPReservation;

public class VerifyPaymentWithAllLedger extends TestCore {

	private WebDriver driver = null;
	public static String test_name = null;
	public static String test_nameOne = null;
	public static String test_description = null;
	public static String test_catagory = null;
	ArrayList<String> testSteps = new ArrayList<>();
	ArrayList<String> getTestSteps = new ArrayList<>();
	RoomClass roomClass = new RoomClass();
	public static String roomClassName;
	public static String roomClassAbbrivation;

	ArrayList<String> roomCost = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	List<LocalDate> datesList = new ArrayList<LocalDate>();
	ArrayList<String> roomMaintance = new ArrayList<String>();
	List<String> date = new ArrayList<String>();
	List<String> day = new ArrayList<String>();
	ArrayList<String> caseId = new ArrayList<String>();
	ArrayList<String> statusCode = new ArrayList<String>();
	String comments;

	ArrayList<String> checkInDates = new ArrayList<>();

//	@BeforeTest(alwaysRun = true)
	public void checkRunMode() {
		String testName = this.getClass().getSimpleName().trim();
		app_logs.info("Verifying Test case " + testName.toUpperCase() + " is Skipped or not");
		if (!Utility.isExecutable(testName, excel_Swarna))
			throw new SkipException("Skipping the test - " + testName);
	}

	@Test(dataProvider = "getData", groups = "GuestServices")
	public void verifyPaymentWithAllLedger(String testCaseID, String ratePlan, String roomClassName,
			String swipeCardString, String swipedCardNumber, String swipedCardName, String swipedCardExpiry)
			throws ParseException {
		test_name = "VerifyPaymentWithAllLedger";
		test_description = "VerifyPaymentWithAllLedger"
				+ "<a href='https://innroad.testrail.io/index.php?/cases/view/848258' target='_blank'>"
				+ "Click here to open TestRail: C848258</a><br>";
		Navigation navigation = new Navigation();
		CPReservationPage reservationPage = new CPReservationPage();
		Properties prop = new Properties();
		ReservationHomePage homePage = new ReservationHomePage();
		ReservationSearch reservationSearch = new ReservationSearch();
		Folio folio = new Folio();
		GuestHistory guestHistory = new GuestHistory();
		LedgerAccount ledgerAccount = new LedgerAccount();
		Account CreateTA = new Account();
		Groups group = new Groups();
		String testName = null;

		String randomString = Utility.GenerateRandomString();
		String salutation = "Mr.";
		String guestFirstName = "VerifyRes" + randomString;
		String guestLastName = "Realization" + randomString;
		String paymentTypeMethod = "MC";
		String marketSegment = "Internet";
		String referral = "Other";
		String guaranteedStatus = "Guaranteed";

		String cardNumber = "5454545454545454";
		String nameOnCard = guestFirstName;
		String cardExpDate = "12/23";
		String billingNotes = "adding primary card";
		String accountType = "";
		String billingSalutation = "Lord.";
		String country = "United States";
		String accountNo = "";
		String phoneNumber = "1234567890";
		String alternativeNumber = phoneNumber;
		String address = "Address123";
		String email = "innroadautomation@innroad.com";
		String city = "New york";
		String state = "Alaska";
		String postalcode = "12345";
		String guestName = guestFirstName + " " + guestLastName;
		String account = "CorporateAccount" + Utility.generateRandomString();
		String accountNumber = Utility.GenerateRandomString15Digit();
		String address1 = "test1";
		String address2 = "test2";
		String address3 = "test3";
		String postalCode = "12345";
		String alternativePhone = "8790321567";
		String groupReferral = "Walk In";
		String groupFirstName = "Bluebook" + randomString;
		String groupLastName = "Group" + randomString;
		String accountName = groupFirstName + groupLastName;
		String invalidCard = "5151515151515151";
		String reservationNumber = "";
		String reservationtoPay = "";

		if (!Utility.validateString(testCaseID)) {
			caseId.add("848258");
			statusCode.add("4");
		} else {
			String[] testcase = testCaseID.split("\\|");
			for (int i = 0; i < testcase.length; i++) {
				caseId.add(testcase[i]);
				statusCode.add("4");
			}

		}
		test_catagory = "Verification";
		testName = test_name;

		String timeZone = "US/Eastren";
		String checkInDate = Utility.getCurrentDate("MM/dd/yyyy");
		String checkOutDate = Utility.getNextDate(1, "MM/dd/yyyy", timeZone);
		String maxAdult = "1";
		String maxPerson = "0";
		String property = "Reports Property";
		checkInDates.add(Utility.getCurrentDate("MM/dd/yyyy"));
		checkInDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(1), "MM/dd/yyyy", "MM/dd/yyyy"));
		checkInDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(2), "MM/dd/yyyy", "MM/dd/yyyy"));
		checkInDates.add(Utility.parseDate(Utility.getDatePast_FutureDate(3), "MM/dd/yyyy", "MM/dd/yyyy"));

		app_logs.info("##################################################################################");
		app_logs.info("EXECUTING: " + testName + " TEST.");
		app_logs.info("##################################################################################");

		// Login
		try {
			if (!Utility.insertTestName.containsKey(testName)) {
				Utility.insertTestName.put(testName, testName);
				Utility.reTry.put(testName, 0);
			} else {
				Utility.reTry.replace(testName, 1);
			}

			driver = getDriver();
			login_Autoota(driver);
			testSteps.add("Logged into the application");
			app_logs.info("Logged into the application");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.updateReport(e, "Failed to login", "NONGS_Login", "Login", driver);
			} else {
				Assert.assertTrue(false);
			}
		}
		//String reservationNumber = "";

		try {

			testSteps.add("===== <a href='https://innroad.testrail.io/index.php?/cases/view/824746' target='_blank'>"
					+ "<b>Verify 'Payment method' dropdown contain all Ledger accounts. </b></a> =====");
			navigation.mainSetupManu(driver);

			navigation.LedgerAccounts(driver);

			ledgerAccount.selectAccountType(driver, "Payment Method", testSteps);
			ArrayList<String> ledgerAccountList = ledgerAccount.getPaymentMethodList(driver);

			navigation.navigateToReservations(driver);
			testSteps.add("Navigate Reservations");
			app_logs.info("Navigate Reservations");

			reservationPage.click_NewReservation(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, checkInDates.get(0));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, checkInDates.get(1));
			testSteps.addAll(getTestSteps);

			reservationPage.enter_Adults(driver, testSteps, maxAdult);
			reservationPage.enter_Children(driver, testSteps, maxPerson);
			reservationPage.select_Rateplan(driver, testSteps, ratePlan, "");
			reservationPage.clickOnFindRooms(driver, testSteps);
			String isAssign = "Yes";
			Wait.wait10Second();

			reservationPage.select_Room(driver, testSteps, roomClassName, isAssign, "");
			reservationPage.clickNext(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyGuestProfileCheckoxChecked(driver, false);
			testSteps.addAll(getTestSteps);

			ArrayList<String> getPaymentMethods = homePage.getpaymentMethodsList(driver);
			Collections.sort(getPaymentMethods);
			Collections.sort(ledgerAccountList);
			int value = getPaymentMethods.size();
			int value1 = ledgerAccountList.size();
			testSteps.add("ledgerAccountList : " + ledgerAccountList);
			boolean isContainAll = getPaymentMethods.containsAll(ledgerAccountList);
			for (int b = 0; b < ledgerAccountList.size(); b++) {
				if (!ledgerAccountList.get(b).equalsIgnoreCase("Airbnb Payout") && !ledgerAccountList.get(b).equalsIgnoreCase("Removed Payment Type")) {
					isContainAll = getPaymentMethods.contains(ledgerAccountList.get(b));
					app_logs.info(ledgerAccountList.get(b));
					assertTrue(isContainAll,
							"Failed : Payment methods list not contains Ledger Account Item with name : "
									+ ledgerAccountList.get(b));
					testSteps.add("Verified ledge account item with name '" + ledgerAccountList.get(b)
							+ "' is not showing in the payment methods list");
				}
			}

			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed reservation tab");
			app_logs.info("Closed reservation tab");

			statusCode.add(0, "1");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create res", "NONGS_Login", "create res", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create res", "NONGS_Login", "create res", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {
		Utility.refreshPage(driver);
		Wait.waitUntilPageIsLoaded(driver);

			testSteps.add("===== <a href='https://innroad.testrail.io/index.php?/cases/view/824833' target='_blank'>"
					+ "<b>no payment associate with reservation</b></a> =====");

			reservationPage.click_NewReservation(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, checkInDates.get(0));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, checkInDates.get(1));
			testSteps.addAll(getTestSteps);

			reservationPage.enter_Adults(driver, testSteps, maxAdult);
			reservationPage.enter_Children(driver, testSteps, maxPerson);
			reservationPage.select_Rateplan(driver, testSteps, ratePlan, "");
			reservationPage.clickOnFindRooms(driver, testSteps);
			String isAssign = "Yes";

			reservationPage.select_Room(driver, testSteps, roomClassName, isAssign, "");
			reservationPage.clickNext(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyGuestProfileCheckoxChecked(driver, false);
			testSteps.addAll(getTestSteps);

			reservationPage.SelectReferral(driver, referral);

			reservationPage.clickBookNow(driver, testSteps);

			
			Wait.wait2Second();
			reservationPage.clickCloseReservationSavePopup(driver, testSteps);
			
			try {
				homePage.verifySetNowButtonIsShowingINPaymentSection(driver, testSteps);
			} catch (Exception | Error e) {
				testSteps.add(e.toString());
			}

			Wait.wait2Second();
			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed reservation tab");
			app_logs.info("Closed reservation tab");

			statusCode.add(1, "1");
		} catch (Exception e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create res", "NONGS_Login", "create res", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			e.printStackTrace();
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create res", "NONGS_Login", "create res", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

//		String reservationtoPay = "";

		try {
		Utility.refreshPage(driver);
		Wait.waitUntilPageIsLoaded(driver);
			testSteps.add("===== <a href='https://innroad.testrail.io/index.php?/cases/view/824915' target='_blank'>"
					+ "<b>CTI_5429-CP fails on reservation creation, payment, and status change when CC exp. date saved as 'mm/yy'</b></a> =====");

			reservationPage.click_NewReservation(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkInDate(driver, checkInDates.get(0));
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.checkOutDate(driver, checkInDates.get(1));
			testSteps.addAll(getTestSteps);

			reservationPage.enter_Adults(driver, testSteps, maxAdult);
			reservationPage.enter_Children(driver, testSteps, maxPerson);
			reservationPage.select_Rateplan(driver, testSteps, ratePlan, "");
			reservationPage.clickOnFindRooms(driver, testSteps);
			String isAssign = "Yes";

			reservationPage.select_Room(driver, testSteps, roomClassName, isAssign, "");
			reservationPage.clickNext(driver, testSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.enterGuestName(driver, getTestSteps, salutation, guestFirstName,
					guestLastName);
			testSteps.addAll(getTestSteps);

			getTestSteps.clear();
			getTestSteps = reservationPage.verifyGuestProfileCheckoxChecked(driver, false);
			testSteps.addAll(getTestSteps);

			reservationPage.enter_PaymentDetails(driver, testSteps, paymentTypeMethod, cardNumber, nameOnCard,
					cardExpDate);

			reservationPage.SelectReferral(driver, referral);

			reservationPage.clickBookNow(driver, testSteps);
			reservationNumber = reservationPage.get_ReservationConfirmationNumber(driver, testSteps);
			app_logs.info("reservationNumber : " + reservationNumber);
			reservationtoPay = reservationNumber;
			String reservationStatus = reservationPage.get_ReservationStatus(driver, testSteps);
			reservationPage.clickCloseReservationSavePopup(driver, testSteps);

			testSteps.add("Verify reservation status 'Reserved' is reserved");
			Utility.verifyEquals(reservationStatus.toLowerCase(), "Reserved".toLowerCase(), testSteps);

			homePage.verifyExpiryDate(driver, testSteps, cardExpDate);

			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed reservation tab");
			app_logs.info("Closed reservation tab");

			reservationSearch.basicSearchWithResNumber(driver, reservationNumber, true);
			testSteps.add("Searched and opened reservation with number : " + reservationNumber);
			app_logs.info("Searched and opened reservation with number : " + reservationNumber);

			reservationPage.changeReservationStatus(driver, "Reserved", "", testSteps);
			reservationPage.verifyReservationStatusStatus(driver, testSteps, "Reserved");

			testSteps.add("===== <a href='https://innroad.testrail.io/index.php?/cases/view/824628' target='_blank'>"
					+ "<b>Auth with CC c1 and Capture with CC c2</b></a> =====");

			testSteps.add("Add Payment as Authorize Only using " + paymentTypeMethod + " card : " + cardNumber);
			String paymentNotes = "";
			String TakePaymentType = "Authorization Only";
			String IsSetAsMainPaymentMethod = "No";
			String IsChangeInPayAmount = "Yes";
			String ChangedAmountValue = "10";
			homePage.clickTakePaymentButton(driver, testSteps);
			reservationPage.takePayment(driver, testSteps, paymentTypeMethod, cardNumber, nameOnCard, cardExpDate,
					TakePaymentType, IsChangeInPayAmount, ChangedAmountValue, IsSetAsMainPaymentMethod, paymentNotes);


			
			reservationPage.click_Folio(driver, testSteps);
			folio.verifyLineItemsState(driver, paymentTypeMethod, "transactionstatus-icon7", 1);

			reservationPage.click_DeatilsTab(driver, testSteps);

			paymentTypeMethod = "Visa";
			cardNumber = "4003000123456781";
			TakePaymentType = "Capture";
			testSteps.add("Add Payment as Capture using " + paymentTypeMethod + " card : " + cardNumber);
			homePage.clickTakePaymentButton(driver, testSteps);
			//Error
			reservationPage.takePayment(driver, testSteps, paymentTypeMethod, cardNumber, nameOnCard, cardExpDate,
					TakePaymentType, IsChangeInPayAmount, ChangedAmountValue, IsSetAsMainPaymentMethod, paymentNotes);

			
			reservationPage.click_Folio(driver, testSteps);
			folio.verifyLineItemsState(driver, paymentTypeMethod, "transactionstatus-icon8", 1);

			Wait.wait2Second();
			reservationPage.closeReservationTab(driver);
			testSteps.add("Closed reservation tab");
			app_logs.info("Closed reservation tab");
			statusCode.add(2, "1");
			statusCode.add(3, "1");

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create res", "NONGS_Login", "create res", driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to create res", "NONGS_Login", "create res", driver);
			} else {
				Assert.assertTrue(false);
			}
		}

		try {

			RetryFailedTestCases.count = Utility.reset_count;
			Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);

		} catch (Exception e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to change options in properties.", "ChangePropertiesOption",
						test_description, driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Error e) {
			if (Utility.reTry.get(testName) == Utility.count) {
				RetryFailedTestCases.count = Utility.reset_count;
				Utility.AddTest_IntoReport(testName, test_description, test_catagory, testSteps);
				Utility.updateReport(e, "Failed to change options in properties.", "ChangePropertiesOption",
						test_description, driver);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@DataProvider
	public Object[][] getData() {

		return Utility.getData("VerifyPaymentWithAllLedger", excel_Swarna);

	}

	@AfterClass(alwaysRun = true)
	public void closeDriver() throws MalformedURLException, IOException, APIException {
	Utility.updateTestCaseAndCloseDriver(driver, caseId, statusCode, comments,TestCore.TestRail_AssignToID);
	}

}
