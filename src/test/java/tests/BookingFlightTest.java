package tests;

import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FlightsPage;


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
            ({"whereFromLocation", "whereToLocation", "departDate", "returnDate"})
    public void bookingFlightTest(String whereFromLocation, String whereToLocation, String departDate, String returnDate) throws InterruptedException {

        BasePage basePage = new BasePage(driver);
        FlightsPage flightsPage = new FlightsPage(driver);

        flightsPage.addWhereFromLocation(whereFromLocation);
        flightsPage.addWhereToLocation(whereToLocation);
        flightsPage.setDates(departDate, returnDate);
        flightsPage.clickSearch();

    }

}
