package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FlightsPage extends BasePage {
    WebDriver driver;

    public FlightsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-testid='searchbox_origin']")
    WebElement whereFrom;
    @FindBy(css ="[data-testid='searchbox_origin_input']")
    WebElement whereFrom1;
    @FindBy(css = "[data-testid='searchbox_destination_input']")
    WebElement whereTo;
    @FindBy(css = "[data-testid='searchbox_calendar']")
    List<WebElement> dates;
    @FindBy(css = "[data-testid='searchbox_submit']")
    WebElement searchButton;



    public void addWhereFromLocation(String whereFromLocationText) throws InterruptedException {
            clickElement(whereFrom);
            clickElement(driver.findElement(By.xpath("//div[@class='css-1eii3rq']//.")));
        //Removes the current where from location
            typeText(whereFrom1, whereFromLocationText);
        //Adds a new where from location
            clickElement(driver.findElements(By.cssSelector("[data-testid='autocomplete_result']")).get(0));
        //Always selects the first option from dropdown

    }

    public void addWhereToLocation(String whereToLocationText) throws InterruptedException {
            typeText(whereTo, whereToLocationText);
            clickElement(driver.findElements(By.cssSelector("[data-testid='autocomplete_result']")).get(0));
    }


    public void setDates(String departDate, String returnDate) throws InterruptedException {
        //departDate must not be in the past
        //returnDate must be after departDate
        clickElement(dates.get(0));
        clickElement(driver.findElement(By.xpath("//span[@data-date-cell='" + departDate + "']")));
        Thread.sleep(1000);
        clickElement(dates.get(1));
        clickElement(driver.findElement(By.xpath("//span[@data-date-cell='" + returnDate + "']")));
        //date format Y-M-D
    }
    public void clickSearch() throws InterruptedException {
        clickElement(searchButton);
    }
}
