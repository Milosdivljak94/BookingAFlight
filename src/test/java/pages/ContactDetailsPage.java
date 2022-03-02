package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class ContactDetailsPage extends BasePage{
    WebDriver driver;

    public ContactDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[type='email']")
    WebElement contactEmail;
    @FindBy(css = ".css-1rbx0uy")
    WebElement countryCodeSelect;
    @FindBy(css = "[name='phone']")
    WebElement contactNum;
    @FindBy(css = "[name='passengers.0.firstName']")
    WebElement firstName;
    @FindBy(css = "[name='passengers.0.lastName']")
    WebElement lastName;
    @FindBy(css = "[name='passengers.0.gender']")
    WebElement genderSelect;
    @FindBy(css = "[name='month']")
    WebElement dateOfBirthM;
    @FindBy(css = "[name='day']")
    WebElement dateOfBirthD;
    @FindBy(css = "[name='year']")
    WebElement dateOfBirthY;


    public void enterContactEmail(String email) throws InterruptedException {
        typeText(contactEmail,email);
    }
    public void selectContactNumCountry(String country) throws InterruptedException, AWTException {

        clickElement(countryCodeSelect);
        typeString(country);
    }
    public void enterContactNum(Integer ContactNumber) throws InterruptedException {
        clickElement(contactNum);
        typeText(contactNum, String.valueOf(ContactNumber));
    }
    public void enterFirstName(String name) throws InterruptedException {
        typeText(firstName, name);
    }
    public void enterLastName(String surname) throws InterruptedException {
        typeText(lastName, surname);
    }
    public void setGender(String value) throws InterruptedException {clickElement(genderSelect);
        selectByValue(genderSelect,value);
    }
    public void setDateOfBirth(int month, int day, int year) throws InterruptedException {
        typeText(dateOfBirthM, String.valueOf(month));
        typeText(dateOfBirthD, String.valueOf(day));
        typeText(dateOfBirthY, String.valueOf(year));
    }
}
