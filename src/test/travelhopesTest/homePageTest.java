package test.travelhopesTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.InititateDriver;
import main.travelhopesPages.SearchFlightPage;
import main.travelhopesPages.homePage;

public class homePageTest {
	private WebDriver driver;
	 private homePage home;
	 @BeforeClass
	  public void setupPreReqs() {
	    driver = InititateDriver.getDriver();
	    home= new homePage(this.driver);
	  }
	 @Test(enabled=true)
	 public void testclickHomePage(){
		 home.clickhomePage();
	 }
	 @Test(enabled=true,dependsOnMethods={"testclickHomePage"})
	 public void testclickMyRewards(){
		 home.clickMyRewards();
		 home.clickhomePage();
	 }
	 @Test(enabled=false,dependsOnMethods={"testclickMyRewards"})
	 public void testclickActivities(){
		 home.clickActivities();
		 home.clickPackages();
	 }
}
