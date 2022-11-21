package practice_TestNG_Swag;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class LetsDoTest {
	public WebDriver driver;

	@BeforeTest
	public void Login() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

	}

	@Test(groups = "1")
	public void sort_items_low_to_high() {
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
		List<WebElement> thePriceList = driver.findElements(By.className("inventory_item_price"));
		List<Double> newEditedList = new ArrayList<>();
		for (int i = 0; i < thePriceList.size(); i++) {
//			System.out.println(thePriceList.get(i).getText());

			String price = thePriceList.get(i).getText();
			// price.trim(); //to remove all spaces in string
			String editedPrice = price.replace("$", " ");
			// System.out.println("******************old price**********************");
			// System.out.println(price);
			// System.out.println("******************new price**********************");
			// System.out.println(editedPrice.trim()); string hay al arqam
//			String[] a = price.split("$");
//			System.out.println(price);
			double val = Double.parseDouble(editedPrice.trim());
			newEditedList.add(val);
		}
		for (int k = 0; k < newEditedList.size(); k++) {
			boolean checkProcess = newEditedList.get(0) < newEditedList.get(newEditedList.size() - 1);
			Assert.assertEquals(checkProcess, true);
		}

	}
	

}
