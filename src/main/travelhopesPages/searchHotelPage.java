package main.travelhopesPages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
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
	private String travlandaHotel = ".//*[@class='hotelsresultblog'][@name='travellanda']/div[2]/div[2]//*[@class='selectbtn']";

	public searchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.getHotelsearchpage();
	}
	
	public void getHotelsearchpage(){
		driver.get("https://travelshoppestrial.travelshoppes.com");
		Assert.assertEquals(InititateDriver.getResponseCode(this.driver), 200);
		driver.findElement(By.xpath(".//*[@id='hotelblog']/a")).click();
	}
	public String getDate(Date date1) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy ");
		String date2 = dateFormat.format(date1);
		return date2;
	}

	public String getCheckinDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 3);
		date = c.getTime();
		return this.getDate(date);
	}

	public String getCheckOutDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 5);
		date = c.getTime();
		return this.getDate(date);
	}

	public void FindHotels(String city, String provider) {
		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		String currentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath(".//*[@id='autocomplete_hotel_city']")).sendKeys(city+"  ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ui-id-1']")));
		List<WebElement> listItems = driver.findElements(By.xpath(".//*[@id='ui-id-1']"));
		listItems.get(0).click();
		// css=.ul.ui-autocomplete li:first a
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-1")));
		// driver.findElement(By.cssSelector(".ul.ui-autocomplete
		// li:first")).click();
		// driver.findElement(By.xpath(".//*[@id='autocomplete_hotel_city']")).sendKeys("Hyd
		// ");
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ui-id-1'][@class='ui-autocomplete
		// ui-front ui-menu ui-widget ui-widget-content']")));
		// driver.findElement(By.xpath(".//*[@id='ui-id-1'][@class='ui-autocomplete
		// ui-front ui-menu ui-widget ui-widget-content']/li[2]")).click();
		// List<WebElement> optionsToSelect =
		// driver.findElements(By.xpath(".//*[@class='ui-autocomplete ui-front
		// ui-menu ui-widget ui-widget-content']"));
		// wait.until(ExpectedConditions.elementToBeSelected(optionsToSelect.get(2)));
		// optionsToSelect.get(2).click();

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String checkinDate = this.getCheckinDate();
		String checkOutDate = this.getCheckOutDate();
		// driver.findElement(By.xpath(".//*[@class='demoWrapper
		// htl-li']//*[@id='check_in']")).sendKeys(checkinDate);
		// driver.findElement(By.xpath(".//*[@class='demoWrapper
		// htl-li']//*[@id='check_out']")).sendKeys(checkOutDate);
		driver.findElement(By.xpath(".//*[@id='hotelsearch']")).click();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		Assert.assertEquals(200, InititateDriver.getResponseCode(this.driver));
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		if (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")) {
			if(!(driver.findElements(By.xpath(".//*[@id='rightcontentresultsholder']/div/p")).size()>0)){
				if(!(driver.findElements(By.xpath(".//*[@class='hotelsresultblog'][@name='"+provider+"']/div[2]/div[2]/div[2]")).size()>0)){
					wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(".//*[@class='hotelsresultblog'][@name='"+provider+"']/div[2]/div[2]/div[2]")));
			List<WebElement> listItems2 = driver.findElements(
					By.xpath(".//*[@class='hotelsresultblog'][@name='"+provider+"']/div[2]/div[2]/div[2]"));
			wait.until(ExpectedConditions.visibilityOfAllElements(listItems2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(".//*[@class='hotelsresultblog'][@name='"+provider+"']/div[2]/div[2]/div[3]/a")));
			List<WebElement> TravelendaHotel = driver.findElements(By.xpath(
					".//*[@class='hotelsresultblog'][@name='"+provider+"']/div[2]/div[2]//*[@class='selectbtn']"));
			System.out.println(TravelendaHotel.size());
			for (int index = 0; index < TravelendaHotel.size(); index++) {
				TravelendaHotel.get(index).click();
				ArrayList<String> openedWindows = new ArrayList<String>(driver.getWindowHandles());
				if (openedWindows.size() > 1 && openedWindows.get(1) != currentWindow) {
					driver.switchTo().window(openedWindows.get(1));
					driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
					Assert.assertEquals(InititateDriver.getResponseCode(driver), 200);
					if (!(driver.findElements(By.xpath(".//*[@id='container']/div/p/a")).size() > 0)) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(".//*[@class='booknow bookthishotel selecthotel']")));
						driver.findElement(By.xpath(".//*[@class='booknow bookthishotel selecthotel']")).click();
						Assert.assertEquals(200, InititateDriver.getResponseCode(this.driver));
						if (!(driver.findElements(By.xpath(".//*[@id='travelshoppessignout']")).size()>0)) {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(".//*[@class='guestaccount']")));
							driver.findElement(By.xpath(".//*[@class='guestaccount']")).click();
						}
						
					}
					else{
						System.out.println(driver.findElement(By.xpath(".//*[@id='container']/div/p/a")).getText());
					}
					driver.close();
					driver.switchTo().window(currentWindow);
					System.out.println(index);
				}

			}
			}
			}
			this.getHotelsearchpage();
			driver.findElement(By.xpath(".//*[@id='autocomplete_hotel_city']")).clear();
		}
	}
	
	public void testTravellandaProvider(){
		
		String city="side,turkey";
		String provider="travellanda";
		this.FindHotels(city, provider);
		city="kuala Lumpur";
		this.FindHotels(city, provider);
		city="Singapore";
		this.FindHotels(city, provider);
		
	}
}
