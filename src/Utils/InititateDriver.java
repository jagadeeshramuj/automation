package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.fluent.Request;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InititateDriver {
  private static WebDriver driver;
  public static Properties properties;
  private static String filePath = "src/res/automation_usercredentials.properties";
  public static synchronized WebDriver getDriver() {
    if (driver != null) {
      return driver;
    }
    FirefoxBinary binary = new FirefoxBinary();
    driver = new FirefoxDriver();
    binary.setTimeout(120000);
    driver.manage().window().maximize();
    return driver;
  }
  
  public static void killDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
  
  public static int getResponseCode(WebDriver driver) {
    System.out.println("the currenturl is :" + driver.getCurrentUrl());
    try {
      return Request.Get(driver.getCurrentUrl()).execute().returnResponse().getStatusLine().getStatusCode();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static void loadProperties() {

    properties = new Properties();
    try {
      FileInputStream fis;
      fis = new FileInputStream(new File(filePath));
      properties.load(fis);

    } catch (FileNotFoundException e) {

    } catch (IOException e) {

    }

  }
}
