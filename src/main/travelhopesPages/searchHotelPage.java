package main.travelhopesPages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Utils.InititateDriver;

public class searchHotelPage {
	private WebDriver driver;
	public searchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		driver.get("https://travelshoppestrial.travelshoppes.com");
		Assert.assertEquals(InititateDriver.getResponseCode(this.driver), 200);
		driver.findElement(By.xpath(".//*[@id='hotelblog']/a")).click();
	}
	
	public String getDate(Date date1){
		 DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy ");
		 String date2= dateFormat.format(date1);
		 return date2;
	  }
	
	public String getCheckinDate(){
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		  c.setTime(date); 
		  c.add(Calendar.DATE, 3);
		  date = c.getTime();
		 return this.getDate(date); 
	}
	public String getCheckOutDate(){
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		  c.setTime(date); 
		  c.add(Calendar.DATE,5);
		  date = c.getTime();
		 return this.getDate(date);
	}
	public void FindHotels(){
		WebDriverWait wait= new WebDriverWait(this.driver,20);
		driver.findElement(By.xpath(".//*[@id='autocomplete_hotel_city']")).sendKeys("Hyd ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ui-id-1'][@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']")));
		driver.findElement(By.xpath(".//*[@id='ui-id-1'][@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']/li[2]")).click();
		//List<WebElement> optionsToSelect = driver.findElements(By.xpath(".//*[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']"));
		//wait.until(ExpectedConditions.elementToBeSelected(optionsToSelect.get(2)));
		//optionsToSelect.get(2).click();
		
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		String checkinDate=this.getCheckinDate();
		String checkOutDate=this.getCheckOutDate();
		//driver.findElement(By.xpath(".//*[@class='demoWrapper htl-li']//*[@id='check_in']")).sendKeys(checkinDate);
		//driver.findElement(By.xpath(".//*[@class='demoWrapper htl-li']//*[@id='check_out']")).sendKeys(checkOutDate);
		driver.findElement(By.xpath(".//*[@id='hotelsearch']")).click();
	}
}
