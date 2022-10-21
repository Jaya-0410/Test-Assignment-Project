package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.assertTrue;

public class HomeLoan {


    WebDriver driver;
    SoftAssert assertion;
//    @BeforeMethod
//    public void setup() {
//
//        //System.setProperty("webdriver.chrome.driver", "D:\\Jaya Capgemini Folder\\chromedriver_win32\\chromedriver.exe");
//
//        //driver = new ChromeDriver();
//
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
//        driver.manage().window().maximize();
//    }

    @Parameters({"browser","viewport"})
    @Test(priority = 0, description ="Itest",enabled = true)
    public void homeloan(String browser,String viewport) throws InterruptedException {

        SoftAssert assertion= new SoftAssert();

        if(browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");

        }
        else if (browser.equalsIgnoreCase("IE"))
        {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
        }


        String title = driver.getTitle();
        String str = driver.findElement(By.id("application_type_single")).getAttribute("checked");

        if (str.equalsIgnoreCase("true"))
        {
            System.out.println("single type is checked");
        }

        Boolean dependantdropdownPresent = driver.findElement(By.xpath("//select[@title='Number of dependants']")).isDisplayed();
        assertTrue("dependant dropdown disaplyed",dependantdropdownPresent==true);

        if(dependantdropdownPresent==true)
        {
            System.out.println("Dropdown is appearing");
        }
        else{
            System.out.println("Dropdown is not appearing");
        }

        String str2 = driver.findElement(By.id("application_type_single")).getAttribute("checked");

        if (str2.equalsIgnoreCase("true"))
        {
            System.out.println("single type is checked");
        }
        WebElement annualincome = driver.findElement(By.xpath("//input[@aria-labelledby='q2q1']"));
        assertTrue("annual income box is available",annualincome.isDisplayed());
        annualincome.sendKeys("80000");
        WebElement otherincome = driver.findElement(By.xpath("//input[@aria-labelledby='q2q2']"));
        assertTrue("annual income box is available",otherincome.isDisplayed());
        otherincome.sendKeys("10000");
        String otherincomestorevalue = otherincome.getText();

        WebElement livingExpense = driver.findElement(By.xpath("//input[@aria-labelledby='q3q1']"));
        assertTrue("annual income box is available",livingExpense.isDisplayed());
        livingExpense.sendKeys("500");
        //enter other loan details

        WebElement otherloan = driver.findElement(By.xpath("//input[@id='otherloans']"));
        assertTrue("annual income box is available",otherloan.isDisplayed());
        livingExpense.sendKeys("100");

        WebElement credit = driver.findElement(By.xpath("//input[@id='credit']"));
        assertTrue("annual income box is available",credit.isDisplayed());
        livingExpense.sendKeys("10000");
        //click on borrow button
        WebElement borrowbtn = driver.findElement(By.xpath("//button[@id='btnBorrowCalculater']"));
        assertTrue("annual income box is available",borrowbtn.isDisplayed());
        borrowbtn.click();
        WebElement borrowestimateamount = driver.findElement(By.xpath("//span[@id='borrowResultTextAmount']"));
        System.out.println(borrowestimateamount.getAttribute("innerHtml"));
        //Clicking the ‘start over’ button clears the form.
        WebElement startover = driver.findElement(By.xpath("//button[@aria-label='Start over']"));
        assertTrue("start oveer button is available",startover.isEnabled());
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", startover);
        Thread.sleep(5000);
        String otherincomeafterstartover = otherincome.getText();
        assertTrue("start oveer button is available",otherincomeafterstartover!=otherincomestorevalue);
        //Entering only $1 for Living expenses, and leaving all other fields as zero, clicking ‘Work out how much I could borrow’
        livingExpense.sendKeys("1");
        borrowbtn.click();
        Thread.sleep(5000);
        WebElement errormessage = driver.findElement(By.xpath("//div[@class='borrow__error__text']"));
        System.out.println(errormessage.getText());
        String expected_errormsg="Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.";
        assertTrue(expected_errormsg.contains(errormessage.getText())); }



    @AfterMethod
    public void teardown(){
        driver.close();
    }
}
