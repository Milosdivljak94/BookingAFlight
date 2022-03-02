package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightsResultsPage extends BasePage {
    WebDriver driver;

    public FlightsResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = (".InputSlider-module__handle___25x3q"))
    WebElement sliderTip;
    @FindBy(xpath = ("//div[@class='css-icaorl']//input[@type='checkbox']"))
    List<WebElement> checkBox;
    @FindBy(xpath = ("//div[@id='flightcard-0']//button"))
    WebElement seeFlightButton;
    @FindBy(css = ("[data-testid='flight_details_inner_modal_select_button']"))
    WebElement selectButton;


    public void dragSliderFilter() throws InterruptedException {

        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(sliderTip));
        wdWait.until(ExpectedConditions.elementToBeClickable(sliderTip));
        Thread.sleep(1000); //sometimes doesn't work without sleep

        try {
            Actions act = new Actions(driver);
            act.dragAndDropBy(sliderTip, -200, -200).perform();
        } catch (StaleElementReferenceException e) {
            Thread.sleep(2000);
            Actions act = new Actions(driver);
            act.dragAndDropBy(sliderTip, -200, -200).perform();
        }
    }

    public void clickFlightTimesCheckBox(int indexOfCheckBox) throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", checkBox.get(indexOfCheckBox));
//        clickElement(checkBox.get(indexOfCheckBox)); doesn't do the job here
    }

    public void clickFlightButton() throws InterruptedException {
        clickElement(seeFlightButton);
    }

    public void clickSelectButton() throws InterruptedException {
        clickElement(selectButton);
    }
}

