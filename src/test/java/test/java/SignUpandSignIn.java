package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SignUpandSignIn {


        WebDriver driver;
        SoftAssert assertion;


        @Parameters({"browser","viewport"})
        @Test(enabled = true)
        public void login(String browser,String viewport) throws InterruptedException {
            SoftAssert assertion= new SoftAssert();

            if(browser.equalsIgnoreCase("chrome"))
            {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("http://automationpractice.com/index.php");
            }
            else if (browser.equalsIgnoreCase("IE"))
            {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                driver.get("http://automationpractice.com/index.php");
            }
            System.out.println("Testcase starter");
            WebDriverWait wait = new WebDriverWait(driver, 100);
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='login']")));

            //Click Signin on the landing page
            List<WebElement> signinbtn = driver.findElements(By.xpath("//a[@class='login']"));

            assertTrue("sign in button available", signinbtn.size() > 0);
            if (signinbtn.size() > 0) {
                //clicking on sign in button
                signinbtn.get(0).click();
            }
            //check
            this.Signuptest(assertion);

        }


        @Step("Check signup")
        public void Signuptest(SoftAssert assertion) throws InterruptedException {

            Thread.sleep(1000);
            List<WebElement> Creataccount = driver.findElements(By.xpath("//h3"));
            assertTrue(Creataccount.get(0).getText().equalsIgnoreCase("CREATE AN ACCOUNT"));

            List<WebElement> Email = driver.findElements(By.id("email_create"));
            assertTrue("email field is available",Email.size()>0);
            Email.get(0).sendKeys("testjaya8@gmail.com");
            //click on create an account button

            List<WebElement> signupbutton = driver.findElements(By.id("SubmitCreate"));
            assertTrue("Create a accountis available",signupbutton.size()>0);
            signupbutton.get(0).click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            List<WebElement> firstname = driver.findElements(By.id("customer_firstname"));
            assertTrue("first name text field is available",firstname.size()>0);
            firstname.get(0).sendKeys("test");

            List<WebElement> lastname = driver.findElements(By.id("customer_lastname"));
            assertTrue("last name text field is available",lastname.size()>0);
            lastname.get(0).sendKeys("lasname");

            List<WebElement> password = driver.findElements(By.id("passwd"));
            assertTrue("password text field is available",password.size()>0);
            password.get(0).sendKeys("Test@123");

            List<WebElement> EnteredFirstnaem = driver.findElements(By.id("firstname"));
            String capturedname = EnteredFirstnaem.get(0).getAttribute("value");
            System.out.println(capturedname);

            List<WebElement> EnteredLastname = driver.findElements(By.id("lastname"));
            String capturedlastname = EnteredLastname.get(0).getAttribute("value");
            System.out.println(capturedlastname);
            System.out.println(capturedname   +" "+ capturedlastname);



            List<WebElement> address = driver.findElements(By.id("address1"));
            assertTrue("address text field is available",address.size()>0);
            address.get(0).sendKeys("39,sdsdsd");

            List<WebElement> city = driver.findElements(By.id("city"));
            assertTrue("address text field is available",city.size()>0);
            city.get(0).sendKeys("Dallas");

            Select drpState= new Select(driver.findElement(By.name("id_state")));
            drpState.selectByVisibleText("Texas");

            List<WebElement> zipcode = driver.findElements(By.id("postcode"));
            assertTrue("address text field is available",zipcode.size()>0);
            zipcode.get(0).sendKeys("76556");

            List<WebElement>  mobilenumber = driver.findElements(By.id("phone_mobile"));
            assertTrue("mobilenumber field is available",mobilenumber.size()>0);
            mobilenumber.get(0).sendKeys("8978908978");

            Thread.sleep(5000);

            List<WebElement>  aliasAddress = driver.findElements(By.id("address_alias"));
            assertTrue("Alas address field is available",aliasAddress.size()>0);
            //aliasAddress.get(0).sendKeys("test address");


            List<WebElement>  Registeer = driver.findElements(By.id("submitAccount"));
            assertTrue("Register is available",Registeer.size()>0);
            Registeer.get(0).click();

            Thread.sleep(5000);
            //Validate on the landing screen - correct name and surname is displayed

            List<WebElement>  myname = driver.findElements(By.xpath("//a[@title='View my customer account']/span"));
            String mynameinLanding = myname.get(0).getText();
            System.out.println(mynameinLanding);
            assertTrue("correct name and surname is displayed",mynameinLanding.equalsIgnoreCase(capturedname   +" "+ capturedlastname));

            //Logout and login again
            List<WebElement>  logout = driver.findElements(By.xpath("//a[@class='logout']"));
            assertTrue("Logout button is available",logout.size()>0);
            logout.get(0).click();
            //login again
            System.out.println("test");

            List<WebElement> Email_singninpage = driver.findElements(By.id("email"));

            assertTrue("email field is available",Email_singninpage.size()>0);
            Email_singninpage.get(0).sendKeys("testjaya123456@gmail.com");

            List<WebElement> pwd_singninpage = driver.findElements(By.id("passwd"));
            assertTrue("password field is available",pwd_singninpage.size()>0);
            pwd_singninpage.get(0).sendKeys("Test@123");

            List<WebElement> signin = driver.findElements(By.id("SubmitLogin"));
            assertTrue("SubmitLogin field is available",signin.size()>0);
            signin.get(0).click();


            WebElement menu = driver.findElement(By.xpath("//a[@title='Women']"));
            menu.click();
            Thread.sleep(9000);

            System.out.println("Done Mouse hover on 'T shirt' from Menu");
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 100);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul//li//a[@class='product_img_link']")));

            Thread.sleep(7000);
            WebElement productnamelis = driver.findElement(By.xpath("//div[@class='right-block']"));
            Actions a=new Actions(driver);
            a.moveToElement(productnamelis).perform();
            //added product to cart

            WebElement addtoCart = driver.findElement(By.xpath("//a[@data-id-product]"));
            addtoCart.click();

        }



        @AfterMethod
        public void teardown(){
            driver.close();
        }
    }


