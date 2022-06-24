package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



public class BaseClass {
   public static WebDriver driver;
   public static Properties prop;
  public static   WebDriverWait wait;

    @BeforeMethod
    public WebDriver SetUp() throws InterruptedException {

        By LoginButton = By.xpath("//button[@type='button']");
        By DeloitteIdButton = By.xpath("(//input[@type='button'])[2]");
        By MailIdField = By.xpath("//input[@type='email']");
        By SubmitButton = By.xpath("//input[@type='submit']");


//        System.setProperty("webdriver.chrome.driver", "C://selenium jars and drivers//driver//Chromedriver//chromedriver.exe");
//        File file = new File("C://Users//bochandana//IdeaProjects//AE//src//test//Config.properties");

        System.setProperty("webdriver.chrome.driver","C://Users//saurasahu//IdeaProjects//drivers//chromedriver.exe");
        File file = new File("C://Users//saurasahu//Desktop//Copy_AllocationEngine//AE_chandana//src//test//Config.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         prop = new Properties();

        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("Url"));
//        driver.findElement(LoginButton).click();
//        driver.findElement(DeloitteIdButton).click();
//        driver.findElement(MailIdField).sendKeys(prop.getProperty("Email"));
//        driver.findElement(SubmitButton).click();


        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        return driver;

    }
    public void do_click(By element) throws InterruptedException {
        //System.out.println(element);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        driver.findElement(element).click();
        Thread.sleep(1000);
    }
    public void js_click(By element) throws InterruptedException {
       // System.out.println(element);

        wait.until(ExpectedConditions.presenceOfElementLocated(element));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(element));

        new WebDriverWait(driver, 20).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));

        Thread.sleep(1000);
    }
    public void go_to_allocation_engine() throws InterruptedException {
        driver.get("https://dna-staging.hashedin.com/allocation/allocate");
    }
    public void go_to_pods_platform() throws InterruptedException {
        driver.get("https://dna-staging.hashedin.com/pods/requests/PR-87");
    }
    @AfterMethod
    public void tearDown() {
        // System.out.println("driver="+driver);
        driver.quit();
    }



}


