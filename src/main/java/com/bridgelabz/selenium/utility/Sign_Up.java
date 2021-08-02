package com.bridgelabz.selenium.utility;

import com.bridgelabz.selenium.base.BaseClass;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Sign_Up extends BaseClass {
    public static final String ACCOUNT_SID = "ACad8a5d3734eb16d154bd232365c4a0b7";
    public static final String AUTH_TOKEN = "51299fae09df4cd92b1b043a412eae5e";

    @FindBy(xpath = "//span[@class='user-login ixi-icon-user']")
    WebElement clickOnAccount;

    @FindBy(xpath = "//span[normalize-space()='SIGN UP']")
    WebElement signUpBtn;

    @FindBy(xpath = "//div[1]//div[1]//div[2]//div[2]//div[1]//div[3]//div[1]//span[1]//div[1]//div[1]//span[1]")
    WebElement selectorMs;

    @FindBy(xpath = "//span[normalize-space()='Miss']")
    WebElement select_Miss;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstname;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastname;

    @FindBy(xpath = "//input[@placeholder='Enter your email ID']")
    WebElement UserEmail;

    @FindBy(xpath = "//span[@class='c-country-code-wrapper']")
    WebElement click_country_code;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[5]/div/div/div[2]/div[2]/div/div[5]/span[1]/span/ul/li[2]")
    WebElement selector_country_code;

    @FindBy(name = "mobile")
    WebElement mobileNumber;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    WebElement register_btn;

    @FindBy(xpath = "//input[@id='otp-0']")
    WebElement otp_enter;

    @FindBy(xpath = "//button[normalize-space()='Verify']")
    WebElement verify_otp;


    public Sign_Up(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void signup() throws InterruptedException {

        clickOnAccount.click();
        Thread.sleep(1000);
        signUpBtn.click();
        Thread.sleep(1000);
        selectorMs.click();
        Thread.sleep(200);
        select_Miss.click();
        Thread.sleep(1000);
        firstname.sendKeys("latika");
        Thread.sleep(200);
        lastname.sendKeys("patil");
        Thread.sleep(200);
        UserEmail.sendKeys("latikakhairnar@gmail.com");
        Thread.sleep(200);
        click_country_code.click();
        Thread.sleep(2100);
        selector_country_code.click();
        Thread.sleep(3000);
        mobileNumber.sendKeys("2674045299");
        Thread.sleep(300);
        register_btn.click();
        Thread.sleep(2000);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String smsBody = getMessage();
        System.out.println(smsBody);
        String[] OTPNumber = smsBody.split("[^\\d]+");
        System.out.println(Arrays.toString(OTPNumber));
        Thread.sleep(2000);
        otp_enter.sendKeys(OTPNumber);
        Thread.sleep(200);
        verify_otp.click();
        Thread.sleep(2000);
    }
    public static String getMessage() {
        return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals("+12674045299")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }

}
