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
            System.out.println("=== ЗАДАНИЕ 3 ===");
            webDriver.get("https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms");

            WebElement jsonElement = webDriver.findElement(By.tagName("pre"));
            String textResponse = jsonElement.getText();

            JSONParser jsonParser = new JSONParser();
            JSONObject rootObject = (JSONObject) jsonParser.parse(textResponse);
            JSONObject hourlyData = (JSONObject) rootObject.get("hourly");

            JSONArray timeArray = (JSONArray) hourlyData.get("time");
            JSONArray tempArray = (JSONArray) hourlyData.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourlyData.get("rain");

            String tableResult = String.format("%-4s | %-19s | %-12s | %-12s\n", "№", "Дата / Время", "Темп. (°C)", "Осадки (мм)");
            tableResult += "=================================================================\n";

            for (int i = 0; i < timeArray.size(); i++) {
                tableResult += String.format("%-4d | %-19s | %-12s | %-12s\n",
                        (i + 1), timeArray.get(i), tempArray.get(i), rainArray.get(i));
            }

            System.out.print(tableResult);

            File folder = new File("result");
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }

            FileWriter fileWriter = new FileWriter(new File(folder, "forecast.txt"));
            fileWriter.write(tableResult);
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}