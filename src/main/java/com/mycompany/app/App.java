package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\test po\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try {
            System.out.println("=== ЗАДАНИЕ 1 ===");
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement passwordElement = webDriver.findElement(By.cssSelector("#resultid b"));
            String passwordText = passwordElement.getText();

            System.out.println("Сгенерированный пароль: " + passwordText);
            System.out.println();

            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}