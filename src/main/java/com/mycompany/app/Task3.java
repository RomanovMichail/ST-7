package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileWriter;
import java.io.File;

public class Task3 {
    public static void run(WebDriver webDriver) {
        try {
            System.out.println("--- ЗАДАНИЕ №3 ---");
            webDriver.get("https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms");
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            String header = String.format("%-3s | %-16s | %-12s | %-12s\n", "№", "Дата/время", "Температура", "Осадки (мм)");
            String separator = "---------------------------------------------------------\n";
            StringBuilder sb = new StringBuilder();
            sb.append(header).append(separator);

            for (int i = 0; i < times.size(); i++) {
                sb.append(String.format("%-3d | %-16s | %-11s°C | %-12s\n",
                        (i + 1), times.get(i), temperatures.get(i), rains.get(i)));
            }

            System.out.print(sb.toString());

            File dir = new File("result");
            if (!dir.exists()) dir.mkdir();

            FileWriter writer = new FileWriter("result/forecast.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}