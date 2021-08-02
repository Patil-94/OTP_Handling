package com.bridgelabz.selenium.test;

import com.bridgelabz.selenium.base.BaseClass;
import com.bridgelabz.selenium.pages.Login;
import com.bridgelabz.selenium.utility.Sign_Up;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_For_OTP extends BaseClass {

    @Test
    public void loginTo_Application_with_valid_credentials() throws InterruptedException {
        Login otpHandle = new Login(driver);
        otpHandle.login_with_mobile_otp();
        String actualUrl=driver.getCurrentUrl();
        System.out.println(actualUrl);
        String expectedUrl="https://www.ixigo.com/";
        Assert.assertEquals(actualUrl,expectedUrl);


    }
        @Test
        public void signUpTo_Application_with_valid_credentials() throws InterruptedException {
        Sign_Up signUp =new Sign_Up(driver);
        signUp.signup();

        String actualUrl=driver.getCurrentUrl();
        System.out.println(actualUrl);
        String expectedUrl="https://www.ixigo.com/";
        Assert.assertEquals(actualUrl,expectedUrl);
    }


}
