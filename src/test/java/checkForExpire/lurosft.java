package checkForExpire;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class lurosft {
    private static final String mainPage = "http://www.lursoft.lv/";
    private static final By CONNECT = By.xpath(".//span[text()='Pieslēgties']");
    private static final By login = By.id("UserId");
    private static final By pass = By.name("Password");
    private static final By enterPage = By.className("btn_login");
    private String link = "c:/lur.txt";
    //here was an locator on download


//    public lurosft() {
//    }

    @Test
    public void searchAndDownload() {
        String loginFromTxt = "";
        String passFromTxt = "";

        try {
            loginFromTxt = Files.readAllLines(Paths.get(link)).get(1);
            passFromTxt = Files.readAllLines(Paths.get(link)).get(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.gecko.driver", "C:/qajava/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.lursoft.lv/");
        driver.findElement(CONNECT).click();
        driver.findElement(login).sendKeys(loginFromTxt);
        driver.findElement(pass).sendKeys(passFromTxt);
        driver.findElement(enterPage).click();
        // here download file


    }
}
