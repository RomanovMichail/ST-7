package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\test po\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            System.out.println("--- ЗАДАНИЕ №1 ---");
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement passwordContainer = webDriver.findElement(By.xpath("//div[@id='content']//b[contains(text(), '$') or contains(text(), '@') or contains(text(), '#') or string-length() > 5]"));
            String generatedPassword = passwordContainer.getText();

            System.out.println("Полученный вариант пароля: " + generatedPassword);
            System.out.println();

            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}