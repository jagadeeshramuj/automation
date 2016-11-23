package test.travelhopesTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.InititateDriver;
import main.travelhopesPages.searchHotelPage;

public class SearchHotelTest {
	private WebDriver driver;
	private searchHotelPage hotel;

	@BeforeClass
	public void setupPreReqs() {
		driver = InititateDriver.getDriver();
		hotel = new searchHotelPage(this.driver);
	}
	
	@Test(enabled=true)
	public void testFindHotels(){
		hotel.testTravellandaProvider();
		}
	}
