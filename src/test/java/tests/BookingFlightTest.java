package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactDetailsPage;
import pages.FlightsPage;
import pages.FlightsResultsPage;

import java.awt.*;
import java.io.IOException;


public class BookingFlightTest extends BaseTest {

    @BeforeMethod
    @Parameters
            ({"BROWSER", "BROWSER_VERSION", "WAIT", "ENV"})
    public void setUp(String browser, String browserVersion, String wait, String env) throws Exception {
        setUPTest(browser, browserVersion, Integer.parseInt(wait));
        startApplication(env);
    }

    @AfterMethod
    public void tearDown() {

        quit();
    }

    @Test(description = "Booking a flight")
    @Description ("Simple end to end test")
    @Parameters
            ({"whereFromLocation", "whereToLocation", "departDate", "returnDate", "indexOfCheckBox", "filters", "description", "email", "country", "contactNum", "firstName", "lastName", "gender", "monthOfBirth", "dayOfBirth", "yearOfBirth"})
    public void bookingFlightTest(String whereFromLocation,
                                  String whereToLocation,
                                  String departDate,
                                  String returnDate,
                                  int indexOfCheckBox,
                                  String filters,
                                  String description,
                                  String email,
                                  String country,
                                  int contactNum,
                                  String firstName,
                                  String lastName,
                                  String gender,
                                  int monthOfBirth,
                                  int dayOfBirth,
                                  int yearOfBirth)
            throws InterruptedException, IOException, AWTException {

        BasePage basePage = new BasePage(driver);
        FlightsPage flightsPage = new FlightsPage(driver);
        FlightsResultsPage flightsResultsPage = new FlightsResultsPage(driver);
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);

        flightsPage.addWhereFromLocation(whereFromLocation);
        flightsPage.addWhereToLocation(whereToLocation);
        flightsPage.setDates(departDate, returnDate);
        flightsPage.clickSearch();
        flightsResultsPage.dragSliderFilter();
        flightsResultsPage.clickFlightTimesCheckBox(indexOfCheckBox);
        basePage.scrollByPixels("400");
        reportScreenshot(filters,description);
        Assert.assertEquals("true", driver.findElements(By.xpath("//div[@class='css-icaorl']//input[@type='checkbox']")).get(2).getAttribute("checked"));
        flightsResultsPage.clickFlightButton();
        flightsResultsPage.clickSelectButton();
        basePage.clickElement(driver.findElement(By.cssSelector("[data-testid='checkout_ticket_type_inner_next'] , [data-testid='checkout_fare_inner_next']")));
        contactDetailsPage.enterContactEmail(email);
        contactDetailsPage.selectContactNumCountry(country);
        contactDetailsPage.enterContactNum(contactNum);
        contactDetailsPage.enterFirstName(firstName);
        contactDetailsPage.enterLastName(lastName);
        contactDetailsPage.setGender(gender);
        contactDetailsPage.setDateOfBirth(monthOfBirth, dayOfBirth, yearOfBirth);

    }

}
