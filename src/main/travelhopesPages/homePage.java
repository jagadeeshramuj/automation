package main.travelhopesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utils.InititateDriver;

public class homePage {
	private WebDriver driver;
	 public homePage(WebDriver driver) {
		    this.driver = driver;
		    PageFactory.initElements(this.driver, this);
		    driver.get("https://travelshoppestrial.travelshoppes.com");
		    Assert.assertEquals(InititateDriver.getResponseCode(this.driver), 200);
		  }
	 
	 public void clickhomePage(){
		 driver.findElement(By.xpath(".//a[@href='/']")).click();
	 }
	 
	 public void clickActivities(){
		 driver.findElement(By.xpath(".//*[@id='activitiesblog']")).click();
		 
	 }
	 
	 public void clickPackages(){
		 driver.findElement(By.xpath(".//*[@id='packageblog']")).click();
		 driver.findElement(By.xpath(".//*[@id='autocomplete_package_city']")).sendKeys("Singapore, Singapore");
		 //driver.findElement(By.xpath(".//*[@id='autocomplete_package_city']")).sendKeys(" ");
		 //driver.findElement(By.xpath(".//*[@id='ui-id-4'][@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']/li")).click();
		 Select numAdults= new Select(driver.findElement(By.xpath(".//*[@class='adultBlogPackageRoom_1']/*[@class='ffSelectWrapper']")));
		 numAdults.selectByIndex(2);
		 Select numofNights=new Select(driver.findElement(By.xpath(".//*[@class='nightsimg']/*[@class='ffSelectWrapper']")));
		 numofNights.selectByIndex(1);
		 //driver.findElement(By.xpath(".//*[@id='search-form-packages_1']/div/div[1]/div[1]/ul/li[6]/div[2]/div/div/div[2]/div/ul/li[3]")).click();
		 driver.findElement(By.xpath(".//*[@id='packagessearchbox']")).click();
	 }
	 
	 public void clickMyRewards(){
		 driver.findElement(By.xpath(".//*[@id='welcometext']/ul/li[1]/div/a")).click();
	 }
}
