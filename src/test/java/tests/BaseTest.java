package tests;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    private final static String pathFile = System.getProperty("user.dir") + "\\itachi.jpg";

    @BeforeAll
    public static void before() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.fastSetValue = true;
        Configuration.pageLoadTimeout = 60000L;
    }

    @Test
    public void fillingForm() {
        try {
            open("/automation-practice-form");
            $(By.id("firstName")).setValue("Tany");
            $(By.id("lastName")).setValue("Tupichko");
            $(By.id("userEmail")).setValue("tupichko@mail.ru");
            $(By.id("genterWrapper")).selectRadio("male");
            $(By.id("userNumber")).setValue(RandomStringUtils.randomNumeric(10));
            $(By.id("dateOfBirthInput")).click();
            $(By.xpath("//*[contains(@aria-label, ('August 16th'))]")).click();
            $(By.cssSelector("#subjectsContainer > div > div.css-1hwfws3")).setValue("English");
            new Robot().keyPress(KeyEvent.VK_ENTER);

            $(By.xpath("//*[@id='hobbies-checkbox-1']")).click();

            StringSelection patchTheFileSelector = new StringSelection(pathFile);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(patchTheFileSelector, null);
            $(By.xpath("//*[@id='uploadPicture']")).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Robot robot = new Robot();
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(150);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        $(By.id("currentAddress")).setValue("Сity of Voronezh, Embankment of Maxim Gorky, 49b");
        $(By.id("submit")).click();
        Assertions.assertThat($(By.id("example-modal-sizes-title-lg")).text()).isEqualTo("Thanks for submitting the form");
    }
}
