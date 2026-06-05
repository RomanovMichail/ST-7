package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static void run(WebDriver webDriver) {
        try {
            System.out.println("=== ЗАДАНИЕ 2 ===");
            webDriver.get("https://api.ipify.org/?format=json");

            WebElement rawElement = webDriver.findElement(By.tagName("pre"));
            String rawJson = rawElement.getText();

            String ipAddress = rawJson.substring(rawJson.indexOf("\":\"") + 3, rawJson.lastIndexOf("\""));

            System.out.println("IP-адрес пользователя: " + ipAddress);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}