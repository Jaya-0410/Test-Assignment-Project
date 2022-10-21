package com.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {
System.setProperty("webdriver.chrome.driver","D:\\Jaya Capgemini Folder\\chromedriver_win32\\chromedriver.exe");

      WebDriver driver=new ChromeDriver();

      driver.get("https://www.amazon.in");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement searchbox = driver.findElement(By.id("twotabsearchtextbox"));

        searchbox.sendKeys("Fridge");
        driver.findElement(By.id("nav-search-submit-button")).click();




    }
}
