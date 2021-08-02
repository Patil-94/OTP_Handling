package com.bridgelabz.selenium.pages;
import com.bridgelabz.selenium.base.BaseClass;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Login extends BaseClass{
    public static final String ACCOUNT_SID = "ACad8a5d3734eb16d154bd232365c4a0b7";
    public static final String AUTH_TOKEN = "51299fae09df4cd92b1b043a412eae5e";

    @FindBy(xpath = "//span[@class='login-txt']")
    WebElement MyAccount;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile No. / Email']")
    WebElement mobile_no;

    @FindBy(xpath = "//span[@class='c-input-country-code']")
    WebElement click_country_code;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[5]/div/div/div[2]/div[2]/div/div[3]/div[1]/span[1]/span/ul/li[2]/span")
    WebElement selector_country_code;

    @FindBy(xpath = "//button[normalize-space()='LOGIN']")
    WebElement login_btn;

    @FindBy(xpath = "//input[@id='otp-0']")
    WebElement otp_input;

    @FindBy(xpath = "//button[normalize-space()='Verify']")
    WebElement verify_otp;



    public Login(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void login_with_mobile_otp() throws InterruptedException {
        MyAccount.click();
        Thread.sleep(1000);
        mobile_no.sendKeys("2674045299");
        Thread.sleep(1000);
        click_country_code.click();
        Thread.sleep(200);
        selector_country_code.click();
        Thread.sleep(3000);
        login_btn.click();
        Thread.sleep(1000);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String smsBody = getMessage();
        System.out.println(smsBody);
        String[] OTPNumber = smsBody.split("[^\\d]+");
        System.out.println(Arrays.toString(OTPNumber));
        Thread.sleep(2000);
        otp_input.sendKeys(OTPNumber);
        Thread.sleep(200);
        verify_otp.click();
        Thread.sleep(7000);


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
