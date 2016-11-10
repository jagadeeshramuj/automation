package test.travelhopesTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.InititateDriver;
import main.travelhopesPages.*;
public class SearchFlightTest {
  private WebDriver driver;
  private SearchFlightPage flight;
  
  @BeforeClass
  public void setupPreReqs() {
    driver = InititateDriver.getDriver();
    flight= new SearchFlightPage(this.driver);
  }
  
  @Test(enabled=true)
  public void search(){
    flight.testSearchFlights();
  }
  
 /* @AfterClass
  public void closeDriver(){
	  InititateDriver.killDriver();
  }*/
}
