package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderTest {
    WebDriver driver;

    @BeforeAll // запускается перед всеми методами
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach // запускается перед каждым методом
    public void setUp1() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");  // открываем браузер
    }

    @AfterEach // метод закрывает все поля браузера
    public void close() {
        driver.quit();
        driver = null;
    }

    // Подготовка закончена, далее создаем тесты
    @Test
    public void test() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван"); // находим элемент по уник.атрибуту через input, т.к. через спан нельзя
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79021234580"); // нах. тел по селектору
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.tagName("button")).click();                                         // находим по тэгу
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
        // trim() - удаляет лишние пробелы слева и права
    }

}



