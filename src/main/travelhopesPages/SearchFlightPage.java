package main.travelhopesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import Utils.InititateDriver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SearchFlightPage {
  private WebDriver driver;
  Date date = new Date();
  
  public String getDate(Date date){
	 DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy ");
	 String date1= dateFormat.format(date);
	 System.out.println(date1);
	 return date1;
  }
  
  public String getfromDate(){	  
	  Calendar c = Calendar.getInstance(); 
	  c.setTime(date); 
	  c.add(Calendar.DATE, 10);
	  date = c.getTime();
	 return this.getDate(date); 
  }
  public String getReturnDate(){
	  Calendar c = Calendar.getInstance(); 
	  c.setTime(date); 
	  c.add(Calendar.DAY_OF_MONTH, 3);
	  date=c.getTime();
	  return this.getDate(date);
  }
  public SearchFlightPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(this.driver, this);
    driver.get("https://travelshoppestrial.travelshoppes.com");
    Assert.assertEquals(InititateDriver.getResponseCode(this.driver), 200);
  }
  
  public void testSearchFlights() {
    driver.findElement(By.xpath(".//*[@id='flightblog']/a")).click();
    driver.findElement(By.xpath(".//*[@id='From']")).sendKeys("Hyderabad (HYD), India");
    driver.findElement(By.xpath(".//*[@id='To']")).sendKeys("Bangalore (BLR), India");
    driver.findElement(By.xpath(".//*[@id='dateDepart']")).sendKeys(this.getfromDate());
    driver.findElement(By.xpath(".//*[@id='dateReturn']")).sendKeys(this.getReturnDate());
    driver.findElement(By.xpath(".//*[@id='btnsearch']")).click();
  }
}
