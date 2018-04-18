package checkForExpire;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static java.nio.file.Paths.*;

public class lurosft {

    // ---- Article Class ---- \\

    private static final String mainPage = "http://www.lursoft.lv/";
    private static final By connect = By.xpath(".//span[text()='Pieslēgties']");
    private static final By login = By.id("UserId");
    private static final By pass = By.name("Password");
    private static final By enterPage = By.className("btn_login");
    private static final By chooseUser = By.className("LIVIKO");
    private static final By enterUser = By.className("Apstiprināt");
    private static final By dateFild = By.id("searchField");
    private static final By searchButton = By.xpath(".//span[text()='Meklēt']");
    private static final By saveButton = By.xpath(".//span[text()='Saglabāt']");
    private String link = "c:/lur.txt";

    // ---- End of Article class ---- \\

    // ---- Login and download Class --- \\

    @Test
    public void searchAndDownload() throws IOException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String loginFromTxt = "";
        String passFromTxt = "";

            Optional<String> line = Files.lines(Paths.get(link)).findFirst();
            loginFromTxt = (line.get());
            Optional<String> line2 = Files.lines(Paths.get(link)).skip(1).findFirst();
            passFromTxt = (line2.get());

        System.setProperty("webdriver.gecko.driver", "C:/qajava/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.lursoft.lv/");
        driver.findElement(connect).click();
        driver.findElement(login).sendKeys(loginFromTxt);
        driver.findElement(pass).sendKeys(passFromTxt);
        driver.findElement(enterPage).click();
        driver.findElement(chooseUser).click();
        driver.findElement(enterUser).click();
        driver.findElement(dateFild).sendKeys(formatForDateNow.format(dateNow));
        driver.findElement(searchButton).click();
        driver.findElement(saveButton).click();

       // --- End Of Login And Download class ---- \\

       // --- Lursoft Excel Check class ---- \\

        String lurFile = "C:/Users/mp/Downloads/test.xls";
        String navFile = "C:/Microsoft Dynamics NAV/CSIDE Client/saves/clients.xls";
                String value = "Anulēt";
        // When xls change at xlsx need to chage HSS ON XSS !!!!!!!!!!!!!!!!!
 /*           FileInputStream lurExcelFile = new FileInputStream(new File(lurFile));
            HSSFWorkbook lurWorkbook = new HSSFWorkbook(lurExcelFile);
            HSSFSheet lurSheet = lurWorkbook.getSheet("Sheet1");
            HSSFRow lurRow = lurSheet.getRow(0);
   */
        HSSFWorkbook navWorkbook =  new HSSFWorkbook(new FileInputStream(navFile));
        HSSFSheet navSheet = navWorkbook.getSheet("sheet1");


        HSSFWorkbook lurWorkbook =  new HSSFWorkbook(new FileInputStream(lurFile));
        HSSFSheet lurSheet = lurWorkbook.getSheet("sheet1");
        HSSFSheet compareSheet = lurWorkbook.createSheet("temp");

        for(int r=0; r<lurSheet.getLastRowNum()+1; r++) {
              HSSFRow row = lurSheet.getRow(r);
              HSSFCell disabledCell = row.getCell((short) 2);
              HSSFCell regLurCell = row.getCell((short) 5);

                if ( disabledCell.equals(value)  ){
                    Row row = compareSheet.createRow(compareSheet.getLastRowNum()+1);
                    Cell cell = row.createCell((short) 0);
                    cell.setCellValue(regLurCell);




                }



        }
    }
}
