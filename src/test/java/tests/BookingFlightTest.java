package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FlightsPage;
import pages.FlightsResultsPage;

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
            ({"whereFromLocation", "whereToLocation", "departDate", "returnDate", "indexOfCheckBox", "filters", "description"})
    public void bookingFlightTest(String whereFromLocation,
                                  String whereToLocation,
                                  String departDate,
                                  String returnDate,
                                  int indexOfCheckBox,
                                  String filters,
                                  String description)
            throws InterruptedException, IOException {

        BasePage basePage = new BasePage(driver);
        FlightsPage flightsPage = new FlightsPage(driver);
        FlightsResultsPage flightsResultsPage = new FlightsResultsPage(driver);

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

    }

}
