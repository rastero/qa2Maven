package FirstTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertFalse;

public class OpenHomepage {
  private String pCHomePageURL = "http://rus.delfi.lv/";
  private String mHomePageURL = "http://m.rus.delfi.lv/";
  private static final By findElement = By.xpath("//a[@class ='top2012-title']");
  @Test
  public  void MozzilaTest () throws InterruptedException {
      System.setProperty("webdriver.gecko.driver", "C:/qajava/geckodriver.exe");
      WebDriver driver = new FirefoxDriver();
      driver.manage().window().maximize();
      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
      driver.get(pCHomePageURL);
      List<WebElement> pcTitles = driver.findElements(findElement);
      // check for empty
      assertFalse("No Elements Found in PC ver.", pcTitles.isEmpty());

      //open mobile url page
      driver.get(mHomePageURL);
      List<WebElement> mTitles = driver.findElements(findElement);
      //check for emty
      assertFalse("No Elements Found in Mobile ver. ", mTitles.isEmpty());

      //compare two Titles PC vs Mobile

    // public static void compareTwoLists(List pcTitles, List mTitles) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pcTitles.get(i).equals(mTitles.get(j)) == true) {
                    System.out.println("Title N" + i + " True.");
                }
                else {
                    System.out.println("Title N " + i +" False.");
                }
            }
        }
    }//
  }


