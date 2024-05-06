package com.billing;

import com.billing.services.Client2ServiceService;
import com.billing.services.ClientService;
import com.billing.services.ServiceService;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTests {

    @Test
    public void example() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();
    }

    @Test
    public void mainPage() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        String title = driver.getTitle();
        System.out.println(title);
        assertEquals("Billing", title);
        driver.quit();
    }

    @Test
    public void clients() {

        WebDriver driver = new ChromeDriver();
        login(driver);
        driver.findElement(By.linkText("Списки клиентов")).click();
        driver.findElement(By.cssSelector("button")).click();

        List<WebElement> rows = driver.findElement(By.cssSelector("tbody"))
                .findElements(By.cssSelector("tr"));

        Client2ServiceService css = new Client2ServiceService();
        assertEquals(css.findAll().size(), rows.size());
        driver.quit();
    }

    @Test
    public void history() {

        WebDriver driver = new ChromeDriver();
        login(driver);
        driver.findElement(By.linkText("История операций по счетам")).click();

        driver.findElement(By.name("clientVAC")).sendKeys("Абрамов Михаил Андреевич");
        driver.findElement(By.cssSelector("button")).click();

        List<WebElement> rows = driver.findElement(By.cssSelector("tbody"))
                .findElements(By.cssSelector("tr"));

        ClientService cs = new ClientService();
        assertEquals(cs.findByName("Абрамов Михаил Андреевич").getAccount().getHistory().size(), rows.size());
        driver.quit();
    }

    private void login(WebDriver driver) {
        driver.get("http://localhost:8080/index");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();
    }

    @Test
    public void registerContract() {

        WebDriver driver = new ChromeDriver();
        login(driver);
        driver.findElement(By.linkText("Регистрация договоров об оказании услуг")).click();

        Client2ServiceService css = new Client2ServiceService();
        int before = css.findAll().size();

        driver.findElement(By.name("clientVAC")).sendKeys("Абрамов Михаил Андреевич");
        driver.findElement(By.name("serviceVAC")).sendKeys("Семья");
        driver.findElement(By.name("contractVAC")).sendKeys("Testing");
        driver.findElement(By.cssSelector("button")).click();

        assertEquals(css.findAll().size(), before + 1);
        driver.quit();
    }

    @Test
    public void registerTrans() {

        WebDriver driver = new ChromeDriver();
        login(driver);
        driver.findElement(By.linkText("Регистрация поступлений/списаний")).click();

        ClientService cs = new ClientService();
        int before = cs.findByName("Абрамов Михаил Андреевич").getAccount().getHistory().size();

        driver.findElement(By.name("clientVAC")).sendKeys("Абрамов Михаил Андреевич");
        driver.findElement(By.name("amountVAC")).sendKeys("1000");
        driver.findElement(By.cssSelector("button")).click();

        assertEquals(cs.findByName("Абрамов Михаил Андреевич").getAccount().getHistory().size(), before + 1);
        driver.quit();
    }

    @Test
    public void manageClient() {

        WebDriver driver = new ChromeDriver();
        login(driver);
        driver.findElement(By.linkText("Управление клиентами")).click();

        ClientService cs = new ClientService();

        driver.findElement(By.name("clientVAC")).sendKeys("Абрамов Михаил Андреевич");
        driver.findElement(By.id("find")).click();

        List<WebElement> rows = driver.findElement(By.cssSelector("tbody"))
                .findElements(By.cssSelector("tr"));
        assertEquals(1, rows.size());

        driver.findElement(By.name("clientVAC")).sendKeys("test");
        driver.findElement(By.name("phoneVAC")).sendKeys("89999999999");
        driver.findElement(By.name("emailVAC")).sendKeys("test@test");
        driver.findElement(By.name("addressVAC")).sendKeys("test");
        driver.findElement(By.id("create")).click();

        assertNotNull(cs.findByName("test"));

        driver.findElement(By.name("clientVAC")).sendKeys("test");
        driver.findElement(By.name("phoneVAC")).sendKeys("89999999998");
        driver.findElement(By.id("edit")).click();

        assertEquals("89999999998", cs.findByName("test").getInfo().getPhone());

        driver.findElement(By.name("clientVAC")).sendKeys("test");
        driver.findElement(By.id("delete")).click();

        assertNull(cs.findByName("test"));
        driver.quit();
    }

    @Test
    public void manageService() {

        WebDriver driver = new ChromeDriver();
        login(driver);
        driver.findElement(By.linkText("Управление услугами")).click();

        ServiceService ss = new ServiceService();

        driver.findElement(By.name("serviceVAC")).sendKeys("Всё за 500");
        driver.findElement(By.id("find")).click();

        List<WebElement> rows = driver.findElement(By.cssSelector("tbody"))
                .findElements(By.cssSelector("tr"));
        assertEquals(1, rows.size());

        driver.findElement(By.name("serviceVAC")).sendKeys("test");
        driver.findElement(By.name("minVAC")).sendKeys("1");
        driver.findElement(By.name("smsVAC")).sendKeys("1");
        driver.findElement(By.name("internetVAC")).sendKeys("1");
        driver.findElement(By.name("max_membersVAC")).sendKeys("1");
        driver.findElement(By.name("tariffVAC")).sendKeys("1");
        driver.findElement(By.name("extra_minVAC")).sendKeys("1");
        driver.findElement(By.name("extra_smsVAC")).sendKeys("1");
        driver.findElement(By.name("extra_internetVAC")).sendKeys("1");
        driver.findElement(By.id("create")).click();

        assertNotNull(ss.findByName("test"));

        driver.findElement(By.name("serviceVAC")).sendKeys("test");
        driver.findElement(By.name("minVAC")).sendKeys("1");
        driver.findElement(By.name("smsVAC")).sendKeys("1");
        driver.findElement(By.name("internetVAC")).sendKeys("1");
        driver.findElement(By.name("max_membersVAC")).sendKeys("2");
        driver.findElement(By.name("tariffVAC")).sendKeys("1");
        driver.findElement(By.name("extra_minVAC")).sendKeys("1");
        driver.findElement(By.name("extra_smsVAC")).sendKeys("1");
        driver.findElement(By.name("extra_internetVAC")).sendKeys("1");
        driver.findElement(By.id("edit")).click();

        assertEquals(2, ss.findByName("test").getPack().getMax_members());

        driver.findElement(By.name("serviceVAC")).sendKeys("test");
        driver.findElement(By.id("delete")).click();

        assertNull(ss.findByName("test"));
        driver.quit();
    }
}